package com.shjz.zp95sky.shjz.server.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shjz.zp95sky.shjz.server.software.domain.YearDateStatisticDo;
import com.shjz.zp95sky.shjz.server.software.entity.SoftwareUseTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 山海紫穹
 * @date 2021年06月24日 13:50
 */
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

}
