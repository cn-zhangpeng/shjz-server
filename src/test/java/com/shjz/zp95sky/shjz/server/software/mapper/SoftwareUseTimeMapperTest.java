package com.shjz.zp95sky.shjz.server.software.mapper;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.common.utils.CustomLocalDateTimeUtil;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTimeStatistic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 软件使用时长 mapper 测试
 * @author 山海紫穹
 * @date 2021年07月05日 14:17
 */
@CommonTestAnnotation
@Slf4j
public class SoftwareUseTimeMapperTest {

    @Resource
    private SoftwareUseTimeMapper useTimeMapper;

    @Test
    public void testSelectYearDateStatistic() {
        int curYear = LocalDate.now().getYear();
        List<YearDateStatisticDo> statisticDoList = useTimeMapper.selectYearDateStatistic(curYear);
        for (YearDateStatisticDo s : statisticDoList) {
            log.info("date: {}, software use time: {}", s.getCurDate(), s.getUseTimeLength());
        }
    }

    @Test
    public void testSelectYearStatistic() {
        LocalDate startDate = CustomLocalDateTimeUtil.getFirstDayOfCurrentYear();
        LocalDate endDate = CustomLocalDateTimeUtil.getLastDayOfCurrentYear();

        List<SoftwareUseTimeStatistic> statisticData = useTimeMapper.selectYearStatistic(startDate, endDate);
        for (SoftwareUseTimeStatistic s : statisticData) {
            log.info("software id: {}, use time length: {}", s.getSoftwareId(), s.getUseTimeLength());
        }
    }

}
