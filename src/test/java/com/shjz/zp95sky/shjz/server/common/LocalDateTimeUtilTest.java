package com.shjz.zp95sky.shjz.server.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 山海紫穹
 * @date 2021年07月21日 11:31
 */
@Slf4j
public class LocalDateTimeUtilTest {

    @Test
    public void test() {
        LocalDate localDate = LocalDate.now();
        LocalDate firstDay = localDate.with(TemporalAdjusters.firstDayOfYear());
        log.info("year first day: {}", firstDay);

        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfYear());
        log.info("year last day: {}", lastDay);
    }

}
