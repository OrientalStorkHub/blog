package com.orientalstorkhub.blog.contentservice.service;

import com.orientalstorkhub.blog.common.pojo.entity.content.Category;
import com.orientalstorkhub.blog.common.pojo.vo.PageResponseVO;
import com.orientalstorkhub.blog.common.pojo.dto.content.CategoryQueryDTO;

import java.util.List;


public interface CategoryService {

    /**
     * 描述：将新类别插入到数据库中
     *
     * @param categoryName 要插入的类别名称。
     * @param userId       创建类别的用户ID。
     * @return void
     */
    void insertCategory(String categoryName, Integer userId);


    /**
     * 根据分类ID删除分类
     *
     * @param categoryIds 分类IDs
     */
    void deleteCategoryByIds(List<Integer> categoryIds);


    /**
     * 查询分类列表
     *
     * @param vo 查询请求对象，包含用户ID、页码和每页大小等信息
     * @return 分类列表的分页响应对象
     */
    PageResponseVO<Category> selectCategoriesPage(CategoryQueryDTO vo);


    void updateCategory(Integer id, String newName, Integer userId);

}
