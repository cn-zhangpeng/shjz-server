package com.shjz.zp95sky.shjz.server.blog.controller;

import com.shjz.zp95sky.shjz.server.annotation.MockTestAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * 文章类型测试
 * @author 华夏紫穹
 */
@MockTestAnnotation
public class ArticleArticleCategoryControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testGetCategoryList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blog/categories")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}
