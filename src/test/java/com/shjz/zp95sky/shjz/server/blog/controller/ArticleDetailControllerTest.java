package com.shjz.zp95sky.shjz.server.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shjz.zp95sky.shjz.server.annotation.MockTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.dto.PublishArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 文章详情测试
 * @author 华夏紫穹
 */
@MockTestAnnotation
public class ArticleDetailControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void testGetArticleList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blog/articles")
                    .param("page", "1").param("size", "10")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testGetArticleListByCategory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/categories/1/articles")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testGetArticleDetail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blog/articles/1")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testGetLatestArticleList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blog/articles/latest")
                    .param("page", "1").param("size", "10")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testPublishArticle() throws Exception {
        PublishArticleDto articleDto = PublishArticleDto.builder()
                .categoryId(1L).articleTitle("人生")
                .articleContent("一个人，在他年轻的时候，倘若不能够总是对未知的生活充满欲望，那么在他不再年轻的时候，一定会深感到遗憾痛悔。那么，我们在青年的时代，应当拥有怎样的人生的态度呢?激情，向上，勇敢，无畏，坚韧，执着，沉着，镇静。但凡用来表示优异品质的，我们都希望获得，我们更多地希望获得这些，而使得自己更有光彩，熠熠夺目，这是出于一种浅薄的虚荣的心理。是的，倘若做一件事的动机是与其结果相悖的话，那么情节的发展，故事的结局又会有怎样的奇妙呢?这是我曾经用一段人生试图去回答的问题，然后我又用很长一段时间去思考和感悟，终究还是不能够真彻的懂得其中的意味。")
                .articleTagList(Arrays.asList("人生", "优美段落")).isOriginal(true)
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/blog/articles")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(articleDto))
                ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testDeleteArticle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/blog/articles/43000980618346496"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}
