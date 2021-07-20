package com.shjz.zp95sky.shjz.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文章详情
 * @author 华夏紫穹
  */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article_detail")
public class ArticleDetail {

    /** 文章ID */
    @TableId(type = IdType.INPUT)
    private Long id;

    /** 分类 ID */
    @TableField("category_id")
    private Long categoryId;

    /** 文章标题 */
    @TableField("article_title")
    private String articleTitle;

    /** 文章摘要 */
    @TableField("article_summary")
    private String articleSummary;

    /** 文章内容 */
    @TableField("article_content")
    private String articleContent;

    /** 文章标签（多个 标签之间，以，拆分） */
    @TableField("article_tags")
    private String articleTags;

    /** 是否原创 */
    @TableField("is_original")
    private Boolean isOriginal;

    /** 文章创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

}
