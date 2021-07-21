package com.shjz.zp95sky.shjz.server.software.controller;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.software.domain.SoftwareListDo;
import com.shjz.zp95sky.shjz.server.software.service.SoftwareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 山海紫穹
 * @date 2021年07月01日 13:52
 */
@Api(tags = "软件管理")
@RestController
@RequestMapping(value = "/software")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class SoftwareController {

    private final SoftwareService softwareService;

    @ApiOperation("查询软件列表")
    @GetMapping("/list")
    public BaseResult<List<SoftwareListDo>> list() {
        return softwareService.getSoftwareList();
    }

}
