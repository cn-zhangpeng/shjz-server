package com.shjz.zp95sky.shjz.server.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shjz.zp95sky.shjz.server.annotation.MockTestAnnotation;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@MockTestAnnotation
public class LoginUtil {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    public HttpSession getSession() throws Exception {
        return getSession("peng.zhang", "123456");
    }

    public HttpSession getSession(String username, String password) throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        HttpSession session = mvcResult.getRequest().getSession();
        if (session == null) {
            throw new Exception("获取 sessionId 失败！");
        }
        return session;
    }

}
