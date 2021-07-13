package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleContentEs;
import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleTotalAndIdList;

/**
 * 文章业务 ES 处理
 * @author 华夏紫穹
 */
public interface ArticleDetailEsService {

    /**
     * 根据关键字查询文章ID列表
     * @param page 当前页，从1开始
     * @param size 条数
     * @param keyword 关键字
     * @return 文章总数及ID列表
     */
    ArticleTotalAndIdList getArticleIdList(Integer page, Integer size, String keyword);

    /**
     * 向 ES 中写入文章内容
     * @param articleContentEs 文章信息
     */
    void saveArticle(ArticleContentEs articleContentEs);

    /**
     * 根据 ID 删除文章内容
     * @param articleId 文章 ID
     * @return 删除成功，返回 true，否则返回 false
     */
    boolean deleteArticle(String articleId);

}
