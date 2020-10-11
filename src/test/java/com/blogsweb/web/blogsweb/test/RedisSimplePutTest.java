package com.blogsweb.web.blogsweb.test;

import com.blogsweb.web.blogsweb.model.User;
import com.blogsweb.web.blogsweb.redis.RedisCache;
import com.blogsweb.web.blogsweb.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisSimplePutTest {
    private Logger LOGGER = LoggerFactory.getLogger(RedisSimplePutTest.class);
    @Autowired
    private RedisCache redisCache;
    /**
     * 存储字符串
     */
    @Test
    public void testPutString() {
        String key = "test_string_2";
        String value = "string_value";
        LOGGER.info("存储字符串开始 key={} value={}", key, value);
        boolean putFlag = redisCache.put(key, "string_value", 0, TimeUnit.MINUTES);
        LOGGER.info("存储字符串结束 putFlag={}", putFlag);
        String rvalue = redisCache.get(key);
        LOGGER.info("存储字符串-> 查询缓存value={}", rvalue);
    }
    /**
     * 存储自定义对象
     */
    @Test
    public void testPutObject() {
        String key = "AD1001";
        byte[] bk = key.getBytes();
        User tArticle =
                new User(1, "AD1001", '女', "白衣酒客", "杨冕","administrator","15927023724","123456","1477699332@qq.com");
        LOGGER.info("存储对象开始 key={} value={}", key, tArticle);
        redisCache.put(bk.toString(), SerializeUtil.serialize(tArticle));
        LOGGER.info("存储对象结束");
        byte[] byt =redisCache.get(bk.toString(), byte[].class);
        User value = (User)SerializeUtil.unserizlize(byt);
        LOGGER.info("存储对象-> 查询缓存value={}", value.getTel());
//        String un ="rO0ABXNyACRjb20uYmxvZ3N3ZWIud2ViLmJsb2dzd2ViLm1vZGVsLlVzZXJ7JDLkef45/QIACUMAA3NleEkABnVzZXJJZEwABWVtYWlsdAASTGphdmEvbGFuZy9TdHJpbmc7TAAIbmlja25hbWVxAH4AAUwACHBhc3N3b3JkcQB+AAFMAAhyZWFsbmFtZXEAfgABTAADdGVscQB+AAFMAAh1c2VyQ29kZXEAfgABTAAIdXNlcm5hbWVxAH4AAXhwWXMAAAABdAARMTQ3NzY5OTMzMkBxcS5jb210AAznmb3ooaPphZLlrqJ0AAYxMjM0NTZ0AAbmnajlhpV0AAsxNTkyNzAyMzcyNHQABkFEMTAwMXQADWFkbWluaXN0cmF0b3I=" ;
//        byte[] byt1 = un.getBytes();
//        User user1 =(User)SerializeUtil.unserizlize(byt1);
//        LOGGER.info("获取到序列化的值", user1.getTel());
    }
}
