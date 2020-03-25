package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeControllerApi {
    /**
     * 加载首页文章
     * @param dto
     * @return
     */
    ResponseResult Load(ArticleHomeDto dto);

    /**
     * 加载更多
     * @param dto
     * @return
     */
    ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * 加载最新的文章
     * @param dto
     * @return
     */
    ResponseResult loadNew(ArticleHomeDto dto);
}
