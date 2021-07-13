package com.shjz.zp95sky.shjz.server.blog.service.impl;

import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleContentEs;
import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleTotalAndIdList;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailEsService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章业务 ES 处理实现
 * @author 华夏紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleDetailEsServiceImpl implements ArticleDetailEsService {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public ArticleTotalAndIdList getArticleIdList(Integer page, Integer size, String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", keyword))
                .should(QueryBuilders.matchQuery("content", keyword));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(page - 1, size))
                .withQuery(queryBuilder)
                .build();
        SearchHits<ArticleContentEs> searchHits = elasticsearchOperations.search(nativeSearchQuery, ArticleContentEs.class);
        List<Long> articleIdList = searchHits.getSearchHits().stream().map(SearchHit::getContent).map(article -> Long.parseLong(article.getId())).collect(Collectors.toList());
        return ArticleTotalAndIdList.builder().total(searchHits.getTotalHits()).articleIdList(articleIdList).build();
    }

    @Override
    public void saveArticle(ArticleContentEs articleContentEs) {
        elasticsearchOperations.save(articleContentEs);
    }

    @Override
    public boolean deleteArticle(String articleId) {
        String deletedArticleId = elasticsearchOperations.delete(articleId, ArticleContentEs.class);
        return articleId.equals(deletedArticleId);
    }
}
