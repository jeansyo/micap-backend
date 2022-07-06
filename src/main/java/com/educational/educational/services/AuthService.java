package com.educational.educational.services;

import com.educational.educational.beans.AuthResponseBean;
import com.educational.educational.beans.ResponseBean;
import com.educational.educational.beans.UserBean;
import com.educational.educational.dao.AuthDao;
import com.educational.educational.models.Users;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

public class AuthService implements IAuthService {

    @Autowired
    private com.educational.educational.dao.AuthDao AuthDao;

    @Autowired
    private JWTUtil jwtUtil;

    public ResponseEntity<AuthResponseBean> serviceLogin(Users user) {

        AuthResponseBean response = new AuthResponseBean();

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());
        UserBean userResponseBean = new UserBean();

        Users isUser = AuthDao.verifyCrendentials(user);

        if( isUser != null ) {

            String token = jwtUtil.create(String.valueOf(isUser.getId()), isUser.getEmail());

            responseBean.setCodeError("200");
            responseBean.setMsgError("Token generado exitosamente");

            userResponseBean.setName(isUser.getName());
            userResponseBean.setEmail(isUser.getEmail());
            userResponseBean.setCodUsr(isUser.getCode());
            userResponseBean.setId(isUser.getId());
            userResponseBean.setToken(token);
            userResponseBean.setRole(isUser.getRole());

            response.setAPI(responseBean);
            response.setUser(userResponseBean);


            return new ResponseEntity<AuthResponseBean>(response, HttpStatus.OK);
        }

        responseBean.setCodeError("404");
        responseBean.setMsgError("Credenciales invalidas");

        userResponseBean.setName(null);
        userResponseBean.setEmail(null);
        userResponseBean.setCodUsr(null);
        userResponseBean.setId(null);
        userResponseBean.setRole(null);

        response.setAPI(responseBean);
        response.setUser(userResponseBean);

        return new ResponseEntity<AuthResponseBean>(response, HttpStatus.NOT_FOUND);
    }

}
