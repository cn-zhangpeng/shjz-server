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
 * 文章内容
 * @author 华夏紫穹
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article_content")
public class ArticleContent {

    /** 内容 ID */
    @TableId(type = IdType.INPUT)
    private Long id;

    /** 文章 ID */
    @TableField("article_id")
    private Long articleDetailId;

    /** 文章内容 */
    @TableField("article_content")
    private String articleContent;

    /** 文章内容创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

}
