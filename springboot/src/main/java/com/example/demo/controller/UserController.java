package com.example.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author fauchard
 * @since 2021-11-11
 */
@RestController    //返回Json 数据 相当于@Controller+@ResponseBody两个注解的结合
@RequestMapping("/user")  //返回路由  请求地址 路径
public class UserController {
    //extends BaseController
    //先有service 类（引入mapper） 再controller类引入 service 类
    //Resource 注解将 UserMapper 引入到 UserController 类
    @Resource
    UserMapper userMapper;

    @PostMapping("/login")    //postmapping 接口
    public Result<?> login(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //新建一个QueryWrapper对象，类型为User对象，也就是需要查询的实体数据
        queryWrapper.eq("username", user.getUsername()); //eq等于 =
        queryWrapper.eq("password", user.getPassword());
        queryWrapper.eq("role", user.getRole());
        User res = userMapper.selectOne(queryWrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        // 生成token
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");  //设置默认密码
        }
        user.setRole(2);
        userMapper.insert(user);
        return Result.success();
    }

    @PostMapping    //新增函数   RequestBody 将前台传过来的JSON对象转换为Java对象（实体）
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    /**
     * MYBATISPLUS 条件构造器带条件查询 selectById 使用
     */
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {

        return Result.success(userMapper.selectById(id));
    }

    /**
     * MYBATISPLUS 条件构造器带条件查询 selectList 使用
     */
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(userMapper.selectList(null));
    }

    /**
     * 统计数据
     *
     * @return
     */
    @GetMapping("/count")
    public Result<?> count() {
//        User user = getUser(); // 当前登录的用户信息
        return Result.success(userMapper.countAddress());
    }

    /**
     * 用户分页列表查询
     * @param pageNum  页码
     * @param pageSize 每页多少条
     * @param search   关键字模糊查询
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        //mybatisPlus 分页接口
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        //分页对象
        Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
        return Result.success(userPage);
    }


}
