package com.demo.cloud.hystrixservice.userservice.services;


import com.demo.cloud.hystrixservice.userservice.dto.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    public int create(User user){
        return 0;
    }

    public int update(User user) {return 0;}

    public User getUser(Long id){
        return new User();
    }

    public List<User> getUserByIds(List<Long> ids) {
        return new ArrayList<>();
    }

    public User getByUsername(String username) {
        return new User();
    }

    public int delete(Long id) {
        return 0;
    }

}
