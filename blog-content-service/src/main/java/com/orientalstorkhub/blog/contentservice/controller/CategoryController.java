package com.orientalstorkhub.blog.contentservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.orientalstorkhub.blog.common.responses.BaseResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/insert")
    public BaseResponse<Object> insertCategory(@RequestBody String categoryName){
        // Implementation for inserting a category
        return null;
        
    }

}
