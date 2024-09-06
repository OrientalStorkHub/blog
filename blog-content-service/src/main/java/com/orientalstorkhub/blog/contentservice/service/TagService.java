package com.orientalstorkhub.blog.contentservice.service;

import com.orientalstorkhub.blog.common.pojo.dto.content.QueryTagDTO;
import com.orientalstorkhub.blog.common.pojo.entity.content.Tag;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;

import java.util.List;

public interface TagService {

    void insertBatchTags(List<String> tagNames);

    void insertTag(String tagName);

    void deleteBatchTags(List<Long> tagIds);

    void updateTags(Long id, String tagName);

    PageResponseVO<Tag> selectTagPage(QueryTagDTO dto);
}
