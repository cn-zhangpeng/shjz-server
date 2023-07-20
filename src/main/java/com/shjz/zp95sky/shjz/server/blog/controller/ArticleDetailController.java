package com.shjz.zp95sky.shjz.server.blog.controller;

import com.shjz.zp95sky.shjz.server.blog.biz.ArticleDetailBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.ChangeOriginalDto;
import com.shjz.zp95sky.shjz.server.blog.dto.GetArticleListByKeywordDto;
import com.shjz.zp95sky.shjz.server.blog.dto.PublishArticleDto;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
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
@Api(value = "文章", tags = "文章相关接口")
@RestController
@RequestMapping(value = "/blog")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleDetailController {

    private final ArticleDetailBiz articleDetailBiz;

    @ApiOperation("查询文章列表")
    @GetMapping("/article/list")
    public BaseResult<List<ArticleListDo>> getArticleList(@ModelAttribute CustomPage customPage) {
        return articleDetailBiz.getArticleList(customPage);
    }

    @ApiOperation("根据类型查询文章列表")
    @ApiImplicitParam(name = "categoryId", value = "文章类型ID", required = true, paramType = "path", dataTypeClass = String.class)
    @GetMapping(value = "/category/{categoryId}/article/list")
    public BaseResult<List<ArticleListDo>> getArticleListByCategory(@PathVariable Long categoryId, @ModelAttribute CustomPage customPage) {
        return articleDetailBiz.getArticleListByCategory(categoryId, customPage);
    }

    @ApiOperation("查询文章详情")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "path", dataTypeClass = String.class)
    @GetMapping(value = "/article/{articleId}")
    public BaseResult<ArticleDetailDo> getArticleDetail(@PathVariable Long articleId) {
        return articleDetailBiz.getArticleById(articleId);
    }

    @ApiOperation("查询最新文章列表")
    @GetMapping(value = "/article/latest")
    public BaseResult<List<ArticleListDo>> getLatestArticleList(@ModelAttribute CustomPage customPage) {
        return articleDetailBiz.getArticleList(customPage);
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "path", dataTypeClass = String.class)
    @DeleteMapping(value = "/article/{articleId}")
    public BaseResult<Void> deleteArticle(@PathVariable Long articleId) {
        boolean result = articleDetailBiz.deleteArticle(articleId);
        return result ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @ApiOperation("发布文章")
    @PostMapping("/article")
    public BaseResult<Void> publishArticle(@RequestBody PublishArticleDto articleDto) {
        boolean result = articleDetailBiz.publishArticle(articleDto);
        return result ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @ApiOperation("搜索文章（目前只支持标题模糊匹配）")
    @GetMapping("/article/search")
    public BaseResult<List<ArticleListDo>> getArticleListByKeyword(@ModelAttribute GetArticleListByKeywordDto keywordDto) {
        return articleDetailBiz.getArticleListByKeyword(keywordDto);
    }

    @ApiOperation("修改文章原创标识")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "path", dataTypeClass = String.class)
    @PatchMapping("/article/{articleId}/changeOriginal")
    public BaseResult<Void> changeOriginal(@PathVariable Long articleId, @RequestBody ChangeOriginalDto originalDto) {
        return articleDetailBiz.changeOriginal(articleId, originalDto.getIsOriginal());
    }

}
