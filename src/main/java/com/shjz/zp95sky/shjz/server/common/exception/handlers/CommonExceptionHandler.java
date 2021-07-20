package com.shjz.zp95sky.shjz.server.common.exception.handlers;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 * @author 山海紫穹
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult<Void> resolveException(HttpServletRequest request, Exception e) {
        String requestUri = request.getRequestURI();
        log.error(" >>>>>> " + requestUri + " error !", e);
        return ResultUtil.buildGeneralResultError();
    }

}
