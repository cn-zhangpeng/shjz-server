package com.shjz.zp95sky.shjz.server.software.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearStatisticDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.dto.ReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTime;

import java.util.List;

/**
 * 软件使用时间数据处理
 */
public interface SoftwareUseTimeService extends IService<SoftwareUseTime> {

}
