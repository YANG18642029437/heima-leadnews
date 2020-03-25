package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApUserArticleListMapper {

    @Select("<script>" +
            "select * from ap_user_article_list\n" +
            "        <where>\n" +
            "            user_id = #{user.id} and is_show = 0 and is_read = 0\n" +
            "            <if test=\"type != null and type == 1\">\n" +
            "                and recommend_time <![CDATA[<]]>  #{dto.minBehotTime}\n" +
            "            </if>\n" +
            "            <if test=\"type != null and type == 2\">\n" +
            "                and recommend_time <![CDATA[>]]>  #{dto.maxBehotTime}\n" +
            "            </if>\n" +
            "\n" +
            "            <if test=\"dto.tag != '__all__'\">\n" +
            "               and channel_id = #{dto.tag}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        limit #{dto.size}" +
            "</script>")
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user, @Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}
