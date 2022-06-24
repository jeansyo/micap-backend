package com.educational.educational.controllers;

import com.educational.educational.beans.CoursesResponseBean;
import com.educational.educational.beans.ResponseBean;
import com.educational.educational.dao.CourseDao;
import com.educational.educational.models.Courses;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class CourseController {

    @Autowired
    private CourseDao CourseDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/courses", method = RequestMethod.GET)
    public ResponseEntity<CoursesResponseBean> getCoursesById(@RequestHeader( value = "X-token" ) String token ) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.FORBIDDEN);

        }

        List<Courses> result = CourseDao.getCoursesByID(userID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");
        coursesResponseBean.setAPI(responseBean);
        coursesResponseBean.setResult(result);

//        if(result == null) {
//            return null;
//        }

        return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.OK);

    }

    @RequestMapping(value="api/courses", method=RequestMethod.POST)
    public ResponseEntity<CoursesResponseBean> createCourse(@RequestHeader(value = "X-token") String token, @RequestBody Courses course) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.FORBIDDEN);

        }

        course.setUser(parseInt(userID));

        boolean courseCreated = CourseDao.createCourse(course);

        if(!courseCreated) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("El curso con el nombre "+course.getName()+" ya existe.");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.FORBIDDEN);
        }

        responseBean.setCodeError("201");
        responseBean.setMsgError("Curso creado exitosamente.");
        coursesResponseBean.setAPI(responseBean);

        return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.CREATED);


    }

    @RequestMapping(value="api/courses/{courseID}", method=RequestMethod.DELETE)
    public ResponseEntity<CoursesResponseBean> createCourse(@RequestHeader(value = "X-token") String token, @PathVariable String courseID) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.FORBIDDEN);

        }

        boolean result = CourseDao.deleteCourse(parseInt(userID), parseInt(courseID));

        if(!result) {
            responseBean.setCodeError("403");
            responseBean.setMsgError("No autorizado");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.UNAUTHORIZED);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("El curso ha sido eliminado.");
        coursesResponseBean.setAPI(responseBean);

        return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.OK);

    }


    }
