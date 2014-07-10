package com.benpoor.controller;

import com.benpoor.model.User;
import com.benpoor.model.Users;
import com.benpoor.persistence.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private UsersMapper usersMapper;

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("ssw");
        return user;
    }

    @RequestMapping("/view")
    public List<Users> views(){
        Users users = new Users();
        List<Users> usersList = usersMapper.find(users);
        return usersList;
    }
}
