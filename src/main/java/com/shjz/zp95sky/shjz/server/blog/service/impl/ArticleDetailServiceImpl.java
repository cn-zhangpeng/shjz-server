package com.shjz.zp95sky.shjz.server.blog.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.GetArticleListByKeywordDto;
import com.shjz.zp95sky.shjz.server.blog.dto.PublishArticleDto;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleContent;
import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleContentEs;
import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleTotalAndIdList;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleContentMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailEsService;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleDetail;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleDetailMapper;
import com.shjz.zp95sky.shjz.server.blog.mapper.CategoryMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailService;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BasePageResult;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ModelResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章详情业务实现
 * @author 华夏紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>
        implements ArticleDetailService {

    private final ArticleDetailEsService articleDetailEsService;
    private final ArticleDetailMapper articleDetailMapper;
    private final CategoryMapper categoryMapper;
    private final ArticleContentMapper articleContentMapper;
    private final Snowflake snowflake;

    @Override
    public BasePageResult<ArticleListDo> getArticleList(CustomPage customPage) {
        return getArticleListService(customPage);
    }

    @Override
    public BasePageResult<ArticleListDo> getArticleListByCategory(Long categoryId, CustomPage customPage) {
        LambdaQueryWrapper<ArticleDetail> queryWrapper = getArticleListQueryWrapper(categoryId, null);
        return getArticleListService(customPage, queryWrapper);
    }

    @Override
    public ArticleDetailDo getArticleById(Long articleId) {
        ArticleDetail articleDetail = selectArticleDetailById(articleId);
        if (articleDetail == null) { return null; }

        return handleArticleDetailData(articleDetail);
    }

    @Override
    public boolean deleteArticle(Long articleId) {
        // 从 es 中删除文章
        if (! articleDetailEsService.deleteArticle(String.valueOf(articleId))) {
            return false;
        }

        // 从 mysql 删除数据
        return deleteArticleById(articleId);
    }

    @Override
    public boolean publishArticle(PublishArticleDto articleDto) {
        Long articleId = snowflake.nextId();

        // 保存文章信息
        if (! publishArticleService(articleId, articleDto)) {
            return false;
        }

        // 向 es 中保存文章内容
        ArticleContentEs articleContentEs = ArticleContentEs.builder()
                .id(String.valueOf(articleId)).title(articleDto.getArticleTitle())
                .content(articleDto.getArticleContent())
                .build();
        articleDetailEsService.saveArticle(articleContentEs);
        return true;
    }

    private boolean publishArticleService(Long articleId, PublishArticleDto articleDto) {
        LocalDateTime curTime = LocalDateTime.now();

        // 保存文章信息
        ArticleDetail articleDetail = ArticleDetail.builder()
                .id(articleId).categoryId(articleDto.getCategoryId())
                .articleTitle(articleDto.getArticleTitle())
                .articleSummary(subArticleSummary(articleDto.getArticleContent()))
                .articleTags(String.join(Constants.ARTICLE_TAG_DELIMITER, articleDto.getArticleTagList()))
                .isOriginal(articleDto.getIsOriginal()).createTime(curTime)
                .build();
        if (insertArticle(articleDetail) != 1) {
            return false;
        }

        // 保存文章内容信息
        ArticleContent articleContent = ArticleContent.builder()
                .id(snowflake.nextId()).articleDetailId(articleId)
                .articleContent(articleDto.getArticleContent())
                .createTime(curTime)
                .build();

        return insertArticleContent(articleContent) == 1;
    }

    @Override
    public BasePageResult<ArticleListDo> getArticleListByKeyword(GetArticleListByKeywordDto keywordDto) {
        Integer page = keywordDto.getPage();
        Integer size = keywordDto.getSize();

        // 通过 ES 查询文章数量及当前页文章 ID 列表
        ArticleTotalAndIdList articleInfo = articleDetailEsService.getArticleIdList(page, size, keywordDto.getKeyword());
        List<Long> articleIdList = articleInfo.getArticleIdList();

        // 假如没有查询到结果，直接返回空。
        if (articleInfo.getTotal() <= 0 || CollectionUtils.isEmpty(articleIdList)) {
            BasePageResult.getInstance(page, size, articleInfo.getTotal(), Collections.emptyList());
        }

        // 通过文章 ID 列表查询文章详情
        LambdaQueryWrapper<ArticleDetail> queryWrapper = getArticleListQueryWrapper(null, articleIdList);
        List<ArticleDetail> articleDetailList = selectArticleList(queryWrapper);

        // 通过文章 ID 列表对查询结果进行重排序
        List<ArticleDetail> orderedArticleDetailList = orderArticleListByArticleIdList(articleDetailList, articleIdList);

        // 组装返回列表
        List<ArticleListDo> articleListDoList = handleArticleListData(orderedArticleDetailList);

        return BasePageResult.getInstance(page, size, articleInfo.getTotal(), articleListDoList);
    }

    @Override
    public BaseResult<Void> changeOriginal(Long articleId, Boolean isOriginal) {
        ArticleDetail articleDetail = ArticleDetail.builder()
                .id(articleId).isOriginal(isOriginal)
                .build();
        updateById(articleDetail);
        return ModelResultUtil.buildResultSuccess();
    }

    private List<ArticleDetail> orderArticleListByArticleIdList(List<ArticleDetail> articleDetailList, List<Long> articleIdList) {
        List<ArticleDetail> result = new ArrayList<>(articleDetailList.size());
        Map<Long, ArticleDetail> articleDetailMap = articleDetailList.stream().collect(Collectors.toMap(ArticleDetail::getId, Function.identity()));
        articleIdList.forEach(a -> result.add(articleDetailMap.get(a)));
        return result;
    }

    private String subArticleSummary(String articleContent) {
        if (articleContent.length() < Constants.ARTICLE_SUMMARY_LENGTH) {
            return articleContent;
        }
        return articleContent.substring(0, Constants.ARTICLE_SUMMARY_LENGTH);
     }

    private BasePageResult<ArticleListDo> getArticleListService(CustomPage customPage) {
        return getArticleListService(customPage, null);
    }
    private BasePageResult<ArticleListDo> getArticleListService(CustomPage customPage, LambdaQueryWrapper<ArticleDetail> queryWrapper) {
        Integer page = customPage.getPage();
        Integer size = customPage.getSize();

        IPage<ArticleDetail> articleDetailPage = this.selectArticleList(page, size, queryWrapper);
        List<ArticleDetail> articleDetailList = articleDetailPage.getRecords();
        if (CollectionUtils.isEmpty(articleDetailList)) {
            return BasePageResult.getInstance(page, size, 0L, new ArrayList<>());
        }

        List<ArticleListDo> articleListDoList = handleArticleListData(articleDetailList);
        return BasePageResult.getInstance(page, size, articleDetailPage.getTotal(), articleListDoList);
    }

    private LambdaQueryWrapper<ArticleDetail> getArticleListQueryWrapper(Long categoryId, List<Long> articleIdList) {
        LambdaQueryWrapper<ArticleDetail> queryWrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq(ArticleDetail::getCategoryId, categoryId);
        }
        if (! CollectionUtils.isEmpty(articleIdList)) {
            queryWrapper.in(ArticleDetail::getId, articleIdList);
        }
        return queryWrapper;
    }

    private int insertArticleContent(ArticleContent articleContent) {
        return articleContentMapper.insert(articleContent);
    }

    private int insertArticle(ArticleDetail articleDetail) {
        return articleDetailMapper.insert(articleDetail);
    }

    private boolean deleteArticleById(Long articleId) {
        if (articleDetailMapper.deleteById(articleId) < 0) {
            return false;
        }
        return deleteArticleContentByArticleId(articleId) == 1;
    }

    private List<ArticleListDo> handleArticleListData(List<ArticleDetail> articleDetailList) {
        List<ArticleListDo> articleListDoList = new ArrayList<>();

        if (CollectionUtils.isEmpty(articleDetailList)) { return articleListDoList; }

        Set<Long> categoryIdSet = articleDetailList.stream().map(ArticleDetail::getCategoryId).collect(Collectors.toSet());
        List<ArticleCategory> categoryList = categoryMapper.selectBatchIds(categoryIdSet);
        Map<Long, String> categoryMap = categoryList.stream().collect(Collectors.toMap(ArticleCategory::getId, ArticleCategory::getCategoryName));

        articleDetailList.forEach(article -> {
            ArticleListDo articleListDo = ArticleListDo.builder()
                    .articleId(article.getId()).categoryName(categoryMap.get(article.getCategoryId()))
                    .articleTitle(article.getArticleTitle()).articleSummary(article.getArticleSummary())
                    .articleTagList(Arrays.asList(article.getArticleTags().split(Constants.ARTICLE_TAG_DELIMITER)))
                    .isOriginal(article.getIsOriginal()).createTime(article.getCreateTime())
                    .build();
            articleListDoList.add(articleListDo);
        });

        return articleListDoList;
    }

    private ArticleDetailDo handleArticleDetailData(ArticleDetail articleDetail) {
        ArticleCategory category = selectArticleCategoryById(articleDetail.getCategoryId());
        ArticleContent content = selectArticleContentByArticleId(articleDetail.getId());

        return ArticleDetailDo.builder()
                .articleId(articleDetail.getId()).articleTitle(articleDetail.getArticleTitle())
                .categoryName(category == null ? Constants.STRING_DATA_DEFAULT : category.getCategoryName())
                .articleContent(content == null ? Constants.STRING_DATA_DEFAULT : content.getArticleContent())
                .articleTagList(Arrays.asList(articleDetail.getArticleTags().split(Constants.ARTICLE_TAG_DELIMITER)))
                .isOriginal(articleDetail.getIsOriginal()).createTime(articleDetail.getCreateTime())
                .build();
    }

    private ArticleDetail selectArticleDetailById(Long articleId) {
        if (articleId == null) { return null; }
        return articleDetailMapper.selectById(articleId);
    }

    private ArticleCategory selectArticleCategoryById(Long categoryId) {
        if (categoryId == null) { return null; }
        return categoryMapper.selectById(categoryId);
    }

    private ArticleContent selectArticleContentByArticleId(Long articleId) {
        if (articleId == null) { return null; }

        LambdaQueryWrapper<ArticleContent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleContent::getArticleDetailId, articleId)
                .orderByDesc(ArticleContent::getCreateTime);
        List<ArticleContent> articleContentList = articleContentMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(articleContentList)) { return null; }

        return articleContentList.get(0);
    }

    private IPage<ArticleDetail> selectArticleList(Integer page, Integer size, LambdaQueryWrapper<ArticleDetail> queryWrapper) {
        IPage<ArticleDetail> pageParam = new Page<>(page, size);

        if (queryWrapper == null) {
            queryWrapper = new LambdaQueryWrapper<>();
        }
        queryWrapper.orderByDesc(ArticleDetail::getCreateTime)
                .orderByDesc(ArticleDetail::getId);

        return articleDetailMapper.selectPage(pageParam, queryWrapper);
    }

    private List<ArticleDetail> selectArticleList(LambdaQueryWrapper<ArticleDetail> queryWrapper) {
        return articleDetailMapper.selectList(queryWrapper);
    }

    private int deleteArticleContentByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleContent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleContent::getArticleDetailId, articleId);
        return articleContentMapper.delete(queryWrapper);
    }

}
