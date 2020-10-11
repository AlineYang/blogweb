package com.blogsweb.web.blogsweb.mapper;

import com.blogsweb.web.blogsweb.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAllUser();

    int insertUser(User user);

    List<User> findUserByCode(String userCode);

    int findUserByCodeCount(String userCode);
}
