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
@Schema(description = "分类更新")
public class UpdateCategoryDTO {

    @Schema(description = "分类id",requiredMode = REQUIRED )
    private Integer categoryId;

    @Schema(description = "新的分类名",requiredMode = REQUIRED )
    private String name;

}
