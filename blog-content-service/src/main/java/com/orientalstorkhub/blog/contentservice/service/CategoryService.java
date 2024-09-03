package com.orientalstorkhub.blog.contentservice.service;

import java.util.Optional;
import com.orientalstorkhub.blog.common.model.entity.content.Category;


public interface CategoryService {

    void insertCategory(String categoryName);

    void deleteCategory(Integer categoryId);

    Optional<Category> selectCategory(Integer userId);
}
