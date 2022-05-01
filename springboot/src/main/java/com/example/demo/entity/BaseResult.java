package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("baseResult")
@Data
public class BaseResult {
    @TableId(value = "code",type = IdType.AUTO)
    private int code;
    private String msg;
    private List<?> data;
}
