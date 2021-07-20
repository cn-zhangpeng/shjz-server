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
import com.shjz.zp95sky.shjz.server.blog.service.ArticleCategoryService;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleDetail;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleDetailMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailService;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文章详情业务实现
 * @author 华夏紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>
        implements ArticleDetailService {

    private final ArticleCategoryService categoryService;

    private final Snowflake snowflake;

    @Override
    public BaseResult<List<ArticleListDo>> getArticleList(CustomPage customPage) {
        Integer page = customPage.getPage();
        Integer size = customPage.getSize();

        IPage<ArticleDetail> pageResult = selectArticleList(page, size);
        return buildArticleListDo(page, size, pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public BaseResult<List<ArticleListDo>> getArticleListByCategory(Long categoryId, CustomPage customPage) {
        Integer page = customPage.getPage();
        Integer size = customPage.getSize();

        IPage<ArticleDetail> pageResult = selectArticleListByCategory(page, size, categoryId);
        return buildArticleListDo(page, size, pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public BaseResult<ArticleDetailDo> getArticleById(Long articleId) {
        ArticleDetail articleDetail = getById(articleId);
        if (articleDetail == null) { return null; }

        return buildArticleDetailDo(articleDetail);
    }

    @Override
    public boolean deleteArticle(Long articleId) {
        // 从 mysql 删除数据
        return removeById(articleId);
    }

    @Override
    public boolean publishArticle(PublishArticleDto articleDto) {
        Long articleId = snowflake.nextId();

        // 保存文章信息
        return publishArticleService(articleId, articleDto);
    }

    @Override
    public BaseResult<List<ArticleListDo>> getArticleListByKeyword(GetArticleListByKeywordDto keywordDto) {
        Integer page = keywordDto.getPage();
        Integer size = keywordDto.getSize();

        // 模糊查询文章列表
        LambdaQueryWrapper<ArticleDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ArticleDetail::getArticleTitle, keywordDto.getKeyword());
        IPage<ArticleDetail> pageResult = selectArticleList(page, size, queryWrapper);

        return buildArticleListDo(page, size, pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public BaseResult<Void> changeOriginal(Long articleId, Boolean isOriginal) {
        ArticleDetail articleDetail = ArticleDetail.builder()
                .id(articleId).isOriginal(isOriginal)
                .build();
        updateById(articleDetail);
        return ResultUtil.buildResultSuccess();
    }

    private IPage<ArticleDetail> selectArticleListByCategory(Integer page, Integer size, Long categoryId) {
        LambdaQueryWrapper<ArticleDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleDetail::getCategoryId, categoryId);
        return selectArticleList(page, size, queryWrapper);
    }

    private boolean publishArticleService(Long articleId, PublishArticleDto articleDto) {
        LocalDateTime curTime = LocalDateTime.now();

        // 保存文章信息
        ArticleDetail articleDetail = ArticleDetail.builder()
                .id(articleId).categoryId(articleDto.getCategoryId())
                .articleTitle(articleDto.getArticleTitle())
                .articleSummary(subArticleSummary(articleDto.getArticleContent()))
                .articleContent(articleDto.getArticleContent())
                .articleTags(String.join(Constants.ARTICLE_TAG_DELIMITER, articleDto.getArticleTagList()))
                .isOriginal(articleDto.getIsOriginal()).createTime(curTime)
                .build();
        return save(articleDetail);
    }

    private String subArticleSummary(String articleContent) {
        if (articleContent.length() < Constants.ARTICLE_SUMMARY_LENGTH) {
            return articleContent;
        }
        return articleContent.substring(0, Constants.ARTICLE_SUMMARY_LENGTH);
     }

    private BaseResult<List<ArticleListDo>> buildArticleListDo(
            Integer page, Integer pageSize, Long total, List<ArticleDetail> articleDetailList) {

        List<ArticleListDo> articleListDoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(articleDetailList)) {
            return ResultUtil.buildResultSuccess(page, pageSize, total, articleListDoList);
        }

        Set<Long> categoryIdSet = articleDetailList.stream().map(ArticleDetail::getCategoryId).collect(Collectors.toSet());
        List<ArticleCategory> categoryList = categoryService.listByIds(categoryIdSet);
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

        return ResultUtil.buildResultSuccess(page, pageSize, total, articleListDoList);
    }

    private BaseResult<ArticleDetailDo> buildArticleDetailDo(ArticleDetail articleDetail) {
        ArticleCategory category = categoryService.getById(articleDetail.getCategoryId());

        ArticleDetailDo articleDetailDo = ArticleDetailDo.builder()
                .articleId(articleDetail.getId()).articleTitle(articleDetail.getArticleTitle())
                .categoryName(category == null ? Constants.STRING_DATA_DEFAULT : category.getCategoryName())
                .articleContent(articleDetail.getArticleContent())
                .articleTagList(Arrays.asList(articleDetail.getArticleTags().split(Constants.ARTICLE_TAG_DELIMITER)))
                .isOriginal(articleDetail.getIsOriginal()).createTime(articleDetail.getCreateTime())
                .build();
        return ResultUtil.buildResultSuccess(articleDetailDo);
    }

    private IPage<ArticleDetail> selectArticleList(Integer page, Integer size) {
        return selectArticleList(page, size, null);
    }
    private IPage<ArticleDetail> selectArticleList(Integer page, Integer size, LambdaQueryWrapper<ArticleDetail> queryWrapper) {
        IPage<ArticleDetail> pageParam = new Page<>(page, size);

        if (queryWrapper == null) {
            queryWrapper = new LambdaQueryWrapper<>();
        }
        queryWrapper.orderByDesc(ArticleDetail::getCreateTime)
                .orderByDesc(ArticleDetail::getId);

        return page(pageParam, queryWrapper);
    }

}
