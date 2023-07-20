package com.shjz.zp95sky.shjz.server.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTime;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTimeStatistic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SoftwareUseTimeMapper extends BaseMapper<SoftwareUseTime> {

    /**
     * 查询指定年份每天的软件使用时长
     * @param searchYear 指定年份
     * @return 每天的软件使用时长
     */
    @Select("select cur_date as curDate, sum(use_time_length) as useTimeLength from software_use_time " +
            "where cur_date like concat('%', #{search_year}, '%') group by cur_date")
    List<YearDateStatisticDo> selectYearDateStatistic(@Param("search_year") Integer searchYear);

    /**
     * 查询指定日期范围软件使用统计
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 软件你使用统计
     */
    @Select("select software_id as SoftwareId, sum(use_time_length) as useTimeLength from software_use_time " +
            "where cur_date between #{start_date} and #{end_date} group by software_id")
    List<SoftwareUseTimeStatistic> selectYearStatistic(@Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate);

}
