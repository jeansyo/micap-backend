package com.educational.educational.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoJWTUtil {

    @Autowired
    private JWTUtil jwtUtil;

    public String get(String token) {

        String userID = jwtUtil.getKey(token);

        if(userID != null) {
            return userID;
        }
        return null;
    }
}
