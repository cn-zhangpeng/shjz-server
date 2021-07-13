package com.shjz.zp95sky.shjz.server.blog.controller;

import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.GetArticleListByKeywordDto;
import com.shjz.zp95sky.shjz.server.blog.dto.PublishArticleDto;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailService;
import com.shjz.zp95sky.shjz.server.common.response.BasePageResult;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ModelResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华夏紫穹
 */
@Api(value = "文章", tags = "文章相关接口")
@RestController
@RequestMapping(value = "/blog")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleDetailController {

    private final ArticleDetailService articleDetailService;

    @ApiOperation("查询文章列表")
    @GetMapping("/articles")
    public BaseResult<BasePageResult<ArticleListDo>> getArticleList(@ModelAttribute CustomPage customPage) {
        BasePageResult<ArticleListDo> pageResult = articleDetailService.getArticleList(customPage);
        return ModelResultUtil.buildPageResultSuccess(pageResult);
    }

    @ApiOperation("根据类型查询文章列表")
    @ApiImplicitParam(name = "categoryId", value = "文章类型ID", required = true, paramType = "path", dataTypeClass = String.class)
    @GetMapping(value = "/categories/{categoryId}/articles")
    public BaseResult<BasePageResult<ArticleListDo>> getArticleListByCategory(@PathVariable Long categoryId, @ModelAttribute CustomPage customPage) {
        BasePageResult<ArticleListDo> pageResult = articleDetailService.getArticleListByCategory(categoryId, customPage);
        return ModelResultUtil.buildPageResultSuccess(pageResult);
    }

    @ApiOperation("查询文章详情")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "path", dataTypeClass = String.class)
    @GetMapping(value = "/articles/{articleId}")
    public BaseResult<ArticleDetailDo> getArticleDetail(@PathVariable Long articleId) {
        ArticleDetailDo articleDetailDo = articleDetailService.getArticleById(articleId);
        return ModelResultUtil.buildResultSuccess(articleDetailDo);
    }

    @ApiOperation("查询最新文章列表")
    @GetMapping(value = "/articles/latest")
    public BaseResult<BasePageResult<ArticleListDo>> getLatestArticleList(@ModelAttribute CustomPage customPage) {
        BasePageResult<ArticleListDo> pageResult = articleDetailService.getArticleList(customPage);
        return ModelResultUtil.buildPageResultSuccess(pageResult);
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "path", dataTypeClass = String.class)
    @DeleteMapping(value = "/articles/{articleId}")
    public BaseResult<Void> deleteArticle(@PathVariable Long articleId) {
        boolean result = articleDetailService.deleteArticle(articleId);
        return result ? ModelResultUtil.buildGeneralResultSuccess() : ModelResultUtil.buildGeneralResultError();
    }

    @ApiOperation("发布文章")
    @PostMapping("/articles")
    public BaseResult<Void> publishArticle(@RequestBody PublishArticleDto articleDto) {
        boolean result = articleDetailService.publishArticle(articleDto);
        return result ? ModelResultUtil.buildGeneralResultSuccess() : ModelResultUtil.buildGeneralResultError();
    }

    /**
     ```sequence
     participant 客户端
     participant Server
     participant ES
     participant MySQL

     客户端 -> Server: 搜索文章
     Server -> ES: 根据关键字查询文章
     ES -> Server: 查询到的文章ID
     Server -> MySQL: 根据ID查询文章详情
     MySQL -> Server: 返回文章信息
     Server -> 客户端: 响应客户端
     ```
     */
    @ApiOperation("搜索文章")
    @GetMapping("/articles/search")
    public BaseResult<BasePageResult<ArticleListDo>> getArticleListByKeyword(@ModelAttribute GetArticleListByKeywordDto keywordDto) {
        BasePageResult<ArticleListDo> pageResult = articleDetailService.getArticleListByKeyword(keywordDto);
        return ModelResultUtil.buildPageResultSuccess(pageResult);
    }

}
