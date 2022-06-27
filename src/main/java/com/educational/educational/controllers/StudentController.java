package com.educational.educational.controllers;

import com.educational.educational.beans.AuthResponseBean;
import com.educational.educational.beans.CoursesResponseBean;
import com.educational.educational.beans.ResponseBean;
import com.educational.educational.dao.CourseDao;
import com.educational.educational.dao.StudentDao;
import com.educational.educational.models.Users;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.lang.Integer.parseInt;

@RestController
public class StudentController {

    @Autowired
    private StudentDao StudentDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/student/{courseID}/{studentCode}", method = RequestMethod.GET)
    public ResponseEntity<ResponseBean> studentAddToCourse(@RequestHeader(value = "X-token") String token ,@PathVariable Integer courseID, @PathVariable String studentCode) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.FORBIDDEN);

        }

        boolean createdStudent = StudentDao.addStudentToCourse(parseInt(userID), courseID, studentCode);

        if( !createdStudent ) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("Datos erroneos");

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.CONFLICT);
        }

        responseBean.setCodeError("201");
        responseBean.setMsgError("Estudiante agregado");

        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.CREATED);

    }

    @RequestMapping(value = "api/student/{courseID}/{studentCode}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBean> studentremoveToCourse(@RequestHeader(value = "X-token") String token ,@PathVariable Integer courseID, @PathVariable String studentCode) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        String userID = jwtUtil.getKey(token);
        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.FORBIDDEN);

        }

        boolean removedStudent = StudentDao.removeStudentToCourse(parseInt(userID), courseID, studentCode);

        if( !removedStudent ) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("Datos erroneos");

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.CONFLICT);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Estudiante eliminado");

        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);

    }

}
