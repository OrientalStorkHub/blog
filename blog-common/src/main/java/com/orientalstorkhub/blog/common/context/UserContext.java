package com.orientalstorkhub.blog.common.context;

public class UserContext {
    private static final ThreadLocal<Integer> userId = new ThreadLocal<>();

    public static void setUserId(Integer id){
        userId.set(id);
    }
    
    public static Integer getUserId(){
        return userId.get();
    }

    public static void clear(){
        userId.remove();
    }
}
