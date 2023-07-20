package com.shjz.zp95sky.shjz.server.software.biz;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearStatisticDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.dto.ReportSoftwareUseTimeDto;

import java.util.List;

/**
 * 软件使用时间业务处理
 */
public interface SoftwareUseTimeBiz {

    /**
     * 上报软件使用时长
     * @param useTimeDto 软件使用时长信息
     */
    void reportSoftwareUseTime(ReportSoftwareUseTimeDto useTimeDto);

    /**
     * 批量上报软件使用时长
     * @param useTimeDto 软件使用时长信息
     * @return 上报结果
     */
    BaseResult<Void> batchReportSoftwareUseTime(BatchReportSoftwareUseTimeDto useTimeDto);

    /**
     * 近一周时间使用量统计
     * @return 查询结果
     */
    BaseResult<WeekStatisticDo> weekStatistic();

    /**
     * 本年软件使用详情
     * @return 使用量统计
     */
    BaseResult<List<YearStatisticDo>> yearStatistic();

    /**
     * 本年每天软件的使用量统计
     * @return 使用统计
     */
    BaseResult<List<YearDateStatisticDo>> yearDateStatistic();

}
