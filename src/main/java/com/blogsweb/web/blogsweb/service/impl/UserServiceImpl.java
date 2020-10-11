package com.blogsweb.web.blogsweb.service.impl;

import com.blogsweb.web.blogsweb.mapper.UserMapper;
import com.blogsweb.web.blogsweb.model.User;
import com.blogsweb.web.blogsweb.redis.RedisCache;
import com.blogsweb.web.blogsweb.service.UserService;
import com.blogsweb.web.blogsweb.utils.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RedisCache redisCache;



    //@Cacheable(key ="'userList'+#accountId")
    public Object findAllUser(String accountId) {
        String key="userList"+accountId;
        List<User> list=null;
        if(redisCache.exists(key)){
            byte[] byt =redisCache.get(key, byte[].class);
            list = (List)SerializeUtil.unserizlize(byt);
        }else{
            list=userMapper.findAllUser();
            redisCache.put(key, SerializeUtil.serialize(list));
        }
        return list;
    }

    @Transactional
    public Object saveUser(User user,String accountId) {


            int count= userMapper.findUserByCodeCount(user.getUserCode());

            if (count==0){
                if(userMapper.insertUser(user)>0){
                    System.out.println("开始插入");
                    try {
                        String key = "userList"+accountId;
                        if(redisCache.exists(key)){
                            //从缓存中取出
                            byte[] byt =redisCache.get(key, byte[].class);
                            List<User> ulis = (List)SerializeUtil.unserizlize(byt);
                            List<User> findUserByCodeList= userMapper.findUserByCode(user.getUserCode());
                            ulis.add(findUserByCodeList.get(0));
                            redisCache.put(key, SerializeUtil.serialize(ulis));
                            return ulis;
                        }else{
                            findAllUser(accountId);
                            byte[] byt =redisCache.get(key, byte[].class);
                            List<User> ulis = (List)SerializeUtil.unserizlize(byt);
                            List<User> findUserByCodeList= userMapper.findUserByCode(user.getUserCode());
                            ulis.add(findUserByCodeList.get(0));
                            redisCache.put(key, SerializeUtil.serialize(ulis));
                            return ulis;
                        }

                    }catch (Exception e){
                        Map<String,String> map=new HashMap<>();
                        map.put("Error","The data insertion failed and the transaction was rolled back;");
                        e.printStackTrace();
                        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return map;

                    }

                }else
                {
                    Map<String,String> map=new HashMap<>();
                    map.put("Error","Data insertion failed");
                    return map;
                }
            }else{
                Map<String,String> map=new HashMap<>();
                map.put("Error","This user already exists");
                return map;

            }


    }
}
