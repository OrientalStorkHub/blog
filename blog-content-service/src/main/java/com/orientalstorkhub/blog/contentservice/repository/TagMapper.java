package com.orientalstorkhub.blog.contentservice.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orientalstorkhub.blog.common.pojo.entity.content.Tag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询一批标签是否存在
     * 
     * @param names
     * @param userId
     * @param status
     * @return
     */
    @Select({
            "<script>",
            "SELECT COUNT(*)",
            "FROM tags",
            "WHERE name IN",
            "<foreach collection='names' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "AND created_by = #{userId}",
            "AND status = #{status}",
            "</script>"
    })
    int existsCountOfBatchInsert(@Param("names") List<String> names, @Param("userId") Integer userId,
            @Param("status") Short status);
}
