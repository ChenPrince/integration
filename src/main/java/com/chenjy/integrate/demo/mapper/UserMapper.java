package com.chenjy.integrate.demo.mapper;

import com.chenjy.integrate.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @program: demo
 * @description: 用户sql映射
 * @author: ztz-prince
 * @create:
 * @date:2018-12-10-20:16
 **/
public interface UserMapper {

    /**
     * 插入新用户
     * @return
     */
    int insertNewUser(User user);
}
