package com.orientalstorkhub.blog.contentservice.controller;

import com.orientalstorkhub.blog.common.pojo.dto.content.DeleteCategoryDTO;
import com.orientalstorkhub.blog.common.pojo.dto.content.InsertCategoryDTO;
import com.orientalstorkhub.blog.common.pojo.dto.content.UpdateCategoryDTO;
import com.orientalstorkhub.blog.common.pojo.entity.content.Category;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;
import com.orientalstorkhub.blog.common.pojo.dto.content.CategoryQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.orientalstorkhub.blog.common.context.UserContext;
import com.orientalstorkhub.blog.common.responses.BaseResponse;
import com.orientalstorkhub.blog.common.utils.ResponseUtil;
import com.orientalstorkhub.blog.contentservice.service.CategoryService;


import java.util.ArrayList;
import java.util.List;

import static com.orientalstorkhub.blog.common.constants.ResponseSuccessMessageEnum.*;

/**
 * @author zhangj
 * @description 文章分类接口
 * @since 2024-09-03 15:10:34
 */

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert")
    public BaseResponse<Object> insertCategory(@RequestBody InsertCategoryDTO dto) {
        Integer userId = UserContext.getUserId();
        categoryService.insertCategory(dto.getName(), userId);
        return ResponseUtil.success(CATEGORY_INSERT_SUCCESS);
    }

    @PostMapping("/listPage")
    public BaseResponse<PageResponseVO<Category>> selectCategoriesPage(@RequestBody CategoryQueryDTO dto) {
        PageResponseVO<Category> categoryPageResponseVO = categoryService.selectCategoriesPage(dto);
        return ResponseUtil.success(CATEGORY_SELECT_SUCCESS, categoryPageResponseVO);
    }


    @PostMapping("/batch/delete")
    public BaseResponse<Object> deleteCategoryById(@RequestBody DeleteCategoryDTO dto) {
        categoryService.deleteCategoryByIds(dto.getCategoryIds());
        return ResponseUtil.success(CATEGORY_DELETE_SUCCESS);
    }


    @PostMapping("/update")
    public BaseResponse<Object> updateCategory(@RequestBody UpdateCategoryDTO dto) {
        Integer userId = UserContext.getUserId();
        categoryService.updateCategory(dto.getCategoryId(), dto.getName(), userId);
        return ResponseUtil.success(CATEGORY_UPDATE_SUCCESS);
    }

}
