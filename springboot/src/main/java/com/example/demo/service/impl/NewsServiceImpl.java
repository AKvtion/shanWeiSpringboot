package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.apache.poi.ss.formula.functions.T;

import com.example.demo.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fauchard
 * @since 2021-11-28
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Resource
    NewsMapper newsMapper;



    @Override
    public Page<News> selectPage(Integer pageNum, Integer pageSize, LambdaQueryWrapper<News> wrapper) {
        Page<News> page = new Page<>(pageNum,pageSize);
        Page<News> selectPage = newsMapper.selectPage(page,query());
        return selectPage;
    }
}
