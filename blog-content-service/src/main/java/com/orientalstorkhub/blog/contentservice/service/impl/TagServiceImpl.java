package com.orientalstorkhub.blog.contentservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.constants.TagStatusEnum;
import com.orientalstorkhub.blog.common.context.UserContext;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.exception.DAEOException;
import com.orientalstorkhub.blog.common.pojo.dto.content.QueryTagDTO;
import com.orientalstorkhub.blog.common.pojo.entity.content.Tag;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;
import com.orientalstorkhub.blog.contentservice.repository.TagMapper;
import com.orientalstorkhub.blog.contentservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.orientalstorkhub.blog.common.constants.TagStatusEnum.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void insertBatchTags(List<String> tagNames) {
        // 检查标签名是否重复
        HashSet<String> uniqueTagNames = new HashSet<>(tagNames);
        if (uniqueTagNames.size() < tagNames.size()) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_NAME_REPEAT);
        }
        // 检查数据库是否存在部分标签名
        Integer userId = UserContext.getUserId();
        int count = tagMapper.existsCountOfBatchInsert(tagNames, userId, ACTIVE.getStatus());
        if (count > 0) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_NAME_EXISTS);
        }
        // 批量插入标签
        List<Tag> tagList = tagNames.stream().map(name -> Tag.builder().name(name).createdBy(userId)
                .status(ACTIVE.getStatus()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
                .build()).collect(Collectors.toList());
        try {
            int result = tagMapper.insert(tagList).size();
            if (result < 1) {
                throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_INSERT_TAG_FAILED);
            }
        } catch (Exception e) {
            throw new DAEOException(ErrorCode.DATABASE_OPERATION_EXCEPTION);
        }

    }

    @Override
    public void deleteBatchTags(List<Long> tagIds) {
        List<Tag> tags = tagIds.stream().map(tagId -> Tag.builder().id(tagId)
                .updatedAt(LocalDateTime.now())
                .status(TagStatusEnum.INACTIVE.getStatus()).build())
                .collect(Collectors.toList());
        try {
            int result = tagMapper.updateById(tags).size();
            if (result < tagIds.size()) {
                throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_DELETE_FAILED);
            }
        } catch (Exception e) {
            throw new DAEOException(ErrorCode.DATABASE_OPERATION_EXCEPTION);
        }

    }

    @Override
    public void updateTags(Long id, String tagName) {
        // 检查标签名是否重复
        Integer userId = UserContext.getUserId();
        Tag tag = Tag.builder().name(tagName)
                .createdBy(userId)
                .status(TagStatusEnum.ACTIVE.getStatus())
                .build();
        long count = tagMapper.selectCount(new QueryWrapper<>(tag));
        if (count > 0) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_NAME_EXISTS);
        }
        // 更新标签
        tag.setId(id);
        tag.setUpdatedAt(LocalDateTime.now());
        try {
            int result = tagMapper.updateById(tag);
            if (result < 1) {
                throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_DELETE_FAILED);
            }
        } catch (Exception e) {
            throw new DAEOException(ErrorCode.DATABASE_OPERATION_EXCEPTION);
        }
    }

    @Override
    public PageResponseVO<Tag> selectTagPage(QueryTagDTO dto) {
        // 创建查询条件
        Integer userId = UserContext.getUserId();
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("created_by", userId).eq("status", TagStatusEnum.ACTIVE.getStatus())
                .orderByDesc("created_at");
        String keyWord = dto.getKeyword();
        if (keyWord != null && !keyWord.trim().isEmpty()) {
            queryWrapper.like("name", keyWord);
        }
        // 分页查询
        IPage<Tag> pageObject = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        IPage<Tag> resultPage = tagMapper.selectPage(pageObject, queryWrapper);
        long count = resultPage.getTotal();
        long totalPages = resultPage.getPages();

        // 构建分页响应对象
        PageResponseVO<Tag> pageResponseVO = PageResponseVO.<Tag>builder()
                .currentPage(dto.getCurrentPage())
                .totalPages(totalPages)
                .totalRecords(count)
                .pageSize(dto.getPageSize())
                .data(pageObject.getRecords())
                .build();

        return pageResponseVO;
    }

    @Override
    public void insertTag(String tagName) {
        // 检查数据库是否存在部分标签名
        Integer userId = UserContext.getUserId();
        Tag tag = Tag.builder().name(tagName).createdBy(userId).status(TagStatusEnum.ACTIVE.getStatus()).build();
        long count = tagMapper.selectCount(new QueryWrapper<>(tag));
        if (count > 0) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_TAG_NAME_EXISTS);
        }
        tag.setCreatedAt(LocalDateTime.now());
        tag.setUpdatedAt(LocalDateTime.now());
        try {
            int result = tagMapper.insert(tag);
            if (result < 1) {
                throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_INSERT_TAG_FAILED);
            }
        } catch (Exception e) {
            throw new DAEOException(ErrorCode.DATABASE_OPERATION_EXCEPTION);
        }
    }

    // TODO 构造简单日志服务
}
