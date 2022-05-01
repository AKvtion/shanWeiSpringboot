package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class TokenUtils {

//    @Autowired
    @Resource
    private UserMapper userMapper;

    private static UserMapper staticUserMapper;

    @PostConstruct
    public void init() {
        staticUserMapper = userMapper;
    }

    /**
     * 生成token
     * @param user
     * @return
     * HMAC（Hash-based Message Authentication Code，散列消息认证码）是一种使用密码散列函数，
     * 同时结合一个加密密钥，通过特别计算方式之后产生的消息认证码（MAC）。
     * 它可以用来保证数据的完整性，同时可以用来作某个消息的身份验证。
     * HMAC算法 是一种基于密钥的报文完整性的验证方法。
     * HMAC算法利用哈希运算，以一个密钥和一个消息为输入，生成一个消息摘要作为输出。
     * 其安全性是建立在Hash加密算法基础上的。它要求通信双方共享密钥、约定算法、对报文进行Hash运算，
     * 形成固定长度的认证码。通信双方通过认证码的校验来确定报文的合法性。HMAC算法可以用来作加密、数字签名、报文验证等。
     */
    public static String genToken(User user) {
        return JWT.create().withExpiresAt(DateUtil.offsetDay(new Date(), 1)).withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    /**
     * 获取token中的用户信息
     * @return
     */
    public static User getUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            String aud = JWT.decode(token).getAudience().get(0);
            Integer userId = Integer.valueOf(aud);
            return staticUserMapper.selectById(userId);
        } catch (Exception e) {
            log.error("解析token失败", e);
            return null;
        }
    }
}
