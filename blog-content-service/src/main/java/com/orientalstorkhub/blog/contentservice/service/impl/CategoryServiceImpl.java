package com.orientalstorkhub.blog.contentservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.orientalstorkhub.blog.common.model.entity.content.Category;
import com.orientalstorkhub.blog.contentservice.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public void deleteCategory(Integer categoryId) {
        // Implementation for deleting a category
    }

    @Override
    public void insertCategory(String categoryName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertCategory'");
    }

    @Override
    public Optional<Category> selectCategory(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCategory'");
    }
    

}
