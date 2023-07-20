package com.shjz.zp95sky.shjz.server.software.biz.impl;

import cn.hutool.core.lang.Snowflake;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.software.domain.SoftwareListDo;
import com.shjz.zp95sky.shjz.server.software.dto.AddSoftwareDto;
import com.shjz.zp95sky.shjz.server.software.entity.Software;
import com.shjz.zp95sky.shjz.server.software.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class SoftwareBizImpl implements com.shjz.zp95sky.shjz.server.software.biz.SoftwareBiz {

    private final SoftwareService softwareService;

    private final Snowflake snowflake;

    @Override
    public void addSoftware(AddSoftwareDto softwareDto) {
        Software software = buildSoftware(softwareDto.getSoftwareName());
        softwareService.save(software);
    }

    @Override
    public void batchAddSoftware(List<AddSoftwareDto> softwareDtoList) {
        List<Software> softwareList = new ArrayList<>(softwareDtoList.size());
        for (AddSoftwareDto softwareDto : softwareDtoList) {
            softwareList.add(buildSoftware(softwareDto.getSoftwareName()));
        }
        softwareService.saveBatch(softwareList);
    }

    @Override
    public BaseResult<List<SoftwareListDo>> getSoftwareList() {
        List<Software> softwareList = softwareService.list();
        List<SoftwareListDo> result = new ArrayList<>(softwareList.size());
        if (CollectionUtils.isEmpty(softwareList)) {
            return ResultUtil.buildResultSuccess(result);
        }

        softwareList.forEach(s -> result.add(buildSoftwareListDo(s)));
        return ResultUtil.buildResultSuccess(result);
    }

    private SoftwareListDo buildSoftwareListDo(Software software) {
        return SoftwareListDo.builder()
                .id(software.getId()).softwareName(software.getSoftwareName())
                .build();
    }

    private Software buildSoftware(String softwareName) {
        return Software.builder()
                .id(snowflake.nextId()).softwareName(softwareName)
                .build();
    }

}
