package com.shjz.zp95sky.shjz.server.software.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticUseTimeDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDetailDto;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.enums.SoftwareDeviceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 软件使用时间业务处理
 * @author 山海紫穹
 * @date 2021年06月24日 16:57
 */
@CommonTestAnnotation
@Slf4j
public class SoftwareUseTimeServiceTest {

    @Resource
    private SoftwareUseTimeService useTimeService;

    @Test
    public void testBatchReportSoftwareUseTime() {
        Map<Long, Integer> params = new HashMap<Long, Integer>() {{
            put(1407990753380143105L, 157); // Google Chrome
            put(1407990753380143106L, 74); // WeChat
            put(1409482786942881792L, 29); // WebStorm
            put(1407990753380143104L, 23); //IDEA
            put(1407990753380143112L, 15); // WPS Office
            put(1407990753380143111L, 13); // 企业微信
            put(1407990753380143107L, 12); // 腾讯QQ
            put(1408368116253200384L, 7); // Typora
            put(1408016476153188352L, 7); // 终端
            put(1407990753380143108L, 5); // Postman
            put(1408017039897006081L, 1); // DataGrip
            put(1407990753380143110L, 1); // 有道云笔记

//            put(1434715800131997696L, 4); //FinalShell
//            put(1408017039897006082L, 1); // Visual Studio Code
//            put(1412006558350577664L, 20); // 微信开发者工具
//            put(1412375884870062080L, 12); // HBuilder X
//            put(1413328446360260609L, 2); // 百度网盘
//            put(1408368819264688128L, 12); // 网易邮箱大师
//            put(1434716184162471936L, 8); // MindMaster
//            put(1434713117455159296L, 7); // 向日葵
//            put(1434713507177304064L, 7); // GoLand
//            put(1408369150115581952L, 1); // 滴答清单
//            put(1407990753380143109L, 8); // Xshell
//            put(1408017039897006080L, 10); // XMind
//            put(1409483181429755904L, 2); // Xftp
//            put(1415270040378413056L, 1); // 墨刀
//            put(1413329623260991488L, 1); // dbeaver
//            put(1415269264725774336L, 19); // Photoshop
//            put(1413328446360260608L, 3); // 阿里云盘
//            put(1414416608402739200L, 33); // 视频会议
//            put(1408368472097951744L, 11); // TeamViewer
//            put(1408017039897006083L, 2); // Docker
//            put(1413326985282850816L, 3); // 腾讯会议
//            put(1409830145539313664L, 1); // Another Redis Desktop Manager
        }};
        BatchReportSoftwareUseTimeDto useTimeDto = constructBatchSoftwareUseTimeDto(params);
        useTimeService.batchReportSoftwareUseTime(useTimeDto);
    }

    @Test
    public void testWeekStatistic() {
        BaseResult<WeekStatisticDo> result = useTimeService.weekStatistic();
        log.info("date list: {}", result.getData().getDateList().toArray());
        for (WeekStatisticUseTimeDo useTimeDo : result.getData().getUseTimeDoList()) {
            log.info("{} use time: {}.", useTimeDo.getSoftwareName(), useTimeDo.getUseTimeList().toArray());
        }
    }

    private BatchReportSoftwareUseTimeDto constructBatchSoftwareUseTimeDto(Map<Long, Integer> params) {
        String deviceType = SoftwareDeviceTypeEnum.PC.getKey();
        List<BatchReportSoftwareUseTimeDetailDto> detailDtoList = new ArrayList<>(params.keySet().size());
        params.forEach((k, v) -> {
            BatchReportSoftwareUseTimeDetailDto detailDto = new BatchReportSoftwareUseTimeDetailDto();
            detailDto.setSoftwareId(k);
            detailDto.setUseTime(v);
            detailDtoList.add(detailDto);
        });

        BatchReportSoftwareUseTimeDto useTimeDto = new BatchReportSoftwareUseTimeDto();
        useTimeDto.setDeviceType(deviceType);
        useTimeDto.setUseTimeDetailDtoList(detailDtoList);
        return useTimeDto;
    }

}
