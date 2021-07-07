package com.module.case4.security.appUser;


import com.module.case4.model.users.User;



public interface IAppUserService {

    User getUserByName(String name);
}
