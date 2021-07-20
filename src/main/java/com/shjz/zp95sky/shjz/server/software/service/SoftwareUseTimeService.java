package com.shjz.zp95sky.shjz.server.software.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.dto.ReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTime;

import java.util.List;

/**
 * 软件使用时间业务处理
 * @author 山海紫穹
 * @date 2021年06月23日 14:09
 */
public interface SoftwareUseTimeService extends IService<SoftwareUseTime> {

    /**
     * 上报软件使用时长
     * @param useTimeDto 软件使用时长信息
     */
    void reportSoftwareUseTime(ReportSoftwareUseTimeDto useTimeDto);

    /**
     * 批量上报软件使用时长
     * @param useTimeDto 软件使用时长信息
     */
    void batchReportSoftwareUseTime(BatchReportSoftwareUseTimeDto useTimeDto);

    /**
     * 近一周时间使用量统计
     * @return 使用量统计
     */
    WeekStatisticDo weekStatistic();

    /**
     * 本年每天软件的使用量统计
     * @return 每一天的软件使用量
     */
    List<YearDateStatisticDo> yearDateStatistic();

}
