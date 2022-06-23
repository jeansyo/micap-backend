package com.educational.educational.controllers;

import com.educational.educational.dao.UserDao;
import com.educational.educational.models.Users;
import com.educational.educational.utils.InfoJWTUtil;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao UserDao;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private InfoJWTUtil infoJWTUtil;

    @RequestMapping(value = "api/students", method = RequestMethod.GET)
    public List<Users> getStudents( @RequestHeader( value = "X-token" ) String token ) {

        String userID = infoJWTUtil.get(token);

        if( userID != null ){
            return UserDao.getUsers();
        }

        return null;

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
