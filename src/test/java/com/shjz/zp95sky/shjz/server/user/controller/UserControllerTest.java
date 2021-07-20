package com.shjz.zp95sky.shjz.server.user.controller;

import com.shjz.zp95sky.shjz.server.annotation.MockTestAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * 用户测试
 * @author 山海紫穹
 */
@MockTestAnnotation
public class UserControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testGetUserInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/info/")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}


