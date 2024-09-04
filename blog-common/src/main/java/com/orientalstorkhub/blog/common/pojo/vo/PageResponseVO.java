package com.orientalstorkhub.blog.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 分页响应对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "分页响应对象")
public class PageResponseVO<T> {

    @Schema(description = "当前页的数据列表")
    private List<T> data;

    @Schema(description = "当前页码")
    private int currentPage;

    @Schema(description = "总页数")
    private long totalPages;

    @Schema(description = "总记录数")
    private long totalRecords;

    @Schema(description = "每页记录数")
    private int pageSize;
}
