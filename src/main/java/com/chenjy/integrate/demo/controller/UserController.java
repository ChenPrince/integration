package com.chenjy.integrate.demo.controller;

import com.chenjy.integrate.demo.entity.User ;
import com.chenjy.integrate.demo.service.UserService;
import com.chenjy.integrate.demo.serviceImpl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: swagger2demo
 * @description: 用户Controller
 * @author: ztz-prince
 * @create:
 * @date:2018-12-06-17:11
 **/

//一般来说接口统一都是传一个对象进来，所有的信息由前端分装在一个对象中，再转换为json传到后台
//所以在下列实例中，使用url传值的并不实用
//@Valid @RequestBody 实现自动注入

@RestController
@Api(description = "测试")
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    //ConcurrentHashMap ,线程安全，能够接受高并发
    static Map<Long,User> map=new ConcurrentHashMap<>();

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表",notes="获取所有用户信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/A01",method= RequestMethod.GET)
    public List<User> getList(){
        List<User> list =new ArrayList<>(map.values());
        return list;
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    //ui外部提示
    @ApiOperation(value = "创建用户",notes="根据user对象创建用户",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //ui内部提示
    @ApiImplicitParam(name="user",value = "用户详情实体",required=true,dataType="User")
    @RequestMapping(value="/A02",method=RequestMethod.POST)
    public String postUser( @RequestBody User user){
        System.out.println(user);
        System.out.println(user.getId());

        map.put(user.getId(),user);
        String message=new String("添加成功");
        return message;
    }

    /**
     * @ @PathVariable注解：通过url获取值
     * @ @ApiImplicitParam：可以不用写，是用于增加ui提示
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详情",notes = "根据url的id来获取用户基本信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/A03/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable String id) {
        return  map.get(Long.parseLong(id));

    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户信息",notes = "根据url的id来指定对象，并且根据传过来的user进行用户基本信息更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "user", value = "用户详情实体类user", required = true, dataType = "User")

    })
    @RequestMapping(value = "/A04/{id}",method = RequestMethod.PUT)
    //@PathVariable 从url中获取参数
    public String putUser(@PathVariable String id,@RequestBody User user) {
        Long ID=Long.parseLong(id);
        User u = map.get(ID);
        u.setAge(user.getAge());
        u.setName(user.getName());
        map.put(ID,u);

        return "用户基本信息已经更新成功";

    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户",notes = "根据url的id来指定对象，进行用户信息删除")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/A05/{id}",method = RequestMethod.DELETE)
    public String delUser(@PathVariable String id) {
        map.remove(Long.parseLong(id));
        return "用户ID为："+ id + " 的用户已经被移除系统";

    }

    // 用户插入测试
    @ApiOperation(value="用户插入测试",notes = "通过mybatis向数据库插入数据")
    @ApiImplicitParam(name="user",value="新用户",required=true,dataType = "User")
    @RequestMapping(value="/A06",method=RequestMethod.POST)
    public String userInsertTest(@RequestBody User user){
        System.out.println(userService.toString());
        System.out.println(userService);
        userService.insertUser(user);

        return "用户插入成功";
    }


}
