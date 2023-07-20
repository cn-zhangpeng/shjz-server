package com.shjz.zp95sky.shjz.server.software.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.software.biz.SoftwareBiz;
import com.shjz.zp95sky.shjz.server.software.dto.AddSoftwareDto;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 软件业务测试类
 */
@CommonTestAnnotation
public class SoftwareServiceTest {

    @Resource
    private SoftwareBiz softwareBiz;

    @Test
    public void testAddSoftware() {
        String[] softwareArr = {"MindMaster"};
        List<AddSoftwareDto> softwareList = new ArrayList<>(softwareArr.length);
        for (String name : softwareArr) {
            softwareList.add(constructAddSoftwareDto(name));
        }
        softwareBiz.batchAddSoftware(softwareList);
    }

    private AddSoftwareDto constructAddSoftwareDto(String softwareName) {
        AddSoftwareDto softwareDto = new AddSoftwareDto();
        softwareDto.setSoftwareName(softwareName);
        return softwareDto;
    }

}
