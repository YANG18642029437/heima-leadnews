package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApArticleMapper {

    @Select("<script>" +
            " select * from ap_article\n" +
            "        <where>\n" +
            "            <if test=\"dto.provinceId != null\"> and province_id = #{dto.provinceId} </if>\n" +
            "            <if test=\"dto.cityId != null\"> and city_id = #{dto.cityId}</if>\n" +
            "            <if test=\"dto.countyId != null\"> and count_id = #{dto.countyId}</if>\n" +
            "            <if test=\" type != null and type == 1\">\n" +
            "                 and publish_time <![CDATA[ < ]]> #{dto.minBehotTime}\n" +
            "            </if>\n" +
            "            <if test=\" type != null and type == 2 \">\n" +
            "                 and publish_time <![CDATA[ > ]]> #{dto.maxBehotTime}\n" +
            "            </if>\n" +
            "            <if test=\" dto.tag != '__all__' \">\n" +
            "                 and author_id = #{dto.tag}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "         limit  #{dto.size}" +
            "</script>")
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto,@Param("type") Short type);

    @Select("<script>" +
            "select * from ap_article\n" +
            "       where id in (\n" +
            "        <trim suffix=\"\" suffixOverrides=\",\">\n" +
            "            <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "                #{item.articleId}\n" +
            "            </foreach>\n" +
            "        </trim>\n" +
            "       )" +
            "</script>")
    List<ApArticle> loadArticleListByIdList(@Param("list") List<ApUserArticleList> list);
}
