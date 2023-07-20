package com.shjz.zp95sky.shjz.server.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleDetail;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleDetailMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailService;
import org.springframework.stereotype.Service;

@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>
        implements ArticleDetailService {

}
