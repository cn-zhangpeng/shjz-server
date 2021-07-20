package com.shjz.zp95sky.shjz.server.software.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.software.domain.SoftwareListDo;
import com.shjz.zp95sky.shjz.server.software.dto.AddSoftwareDto;
import com.shjz.zp95sky.shjz.server.software.entity.Software;
import com.shjz.zp95sky.shjz.server.software.mapper.SoftwareMapper;
import com.shjz.zp95sky.shjz.server.software.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 山海紫穹
 * @date 2021年06月24日 13:36
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class SoftwareServiceImpl extends ServiceImpl<SoftwareMapper, Software> implements SoftwareService {

    private final Snowflake snowflake;

    @Override
    public void addSoftware(AddSoftwareDto softwareDto) {
        Software software = constructSoftware(softwareDto.getSoftwareName());
        save(software);
    }

    @Override
    public void batchAddSoftware(List<AddSoftwareDto> softwareDtoList) {
        List<Software> softwareList = new ArrayList<>(softwareDtoList.size());
        for (AddSoftwareDto softwareDto : softwareDtoList) {
            softwareList.add(constructSoftware(softwareDto.getSoftwareName()));
        }
        saveBatch(softwareList);
    }

    @Override
    public List<SoftwareListDo> getSoftwareList() {
        List<Software> softwareList = list();
        List<SoftwareListDo> softwareListDos = new ArrayList<>(softwareList.size());

        if (CollectionUtils.isEmpty(softwareList)) { return softwareListDos; }
        softwareList.forEach(s -> softwareListDos.add(constructSoftwareListDo(s)));
        return softwareListDos;
    }

    private SoftwareListDo constructSoftwareListDo(Software software) {
        return SoftwareListDo.builder()
                .id(software.getId()).softwareName(software.getSoftwareName())
                .build();
    }

    private Software constructSoftware(String softwareName) {
        return Software.builder()
                .id(snowflake.nextId()).softwareName(softwareName)
                .build();
    }

}
