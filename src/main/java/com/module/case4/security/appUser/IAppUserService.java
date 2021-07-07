package com.module.case4.security.appUser;


import com.module.case4.model.users.User;



public interface IAppUserService {

    User getUserByUserName(String name);
    User getCurrentUser();
}
