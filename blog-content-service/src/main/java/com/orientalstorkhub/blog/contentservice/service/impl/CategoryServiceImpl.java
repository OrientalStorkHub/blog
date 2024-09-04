package com.orientalstorkhub.blog.contentservice.service.impl;

import java.time.Instant;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orientalstorkhub.blog.common.context.UserContext;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;
import com.orientalstorkhub.blog.common.pojo.dto.content.CategoryQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orientalstorkhub.blog.common.constants.CategoryStatusEnum;
import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.pojo.entity.content.Category;
import com.orientalstorkhub.blog.contentservice.repository.CategoryMapper;
import com.orientalstorkhub.blog.contentservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;



    @Override
    public void deleteCategoryByIds(List<Integer> categoryIds) {
        // 构建更新后的分类对象列表
        int result = categoryMapper.updateCategoriesStatusByIds(CategoryStatusEnum.INACTIVE.getStatus(),
                Timestamp.from(Instant.now()), categoryIds);
        if (result < categoryIds.size()) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_CATEGORY_DELETE_FAILED);
        }
    }

    @Override
    public void insertCategory(String categoryName, Integer userId) {
        //判断分类名是否存在
        Category category = Category.builder()
                .name(categoryName)
                .createdBy(userId)
                .status(CategoryStatusEnum.ACTIVE.getStatus())
                .build();
        long count = categoryMapper.selectCount(new QueryWrapper<>(category));
        if (count > 0){
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_CATEGORY_EXISTS);
        }
        //插入分类名
        category.setCreatedAt(Timestamp.from(Instant.now()));
        category.setUpdatedAt(Timestamp.from(Instant.now()));
        category.setStatus(CategoryStatusEnum.ACTIVE.getStatus());        
        int result = categoryMapper.insert(category);
        if (result < 1) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_CATEGORY_INSERT_FAILED);
        }
    }

    @Override
    public PageResponseVO<Category> selectCategoriesPage(CategoryQueryDTO dto) {
        //创建查询条件
        Integer userId = UserContext.getUserId();
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("created_by", userId).eq("status", CategoryStatusEnum.ACTIVE.getStatus())
                .orderByDesc("created_at");
        String keyWord = dto.getKeyword();
        if (keyWord != null && !keyWord.trim().isEmpty()){
            queryWrapper.like("name", keyWord);
        }
        // 分页查询
        IPage<Category> pageObject = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        categoryMapper.selectPage(pageObject, queryWrapper);
        long count = pageObject.getTotal();
        long totalPages = pageObject.getPages();

        // 构建分页响应对象
        PageResponseVO<Category> pageResponseVO = PageResponseVO.<Category>builder()
                .currentPage(dto.getCurrentPage())
                .totalPages(totalPages)
                .totalRecords(count)
                .pageSize(dto.getPageSize())
                .data(pageObject.getRecords())
                .build();

        return pageResponseVO;
    }


    @Override
    public void updateCategory(Integer id, String newName, Integer userId) {
        //判断新分类名是否重复
        Category category = Category.builder().createdBy(userId).name(newName).build();
        Long count = categoryMapper.selectCount(new QueryWrapper<>(category));
        if (count > 0) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_CATEGORY_EXISTS);
        }
        category.setName(newName);
        category.setUpdatedAt(Timestamp.from(Instant.now()));
        int result = categoryMapper.updateById(category);
        if (result < 1) {
            throw new BlogBaseException(ErrorCode.CONTENT_SERVICE_CATEGORY_UPDATE_FAILED);
        }
    }


}
