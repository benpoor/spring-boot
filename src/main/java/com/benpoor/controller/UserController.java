package com.benpoor.controller;

import com.benpoor.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-12
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("ssw");
        return user;
    }
}
