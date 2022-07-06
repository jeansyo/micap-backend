package com.educational.educational.services;

import com.educational.educational.beans.AuthResponseBean;
import com.educational.educational.models.Users;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<AuthResponseBean> serviceLogin(Users user);

}
