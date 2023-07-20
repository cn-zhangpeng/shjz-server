package com.shjz.zp95sky.shjz.server.user.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.user.biz.UserBiz;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * 用户 Service 测试
 * @author 山海紫穹
 */
@CommonTestAnnotation
public class UserServiceTest {

    @Resource
    private UserBiz userBiz;

    @Test
    public void testGetByPrimaryKey() {
        BaseResult<UserDo> userInfo = userBiz.getUserInfo();
        System.out.println(userInfo);
    }

}
