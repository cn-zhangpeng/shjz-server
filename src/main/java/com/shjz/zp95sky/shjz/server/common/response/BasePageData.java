package com.shjz.zp95sky.shjz.server.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import liquibase.pro.packaged.T;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分页信息
 * @author 山海紫穹
 */
@ApiModel(value = "BasePageResult 分页信息", description = "分页信息")
@Data
@Builder
public class BasePageData {

	@ApiModelProperty("当前页")
	private Integer page;

	@ApiModelProperty("每页条数")
	private Integer pageSize;

	@ApiModelProperty("数据总数")
	private Long total;

}
