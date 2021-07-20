package com.shjz.zp95sky.shjz.server.software.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticDo;
import com.shjz.zp95sky.shjz.server.software.domain.WeekStatisticUseTimeDo;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDetailDto;
import com.shjz.zp95sky.shjz.server.software.dto.BatchReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.dto.ReportSoftwareUseTimeDto;
import com.shjz.zp95sky.shjz.server.software.entity.Software;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTime;
import com.shjz.zp95sky.shjz.server.software.enums.SoftwareDeviceTypeEnum;
import com.shjz.zp95sky.shjz.server.software.mapper.SoftwareUseTimeMapper;
import com.shjz.zp95sky.shjz.server.software.service.SoftwareService;
import com.shjz.zp95sky.shjz.server.software.service.SoftwareUseTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 山海紫穹
 * @date 2021年06月23日 14:09
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class SoftwareUseTimeServiceImpl extends ServiceImpl<SoftwareUseTimeMapper, SoftwareUseTime>
        implements SoftwareUseTimeService {

    private final Snowflake snowflake;

    private final SoftwareService softwareService;

    private final SoftwareUseTimeMapper useTimeMapper;

    @Override
    public void reportSoftwareUseTime(ReportSoftwareUseTimeDto useTimeDto) {
        SoftwareDeviceTypeEnum deviceTypeEnum = SoftwareDeviceTypeEnum.getByKey(useTimeDto.getDeviceType());
        SoftwareUseTime softwareUseTime = constructSoftwareUseTime(useTimeDto.getSoftwareId(),
                deviceTypeEnum, useTimeDto.getUseTime());
        save(softwareUseTime);
    }

    @Override
    public void batchReportSoftwareUseTime(BatchReportSoftwareUseTimeDto userTimeDto) {
        List<BatchReportSoftwareUseTimeDetailDto> useTimeDtoList = userTimeDto.getUseTimeDetailDtoList();
        List<SoftwareUseTime> softwareUseTimeList = new ArrayList<>(useTimeDtoList.size());
        for (BatchReportSoftwareUseTimeDetailDto utDto : useTimeDtoList) {
            SoftwareDeviceTypeEnum dtEnum = SoftwareDeviceTypeEnum.getByKey(userTimeDto.getDeviceType());
            softwareUseTimeList.add(constructSoftwareUseTime(utDto.getSoftwareId(), dtEnum, utDto.getUseTime()));
        }
        saveBatch(softwareUseTimeList);
    }

    @Override
    public WeekStatisticDo weekStatistic() {
        // 查询的开始和结束日期
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.plusDays(-6L);

        // 查询一周之内软件的时间使用记录
        List<SoftwareUseTime> softwareUseTimeList = selectUseTimeByDate(startDate, endDate);

        // 统计各软件一周的使用总量，key：软件ID，value：一周使用总时长
        Map<Long, Integer> totalMap = new HashMap<>();
        // 统计各软件每天的使用量，key：软件ID:日期，value，使用时长
        Map<String, Integer> dateTotalMap = new HashMap<>();
        statisticWeekMapData(softwareUseTimeList, totalMap, dateTotalMap);

        // 使用时长最长的5个软件ID
        List<Long> topIds = searchTopFiveSoftwareId(totalMap);
        log.info("use time length top five: {}", topIds);

        // 查询对应的软件信息
        List<Software> softwareList = softwareService.listByIds(topIds);
        Map<Long, String> softwareMap = softwareList.stream()
                .collect(Collectors.toMap(Software::getId, Software::getSoftwareName));

        // 组装返回结果
        return constructWeekStatisticDo(topIds, dateTotalMap, softwareMap, startDate, endDate);
    }

    @Override
    public List<YearDateStatisticDo> yearDateStatistic() {
        int curYear = LocalDate.now().getYear();

        // 查询指定年份的每一天的软件使用时长
        return useTimeMapper.selectYearDateStatistic(curYear);
    }

    private WeekStatisticDo constructWeekStatisticDo(List<Long> topIds, Map<String, Integer> dateTotalMap,
                                                     Map<Long, String> softwareMap, LocalDate startDate, LocalDate endDate) {
        // 组装日期
        List<LocalDate> dateList = new ArrayList<>();
        for (LocalDate cur = startDate; cur.isBefore(endDate.plusDays(1)); cur = cur.plusDays(1)) {
            dateList.add(cur);
        }

        // 组装数据
        List<WeekStatisticUseTimeDo> useTimeDoList = new ArrayList<>();
        List<String> softwareNameList = new ArrayList<>();
        WeekStatisticUseTimeDo useTimeDo;
        for (Long id : topIds) {
            String softwareName = softwareMap.get(id);
            softwareNameList.add(softwareName);

            useTimeDo = new WeekStatisticUseTimeDo();
            useTimeDo.setSoftwareName(softwareName);
            List<Integer> useTimes = new ArrayList<>();
            for (LocalDate cur = startDate; cur.isBefore(endDate.plusDays(1)); cur = cur.plusDays(1)) {
                Integer useTime = dateTotalMap.get(id + ":" + cur);
                useTimes.add(ObjectUtils.isEmpty(useTime) ? Integer.valueOf(0) : useTime);
            }
            useTimeDo.setUseTimeList(useTimes);

            useTimeDoList.add(useTimeDo);
        }

        return WeekStatisticDo.builder()
                .dateList(dateList).nameList(softwareNameList)
                .useTimeDoList(useTimeDoList)
                .build();
    }

    /**
     * 对一周的数据进行一个汇总统计
     * @param softwareUseTimeList 软件使用记录
     * @param totalMap 各软件一周使用量，key：软件ID，value：一周使用总时长
     * @param dateTotalMap 各软件每天使用量，key：软件ID:日期，value，使用时长
     */
    private void statisticWeekMapData(List<SoftwareUseTime> softwareUseTimeList, Map<Long, Integer> totalMap,
                                      Map<String, Integer> dateTotalMap) {
        for (SoftwareUseTime sut : softwareUseTimeList) {
            Long softwareId = sut.getSoftwareId();
            LocalDate curDate = sut.getCurrentDate();
            Integer useTimeLength = sut.getUseTimeLength();

            // 总量统计
            if (totalMap.containsKey(softwareId)) {
                totalMap.put(softwareId, totalMap.get(softwareId) + useTimeLength);
            } else {
                totalMap.put(softwareId, useTimeLength);
            }

            // 每天使用量的统计
            dateTotalMap.put(softwareId + ":" + curDate, useTimeLength);
        }
    }

    /**
     * 查找使用时间最长的前五的软件ID，如果不够五个，返回对应个数。
     * @param totalMap 各软件一周使用量，key：软件ID，value：一周使用总时长
     * @return 排名前五的软件ID
     */
    private List<Long> searchTopFiveSoftwareId(Map<Long, Integer> totalMap) {

        // 假如总数小于5，直接返回
        if (totalMap.size() <= 5) {
            return new ArrayList<>(totalMap.keySet());
        }

        return totalMap.entrySet().stream()
                .sorted((Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) -> o2.getValue() - o1.getValue())
                .map(Map.Entry::getKey).collect(Collectors.toList()).subList(0, 5);
    }

    private List<SoftwareUseTime> selectUseTimeByDate(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<SoftwareUseTime> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(SoftwareUseTime::getCurrentDate, startDate, endDate);
        return list(queryWrapper);
    }

    private SoftwareUseTime constructSoftwareUseTime(Long softwareId, SoftwareDeviceTypeEnum deviceTypeEnum,
                                                     Integer useTime) {
        String deviceType = ObjectUtils.isEmpty(deviceTypeEnum) ? "" : deviceTypeEnum.getKey();
        return SoftwareUseTime.builder()
                .id(snowflake.nextId()).softwareId(softwareId).deviceType(deviceType)
                .currentDate(LocalDate.now()).useTimeLength(useTime)
                .build();
    }

}
