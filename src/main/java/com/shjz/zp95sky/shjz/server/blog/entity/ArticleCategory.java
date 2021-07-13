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
 * 文章类型
 * @author 华夏紫穹
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article_category")
public class ArticleCategory {

    @TableId(type = IdType.INPUT)
    private Long id;

    @TableField("category_name")
    private String categoryName;

    @TableField("create_time")
    private LocalDateTime createTime;

}
