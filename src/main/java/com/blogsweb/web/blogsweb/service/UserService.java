package com.blogsweb.web.blogsweb.service;

import com.blogsweb.web.blogsweb.model.User;

import java.util.List;

public interface UserService {

    Object findAllUser(String accountId);

    Object saveUser(User user,String accountId);
}
