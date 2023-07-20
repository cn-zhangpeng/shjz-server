package com.shjz.zp95sky.shjz.server.common.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期处理工具类
 * @author 山海紫穹
 */
public class CustomLocalDateTimeUtil extends cn.hutool.core.date.LocalDateTimeUtil {

    private CustomLocalDateTimeUtil() { }

    /**
     * 查询指定日期所在年的第一天日期
     * @param localDate 指定日期
     * @return 所在年的第一天日期
     */
    public static LocalDate getFirstDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 查询今年的第一天日期
     * @return 今年的第一天日期
     */
    public static LocalDate getFirstDayOfCurrentYear() {
        LocalDate localDate = LocalDate.now();
        return getFirstDayOfYear(localDate);
    }

    /**
     * 查询指定日期所在年的最后一天日期
     * @param localDate 指定日期
     * @return 所在年的最后一天日期
     */
    public static LocalDate getLastDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 查询今年的最后一天日期
     * @return 今年的最后一天日期
     */
    public static LocalDate getLastDayOfCurrentYear() {
        LocalDate localDate = LocalDate.now();
        return getLastDayOfYear(localDate);
    }

}
