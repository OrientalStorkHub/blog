package com.orientalstorkhub.blog.common.pojo.entity.content;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("categories")
public class Category {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer createdBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer status;


}
