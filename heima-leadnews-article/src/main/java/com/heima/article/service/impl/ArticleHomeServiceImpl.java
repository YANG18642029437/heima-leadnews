package com.heima.article.service.impl;

import com.heima.article.service.ArticleHomeService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.nntp.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleHomeServiceImpl implements ArticleHomeService {

    private static final Integer MAX_SIZE = 50;

    @Resource
    private ApUserArticleListMapper apUserArticleListMapper;

    @Resource
    private ApArticleMapper apArticleMapper;

    /**
     * @param dto
     * @param type 1, 加载更多 2，加载最新
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        // 进行参数校验
        if (dto == null){
            dto = new ArticleHomeDto();
        }
        //进行时间校验
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        // 进行分页数量校验
        Integer size = dto.getSize();
        if (size == null || size <= 0) {
            size = 20;
        }
        size = Math.min(size, MAX_SIZE);

        dto.setSize(size);
        // 进行 频道校验
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }
        // 进行加载类型校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }
        // 获取用户
        ApUser user = AppThreadLocalUtils.getUser();
        List<ApArticle> apArticles;
        // 校验用户信息
        if (user != null){
            apArticles = getUserArticle(user,dto,type);
        }else {
            apArticles = getDefaultArticle(dto,type);
        }


        return ResponseResult.okResult(apArticles);
    }

    /**
     * 加载默认的文章信息
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }

    /**
     * 先从用户推荐表中查找文章信息，如果没有再从文章信息获取数据
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
        if (list.isEmpty()){
            return getDefaultArticle(dto,type);
        }else {
            return apArticleMapper.loadArticleListByIdList(list);
        }
    }
}
