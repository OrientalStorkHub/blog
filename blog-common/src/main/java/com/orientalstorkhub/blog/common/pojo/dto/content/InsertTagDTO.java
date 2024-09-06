package com.orientalstorkhub.blog.common.pojo.dto.content;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.AUTO;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "标签插入")
public class InsertTagDTO {

    @Schema(description = "标签名，单个插入使用", requiredMode = AUTO)
    private String tagName;

    @Schema(description = "标签名列表，批量插入使用", requiredMode = AUTO)
    private List<String> tagNames;

}
