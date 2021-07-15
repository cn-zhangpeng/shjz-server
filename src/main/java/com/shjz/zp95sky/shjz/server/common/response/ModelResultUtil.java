package com.shjz.zp95sky.shjz.server.common.response;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;

import java.util.List;

/**
 * 返回工具类
 * @author 华夏紫穹
 */
public class ModelResultUtil {

    private ModelResultUtil() {
    }

    public static BaseResult<Void> buildGeneralResultSuccess() {
        return buildResultSuccess(null);
    }

    public static <T> BaseResult<T> buildResultSuccess(T data) {
        BaseResult<T> result = new BaseResult<>();
        ResponseCodeEnum response = ResponseCodeEnum.SUCCESS;
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> BaseResult<T> buildResultSuccess() {
        BaseResult<T> result = new BaseResult<>();
        ResponseCodeEnum response = ResponseCodeEnum.SUCCESS;
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        result.setData(null);
        return result;
    }

    public static <T> BaseResult<BasePageResult<T>> buildPageResultSuccess(Integer page, Integer pageSize, Long total, List<T> dataList) {
        BasePageResult<T> pageResult = BasePageResult.getInstance(page, pageSize, total, dataList);
        return buildResultSuccess(pageResult);
    }

    public static <T> BaseResult<BasePageResult<T>> buildPageResultSuccess(BasePageResult<T> pageResult) {
        return buildResultSuccess(pageResult);
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
