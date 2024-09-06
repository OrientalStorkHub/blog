package com.orientalstorkhub.blog.common.pojo.dto.content;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "标签删除")
public class DeleteTagDTO {
    @Schema(description = "删除标签id列表",requiredMode = REQUIRED )
    private List<Long> tagIds;
}
