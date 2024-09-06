package com.orientalstorkhub.blog.common.pojo.dto.content;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "标签更新")
public class UpdateTagDTO {

    @Schema(description = "标签id",requiredMode = REQUIRED )
    private Long tagId;

    @Schema(description = "新的标签名",requiredMode = REQUIRED )
    private String tagName;
}


