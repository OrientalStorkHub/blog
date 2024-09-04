package com.orientalstorkhub.blog.common.pojo.dto.content;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "分类插入")
public class InsertCategoryDTO {

    @Schema(description = "分类名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

}
