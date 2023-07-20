package com.shjz.zp95sky.shjz.server.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleCategoryMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

/**
 * 文章类型业务实现
 * @author 山海紫穹
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory>
        implements ArticleCategoryService {

}
