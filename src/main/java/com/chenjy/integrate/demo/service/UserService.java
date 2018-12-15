package com.chenjy.integrate.demo.service;

import com.chenjy.integrate.demo.entity.User;

public interface UserService {

    void insertUser(User user) ;

    @Override
    String toString();

}
