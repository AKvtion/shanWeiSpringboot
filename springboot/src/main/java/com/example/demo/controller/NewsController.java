package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fauchard
 * @since 2021-11-11
 */
@RestController     //@RestController注解相当于@ResponseBody ＋ @Controller  可以返回json数据
@RequestMapping("/news")   // 请求路径
public class NewsController {

    /**
     * @Autowired 通过byType 实现自动装配
     * @Resource 依赖注入（自动按byName方式进行查找，如果没有找到符合的bean，则回退为一个原始类型进行查找，如果找到就注入bean）
     */
    @Resource
    NewsMapper newsMapper;

    @PostMapping  // @PostMapping 处理HTTP POST请求
    public Result<?> save(@RequestBody News news) {
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success();
    }

    @DeleteMapping("/{id}")     // @DeleteMapping 处理HTTP Delete请求
    public Result<?> update(@PathVariable Long id) {
        newsMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(newsMapper.selectById(id));
    }


    /**
     * findPage方法 新闻列表分页查询    传入三个值
     * @param pageNum   页码  默认值为 1
     * @param pageSize  每页多少条  默认值为  10
     * @param search    关键字模糊查询  默认值为 空
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(News::getTitle, search);
        }
        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }
}
