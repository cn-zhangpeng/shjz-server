package com.shjz.zp95sky.shjz.server.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.GetArticleListByKeywordDto;
import com.shjz.zp95sky.shjz.server.blog.dto.PublishArticleDto;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleDetail;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BasePageData;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;

import java.util.List;

/**
 * 文章业务
 * @author 华夏紫穹
 */
public interface ArticleDetailService extends IService<ArticleDetail> {

    /**
     * 根据分页信息查询文章列表
     * @param customPage 分页信息
     * @return 查询结果
     */
    BaseResult<List<ArticleListDo>> getArticleList(CustomPage customPage);

    /**
     * 根据类型查询文章列表
     * @param categoryId 文章类型 ID
     * @param customPage 分页信息
     * @return 查询结果
     */
    BaseResult<List<ArticleListDo>> getArticleListByCategory(Long categoryId, CustomPage customPage);

    /**
     * 根据文章 ID 查询文章详情
     * @param articleId 文章 ID
     * @return 查询结果
     */
    BaseResult<ArticleDetailDo> getArticleById(Long articleId);

    /**
     * 根据文章 ID 删除文章
     * @param articleId 文章 ID
     * @return 删除成功，返回 true，否则返回 false
     */
    boolean deleteArticle(Long articleId);

    /**
     * 发布文章
     * @param articleDto 文章信息
     * @return 发布成功，返回 true，否则返回 false
     */
    boolean publishArticle(PublishArticleDto articleDto);

    /**
     * 根据关键字查询文章列表（标题）
     * @param keywordDto 关键字及分页信息
     * @return 查询结果
     */
    BaseResult<List<ArticleListDo>> getArticleListByKeyword(GetArticleListByKeywordDto keywordDto);

    /**
     * 修改文章是否原创标识
     * @param articleId 文章ID
     * @param isOriginal 是否原创，true：原创，false：非原创
     * @return 修改结果
     */
    BaseResult<Void> changeOriginal(Long articleId, Boolean isOriginal);

}
