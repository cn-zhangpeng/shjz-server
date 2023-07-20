package com.shjz.zp95sky.shjz.server.software.controller;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.software.biz.SoftwareUseTimeBiz;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearStatisticDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "软件使用统计")
@RestController
@RequestMapping(value = "/software/time")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class SoftwareUseTimeController {

    private final SoftwareUseTimeBiz softwareUseTimeBiz;

    @ApiOperation("上报使用记录")
    @PostMapping("/reportRecord/batch")
    public BaseResult<Void> reportRecord(@RequestBody BatchReportSoftwareUseTimeDto softwareUseTimeDto) {
        return softwareUseTimeBiz.batchReportSoftwareUseTime(softwareUseTimeDto);
    }

    @ApiOperation("近一周时间使用量统计")
    @GetMapping("/weekStatistic")
    public BaseResult<WeekStatisticDo> weekStatistic() {
        return softwareUseTimeBiz.weekStatistic();
    }

    @ApiOperation("本年软件使用详情")
    @GetMapping("/yearStatistic")
    public BaseResult<List<YearStatisticDo>> yearStatistic() {
        return softwareUseTimeBiz.yearStatistic();
    }

    @ApiOperation("本年每天软件的使用时长")
    @GetMapping("/yearDateStatistic")
    public BaseResult<List<YearDateStatisticDo>> yearDateStatistic() {
        return softwareUseTimeBiz.yearDateStatistic();
    }

}
