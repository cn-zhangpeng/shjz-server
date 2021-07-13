package com.shjz.zp95sky.shjz.server.blog.entity.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * ES文章内容
 * @author 华夏紫穹
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "blog-article", createIndex = false)
public class ArticleContentEs {

    @Id
    private String id;

    @Field(name = "title")
    private String title;

    @Field(name = "content")
    private String content;

}
