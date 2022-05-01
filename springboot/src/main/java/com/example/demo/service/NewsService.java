package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.News;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fauchard
 * @since 2021-11-28
 */
public interface NewsService extends IService<News> {

    Page<News> selectPage(Integer pageNum, Integer pageSize, LambdaQueryWrapper<News> wrapper);
}
