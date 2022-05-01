package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Plank;
/*
Mybatis-Plus 是一款 Mybatis 动态 SQL 自动注入 Mybatis 增删改查 CRUD 操作中间件，
减少你的开发周期优化动态维护 XML 实体字段。
调用BaseMapper中的一些基本方法。在使用的时候需要实现BaseMapper接口
Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 */
public interface PlankMapper extends BaseMapper<Plank> {

}
