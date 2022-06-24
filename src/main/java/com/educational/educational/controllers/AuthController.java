package com.educational.educational.controllers;

import com.educational.educational.beans.AuthResponseBean;
import com.educational.educational.beans.UserResponseBean;
import com.educational.educational.dao.AuthDao;
import com.educational.educational.beans.ResponseBean;
import com.educational.educational.models.Users;
import com.educational.educational.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private AuthDao AuthDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseBean> authLogin(@RequestBody Users user) {

        AuthResponseBean response = new AuthResponseBean();

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());
        UserResponseBean userResponseBean = new UserResponseBean();

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

    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseBean> authRegister(@RequestBody Users user) {

        ResponseBean response = new ResponseBean();
        UserResponseBean userResponseBean = new UserResponseBean();
        AuthResponseBean authResponseBean = new AuthResponseBean();
        response.setDate(new Date().toString());

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        boolean created = AuthDao.createUser(user);

        if(created == true) {
            response.setMsgError("Cuenta creada exitosamente");
            response.setCodeError("201");
            authResponseBean.setAPI(response);
            authResponseBean.setUser(userResponseBean);
            return new ResponseEntity<AuthResponseBean>(authResponseBean, HttpStatus.CREATED);
        }

        response.setCodeError("400");
        response.setMsgError("Las credenciales ya estan registradas.");

        authResponseBean.setUser(userResponseBean);
        authResponseBean.setAPI(response);

        return new ResponseEntity<AuthResponseBean>(authResponseBean, HttpStatus.CREATED);
    }

    @RequestMapping(value = "api/renew", method = RequestMethod.GET)
    public ResponseEntity<AuthResponseBean> authRenew(@RequestHeader(value="X-token") String token) {

        AuthResponseBean authResponseBean = new AuthResponseBean();

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());
        UserResponseBean userResponseBean = new UserResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {
            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            authResponseBean.setAPI(responseBean);
            authResponseBean.setUser(userResponseBean);

            return new ResponseEntity<AuthResponseBean>(authResponseBean, HttpStatus.FORBIDDEN);

        }

        Users result = AuthDao.getUserByID(userID);

        if(result == null) {
            responseBean.setCodeError("404");
            responseBean.setMsgError("Usuario no encontrado");
            authResponseBean.setAPI(responseBean);
            authResponseBean.setUser(userResponseBean);

            return new ResponseEntity<AuthResponseBean>(authResponseBean, HttpStatus.NOT_FOUND);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Token generado");

        String newToken = jwtUtil.create(String.valueOf(result.getId()), result.getEmail());

        userResponseBean.setRole(result.getRole());
        userResponseBean.setId(result.getId());
        userResponseBean.setName(result.getName());
        userResponseBean.setEmail(result.getEmail());
        userResponseBean.setCodUsr(result.getCode());
        userResponseBean.setToken(newToken);

        authResponseBean.setAPI(responseBean);
        authResponseBean.setUser(userResponseBean);

        return new ResponseEntity<AuthResponseBean>(authResponseBean, HttpStatus.OK);
    }

}
