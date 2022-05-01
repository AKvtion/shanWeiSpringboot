package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.entity.Plank;
import com.example.demo.mapper.PlankMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fauchard
 * @since 2021-11-11
 */
@RestController //相当于@Controller+@ResponseBody两个注解的结合
@RequestMapping("/plank")
public class PlankController {

    /**
     * @Autowired 通过byType 实现自动装配
     * @Resource 依赖注入（自动按byName方式进行查找，如果没有找到符合的bean，则回退为一个原始类型进行查找，如果找到就注入bean）
     */
    @Resource
    PlankMapper plankMapper;

    @PostMapping    // @PostMapping 处理HTTP POST请求
    public Result<?> save(@RequestBody Plank plank) {
        plank.setTime(new Date());
        plankMapper.insert(plank);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Plank plank) {
        plankMapper.updateById(plank);
        return Result.success();
    }

    @DeleteMapping("/{id}")     // @DeleteMapping 处理HTTP Delete请求
    public Result<?> update(@PathVariable Long id){
        plankMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){

        return Result.success(plankMapper.selectById(id));
    }

    @RequestMapping(value = "/planktalk/lastplank")
    @ResponseBody
    public Result<?> allPlank(){
        List<Plank> plankList = plankMapper.selectList(null);
        plankList.forEach(System.out::println);
        return Result.success(plankList);
    }


    /**
     * 公告分页列表查询
     * @param pageNum  页码
     * @param pageSize 每页多少条
     * @param search   关键字模糊查询
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Plank> wrapper = Wrappers.<Plank>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Plank::getTitle, search);
        }
        Page<Plank> newsPage = plankMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }

}
