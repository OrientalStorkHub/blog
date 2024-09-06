package com.orientalstorkhub.blog.contentservice.controller;


import static com.orientalstorkhub.blog.common.constants.ResponseSuccessMessageEnum.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.pojo.dto.content.InsertTagDTO;
import com.orientalstorkhub.blog.common.pojo.dto.content.QueryTagDTO;
import com.orientalstorkhub.blog.common.pojo.dto.content.UpdateTagDTO;
import com.orientalstorkhub.blog.common.pojo.dto.content.DeleteTagDTO;
import com.orientalstorkhub.blog.common.pojo.entity.content.Tag;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;
import com.orientalstorkhub.blog.common.responses.BaseResponse;
import com.orientalstorkhub.blog.common.utils.ResponseUtil;
import com.orientalstorkhub.blog.contentservice.service.TagService;

/**
* @description tag接口
* @author zhangj
* @since 2024-09-05 16:46:56
*/

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;


    @PostMapping("/batch/insert")
    public BaseResponse<Object> insertBatchTags(@RequestBody InsertTagDTO dto) {
        if (dto.getTagNames().isEmpty()) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_INSERT_TAG_NOT_EMPTY);
        }
        tagService.insertBatchTags(dto.getTagNames());
        return ResponseUtil.success(TAG_INSERT_SUCCESS);
    }

    @PostMapping("/insert")
    public BaseResponse<Object> insertTag(@RequestBody InsertTagDTO dto) {
        if (dto.getTagName() == null || dto.getTagName().isEmpty()) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_INSERT_TAG_NOT_EMPTY);
        }
        tagService.insertTag(dto.getTagName());
        return ResponseUtil.success(TAG_INSERT_SUCCESS);
    }


@PostMapping("/listPage")
public BaseResponse<PageResponseVO<Tag>> selectTagsPage(@RequestBody QueryTagDTO dto) {
    PageResponseVO<Tag> tagPageResponseVO = tagService.selectTagPage(dto);
    return ResponseUtil.success(TAG_SELECT_SUCCESS, tagPageResponseVO);
}

@PostMapping("/batch/delete")
public BaseResponse<Object> deleteBatchTags(@RequestBody DeleteTagDTO dto) {
    tagService.deleteBatchTags(dto.getTagIds());
    return ResponseUtil.success(TAG_DELETE_SUCCESS);
}

@PostMapping("/update")
public BaseResponse<Object> updateTag(@RequestBody UpdateTagDTO dto) {
    tagService.updateTags(dto.getTagId(), dto.getTagName());
    return ResponseUtil.success(TAG_UPDATE_SUCCESS);
}


}
