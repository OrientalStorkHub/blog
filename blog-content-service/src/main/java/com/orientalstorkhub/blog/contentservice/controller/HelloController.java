package com.orientalstorkhub.blog.contentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orientalstorkhub.blog.common.context.UserContext;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    Integer userid  = UserContext.getUserId();
    System.out.println("userid is " + userid);
    return "hello content service";
  }
}

