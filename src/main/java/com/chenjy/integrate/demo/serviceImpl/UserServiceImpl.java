package com.chenjy.integrate.demo.serviceImpl;

import com.chenjy.integrate.demo.entity.User;
import com.chenjy.integrate.demo.mapper.UserMapper;
import com.chenjy.integrate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: demo
 * @description: 用户服务
 * @author: ztz-prince
 * @create:
 * @date:2018-12-12-19:32
 **/
@Component
@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    String log="???";

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    /**
     * 插入新用户
     */
    @Override
    public void insertUser(User user) {

        try{
            userMapper.insertNewUser(user);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public String toString(){
        return "注入成功";
    }

}
