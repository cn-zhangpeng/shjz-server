package com.shjz.zp95sky.shjz.server.software.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.software.dto.AddSoftwareDto;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 软件业务测试类
 * @author 山海紫穹
 * @date 2021年06月24日 13:31
 */
@CommonTestAnnotation
public class SoftwareServiceTest {

    @Resource
    private SoftwareService softwareService;

    @Test
    public void testAddSoftware() {
        String[] softwareArr = {"MindMaster"};
        List<AddSoftwareDto> softwareList = new ArrayList<>(softwareArr.length);
        for (String name : softwareArr) {
            softwareList.add(constructAddSoftwareDto(name));
        }
        softwareService.batchAddSoftware(softwareList);
    }

    private AddSoftwareDto constructAddSoftwareDto(String softwareName) {
        AddSoftwareDto softwareDto = new AddSoftwareDto();
        softwareDto.setSoftwareName(softwareName);
        return softwareDto;
    }

}
