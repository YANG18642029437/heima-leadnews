package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApShowBehaviorMapper {

    @Select("<script>" +
            " select * from ap_show_behavior\n" +
            "        <where>\n" +
            "            entry_id = #{id}\n" +
            "            <foreach collection=\"ids\" item=\"item\" open=\" and article_id in ( \" close=\")\" separator=\",\">\n" +
            "                #{item}\n" +
            "            </foreach>\n" +
            "        </where>" +
            "</script>")
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("id") Integer id, @Param("ids") Integer[] articleIds);

    @Select("<script>" +
            "insert  into ap_show_behavior (entry_id, article_id,is_click, show_time,created_time) values\n" +
            "        <foreach collection=\"ids\" item=\"item\" >\n" +
            "            (#{id},#{item},0, now(),now())\n" +
            "        </foreach>" +
            "</script>")
    void saveShowBehavior(@Param("ids") Integer[] articleIds, @Param("id") Integer id);
}
