package com.shjz.zp95sky.shjz.server.common.response;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;

/**
 * 返回工具类
 * @author 山海紫穹
 */
public class ResultUtil {

    private ResultUtil() {
    }

    public static <T> BaseResult<T> buildResultSuccess(BasePageData pageData, T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setPageData(pageData);
        result.setData(data);
        ResponseCodeEnum response = ResponseCodeEnum.SUCCESS;
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        return result;
    }

    public static <T> BaseResult<T> buildResultSuccess(Integer page, Integer pageSize, Long total, T data) {
        BasePageData pageData = BasePageData.builder()
                .page(page).pageSize(pageSize).total(total)
                .build();
        return buildResultSuccess(pageData, data);
    }

    public static <T> BaseResult<T> buildResultSuccess(T data) {
        return buildResultSuccess(null, data);
    }

    public static <T> BaseResult<T> buildResultSuccess() {
        return buildResultSuccess(null);
    }

    public static <T> BaseResult<T> buildResultError(ResponseCodeEnum codeEnum) {
        BaseResult<T> result = new BaseResult<>();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        return result;
    }

    public static BaseResult<Void> buildGeneralResultError() {
        return buildResultError(ResponseCodeEnum.ERROR_SYSTEM_ERROR);
    }

}
