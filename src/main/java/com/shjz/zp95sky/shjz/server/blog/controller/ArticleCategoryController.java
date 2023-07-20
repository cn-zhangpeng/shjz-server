package com.shjz.zp95sky.shjz.server.blog.controller;

import com.shjz.zp95sky.shjz.server.blog.biz.ArticleCategoryBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 山海紫穹
 */
@Api(value = "文章分类", tags = "文章分类接口")
@RestController
@RequestMapping(value = "/blog/category")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleCategoryController {

    private final ArticleCategoryBiz categoryBiz;

    @ApiOperation("查询所有文章类型")
    @GetMapping("/list")
    public BaseResult<List<ArticleCategoryListDo>> getCategoryList() {
        return categoryBiz.getCategoryList();
    }

    @ApiOperation("更新文章类型")
    @ApiImplicitParam(name = "categoryId", value = "文章类型ID", required = true, paramType = "path", dataTypeClass = String.class)
    @PutMapping("/{categoryId}")
    public BaseResult<Void> updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoryDto categoryDto) {
        return categoryBiz.updateCategory(categoryId, categoryDto);
    }

    @ApiImplicitParam(name = "categoryId", value = "文章类型ID", required = true, paramType = "path", dataTypeClass = String.class)
    @ApiOperation("删除文章类型")
    @DeleteMapping(value = "/{categoryId}")
    public BaseResult<Void> deleteCategory(@PathVariable Long categoryId) {
        return categoryBiz.deleteCategory(categoryId);
    }

    @ApiOperation("添加文章类型")
    @PostMapping
    public BaseResult<Void> addCategory(@RequestBody AddCategoryDto categoryDto) {
        return categoryBiz.addCategory(categoryDto);
    }

}
