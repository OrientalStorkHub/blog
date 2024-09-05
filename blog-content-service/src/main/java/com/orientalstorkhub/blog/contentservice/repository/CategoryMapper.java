package com.orientalstorkhub.blog.contentservice.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orientalstorkhub.blog.common.pojo.entity.content.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {


    /**
     * 批量删除分类
     * @param status
     * @param updatedAt
     * @param categoryIds
     * @return
     */
    @Update({
            "<script>",
            "UPDATE categories",
            "SET status = #{status},",
            "updated_at = #{updatedAt}",
            "WHERE id IN",
            "<foreach collection='categoryIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int updateCategoriesStatusByIds(
            @Param("status") Integer status,
            @Param("updatedAt") Timestamp updatedAt,
            @Param("categoryIds") List<Integer> categoryIds);


}
