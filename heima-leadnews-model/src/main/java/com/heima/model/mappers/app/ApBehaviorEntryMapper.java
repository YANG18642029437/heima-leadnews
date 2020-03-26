package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ApBehaviorEntryMapper {
    /**
     * 通过 user id 或者 系统id 查询查询用户行为表
     * @param userId
     * @param equipmentId
     * @return
     */
    @Select("<script>" +
            "select * from ap_behavior_entry \n" +
            "        <where>\n" +
            "            <choose>\n" +
            "                <when test=\"userId != null\">\n" +
            "                    and entry_id = #{userId}\n" +
            "                </when>\n" +
            "                <otherwise>\n" +
            "                    and entry_id = #{equipmentId}\n" +
            "                </otherwise>\n" +
            "            </choose>\n" +
            "        </where>" +
            "</script>")
    ApBehaviorEntry selectByUserIdOrEquipemntId(@Param("userId") Long userId, @Param("equipmentId") Integer equipmentId);
}
