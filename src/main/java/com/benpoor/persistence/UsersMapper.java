package com.benpoor.persistence;

import com.benpoor.model.Users;

import java.util.List;

/**
 * @Description 实体对象DAO接口，可以在此基础上扩展。增加新的实现接口，特殊的接口
 * @author shensw
 * @date 2014-07-10
 * @version 1.0
 */
public interface UsersMapper {
    public List<Users> find(Users users);
}
    