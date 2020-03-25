package com.heima.article.service;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeService {
    ResponseResult load(ArticleHomeDto dto, Short loadtypeLoadMore);
}
