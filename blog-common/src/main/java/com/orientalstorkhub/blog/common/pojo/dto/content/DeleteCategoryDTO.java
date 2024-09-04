package com.orientalstorkhub.blog.common.pojo.dto.content;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "分类删除")
public class DeleteCategoryDTO {
    @Schema(description = "删除分类id列表",requiredMode = REQUIRED )
    private List<Integer> categoryIds;
}
