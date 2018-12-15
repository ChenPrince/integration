package com.chenjy.integrate.demo.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: swagger2demo
 * @description: 用户实体
 * @author: ztz-prince
 * @create:
 * @date:2018-12-06-17:12
 **/

public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    @NotNull(message = "用户id不能为空")
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
