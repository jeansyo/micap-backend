package com.educational.educational.controllers;

import com.educational.educational.dao.UserDao;
import com.educational.educational.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao UserDao;

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<Users> getUsers() {

        return UserDao.getUsers();
    }

    @RequestMapping(value = "api/user/{code}", method = RequestMethod.GET)
    public Users user(@PathVariable String code) {


        Users currentuser = new Users();
        currentuser.setName("Jean");
        currentuser.setEmail("jeansyo@gmail.com");
        currentuser.setPassword("123214123");
        currentuser.setCode(code);

        return currentuser;
    }

}
