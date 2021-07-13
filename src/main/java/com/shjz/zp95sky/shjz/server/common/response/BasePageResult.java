package com.shjz.zp95sky.shjz.server.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页通用返回
 * @author 华夏紫穹
 */
@ApiModel(value = "BasePageResult<T> 分页请求通用相应信息", description = "分页请求通用相应信息")
@Data
public class BasePageResult<T> {

	private BasePageResult() { }

	@ApiModelProperty("当前页")
	private Integer page;

	@ApiModelProperty("每页条数")
	private Integer size;

	@ApiModelProperty("数据总数")
	private Long total;

	@ApiModelProperty(value = "相应数据", dataType = "array")
	private List<T> data;

	public static <T> BasePageResult<T> getInstance(Integer page, Integer pageSize, Long total, List<T> dataList) {
		BasePageResult<T> pageResult = new BasePageResult<>();
		pageResult.setPage(page);
		pageResult.setSize(pageSize);
		pageResult.setTotal(total);
		pageResult.setData(dataList);
		return pageResult;
	}

}
