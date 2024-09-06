package com.orientalstorkhub.blog.common.pojo.dto.content;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "标签查询")
public class QueryTagDTO {
    @Schema(description = "当前页",requiredMode = REQUIRED )
    private Integer currentPage;

    @Schema(description = "每页显示记录数",requiredMode = REQUIRED )
    private Integer pageSize;

    // 模糊查询关键词（如标签名称）
    @Schema(description = "关键字",requiredMode = NOT_REQUIRED )
    private String keyword;
}
