package com.heima.article.controller.v1;

import com.heima.article.apis.ArticleHomeControllerApi;
import com.heima.article.service.ArticleHomeService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/v1/article")
public class ArticleHomeController implements ArticleHomeControllerApi {

    @Resource
    private ArticleHomeService articleHomeService;

    @Override
    public ResponseResult Load(ArticleHomeDto dto) {
        return articleHomeService.load(dto,ArticleConstans.LOADTYPE_LOAD_MORE);
    }

    @Override
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return articleHomeService.load(dto,ArticleConstans.LOADTYPE_LOAD_MORE);
    }

    @Override
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return articleHomeService.load(dto,ArticleConstans.LOADTYPE_LOAD_NEW);
    }
}
