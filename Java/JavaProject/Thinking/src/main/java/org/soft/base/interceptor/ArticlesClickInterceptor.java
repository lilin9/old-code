package org.soft.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.soft.base.model.Human;
import org.soft.base.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by LILIN on 2023/8/1/10:27:03
 * 文章被点击的拦截器
 */
@Component
@Slf4j
public class ArticlesClickInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    //从配置文件中读取
    @Value("${my.redis.zset.articleKey}")
    private String articleKey;
    @Value("${my.redis.zset.articleMember}")
    private String memberPrefix;
    @Value("${my.redis.string.expireTime}")
    private String expireTime;
    @Value("${my.redis.string.userKey}")
    private String userPrefix;

    /*
     * 拦截用户点击文章的请求，然后在 redis 里面记录文章的点击量
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取 session 里面的用户信息
        Human human = (Human) request.getSession().getAttribute("human");
        //判空
        if (Objects.isNull(human)) {
            //重定向登录页面
            response.sendRedirect("/view/login.html");
            return false;
        }

        //拼接出 redis 中的 string 的key
        String url = request.getServletPath();
        String articleMember = memberPrefix + url.substring(url.lastIndexOf("/") + 1);
        String stringKey = userPrefix + human.getHumanId() + "_" + articleMember;

        //判断其在 redis 里面是否还在过期时间内
        if (!redisUtils.hasKey(stringKey)) {
            //将用户信息存入 redis，并设置过期时间
            setUserExpireTime(human, stringKey);
            //增加文章的点击量
            addClickCount(articleMember);
        }
        return true;
    }

    /**
     * @param articleMember articleMember
     * @Return
     * @Description 增加文章点击量
     * @Author LILIN
     * @Date 2023/8/3 10:08:34
     */
    private void addClickCount(String articleMember) {
        //将用户点击存入 redis
        Double resultScore = redisUtils.incrZSet(articleKey, articleMember);
        //打印日志
        log.info("文章 {} 的点击量是 {} 次.", articleKey + "::" + articleMember, resultScore);
    }

    /**
     * @param human human
     * @param stringKey stringKey
     * @Return
     * @Description 设置用户过期时间，过期时间是一天，这一天内同一个用户对文章的多次点击只能算一次
     * @Author LILIN
     * @Date 2023/8/3 10:08:49
     */
    private void setUserExpireTime(Human human, String stringKey) {
        //将用户信息存入 redis，并设置过期时间
        redisUtils.addString(stringKey, human.toString());
        redisUtils.expireTime(stringKey, Long.parseLong(expireTime));

        //打印日志信息
        log.info("key {} 的过期时间是 {}.", stringKey, expireTime);
    }
}
