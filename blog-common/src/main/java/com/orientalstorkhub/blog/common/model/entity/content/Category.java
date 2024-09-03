package com.orientalstorkhub.blog.common.model.entity.content;

import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("categories")
public class Category {

    
    private Integer id;

    private String name;

    private Integer createdBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer status;




}
