package com.shjz.zp95sky.shjz.server.software.biz;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.software.domain.SoftwareListDo;
import com.shjz.zp95sky.shjz.server.software.dto.AddSoftwareDto;

import java.util.List;

/**
 * 软件业务处理
 */
public interface SoftwareBiz {

    /**
     * 添加软件
     * @param softwareDto 软件参数
     */
    void addSoftware(AddSoftwareDto softwareDto);

    /**
     * 批量添加软件
     * @param softwareDtoList 软件信息列表
     */
    void batchAddSoftware(List<AddSoftwareDto> softwareDtoList);

    /**
     * 查询软件列表
     * @return 软件列表
     */
    BaseResult<List<SoftwareListDo>> getSoftwareList();

}
