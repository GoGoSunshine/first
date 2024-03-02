package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    //新增和修改
    @PostMapping
    public Integer save(@RequestBody User user){
        //新增或者更新都在这个里面
        return userService.save(user);
    }
    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        List<User> all= userMapper.findAll();
        return all;
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

    //查询所有数据
    //分页查询
    //@RewuestParam接收？pageNum=1$pageSize=10
    @GetMapping("/page")   //接口路径/user/page
    public Map<String,Object> findAll(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String username){
        pageNum = (pageNum -1) * pageSize;
        username = "%" + username + "%";
        List<User> data = userMapper.selectPage(pageNum,pageSize,username);
        Integer total = userMapper.selectTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
