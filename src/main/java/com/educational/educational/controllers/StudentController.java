package com.educational.educational.controllers;

import com.educational.educational.beans.*;
import com.educational.educational.dao.StudentDao;
import com.educational.educational.models.Users;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    @Autowired
    private StudentDao StudentDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/student/{courseID}/{studentCode}", method = RequestMethod.GET)
    public ResponseEntity<StudentResponseBean> studentAddToCourse(@RequestHeader(value = "X-token") String token , @PathVariable Integer courseID, @PathVariable String studentCode) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        StudentBean studentBean = new StudentBean();

        StudentResponseBean studentResponseBean = new StudentResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            studentResponseBean.setAPI(responseBean);

            return new ResponseEntity<StudentResponseBean>(studentResponseBean, HttpStatus.FORBIDDEN);

        }

        Users createdStudent = StudentDao.addStudentToCourse(parseInt(userID), courseID, studentCode);

        if( createdStudent == null ) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("Datos erroneos");
            studentResponseBean.setAPI(responseBean);

            return new ResponseEntity<StudentResponseBean>(studentResponseBean, HttpStatus.CONFLICT);
        }

        responseBean.setCodeError("201");
        responseBean.setMsgError("Estudiante agregado");

        studentBean.setCode(createdStudent.getCode());
        studentBean.setEmail(createdStudent.getEmail());
        studentBean.setName(createdStudent.getName());

        studentResponseBean.setAPI(responseBean);
        studentResponseBean.setResult(studentBean);

        return new ResponseEntity<StudentResponseBean>(studentResponseBean, HttpStatus.CREATED);

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

    @RequestMapping(value = "api/students/{courseID}", method = RequestMethod.GET)
    public ResponseEntity<StudentsResponseBean> getStudents(@RequestHeader(value = "X-token") String token , @PathVariable Integer courseID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        StudentsResponseBean studentsResponseBean = new StudentsResponseBean();

        String userID = jwtUtil.getKey(token);
        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            studentsResponseBean.setAPI(responseBean);

            return new ResponseEntity<StudentsResponseBean>(studentsResponseBean, HttpStatus.FORBIDDEN);

        }

        List<StudentBean> result = StudentDao.getStudents(courseID);
        if(result == null) {
            responseBean.setCodeError("403");
            responseBean.setMsgError("No autorizado");

            studentsResponseBean.setAPI(responseBean);

            return new ResponseEntity<StudentsResponseBean>(studentsResponseBean, HttpStatus.UNAUTHORIZED);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Estudiantes");

        studentsResponseBean.setAPI(responseBean);
        studentsResponseBean.setResult(result);

        return new ResponseEntity<StudentsResponseBean>(studentsResponseBean, HttpStatus.OK);

    }

    }
