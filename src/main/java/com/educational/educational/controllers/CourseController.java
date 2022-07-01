package com.educational.educational.controllers;

import com.educational.educational.beans.CourseBean;
import com.educational.educational.beans.CourseResponseBean;
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

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

    @Autowired
    private CourseDao CourseDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/courses", method = RequestMethod.GET)
    public ResponseEntity<CoursesResponseBean> getCourses(@RequestHeader( value = "X-token" ) String token ) {

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

        List<CourseBean> result = CourseDao.getCourses(userID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");
        coursesResponseBean.setAPI(responseBean);
        coursesResponseBean.setResult(result);

        return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.OK);

    }

    @RequestMapping(value="api/courses", method=RequestMethod.POST)
    public ResponseEntity<CourseResponseBean> createCourse(@RequestHeader(value = "X-token") String token, @RequestBody Courses course) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());
        CourseBean courseBean = new CourseBean();

        CourseResponseBean courseResponseBean = new CourseResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            courseResponseBean.setAPI(responseBean);

            return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.FORBIDDEN);

        }

        course.setUser(parseInt(userID));

        Courses courseCreated = CourseDao.createCourse(course);

        if(courseCreated == null) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("El curso con el nombre "+course.getName()+" ya existe.");
            courseResponseBean.setAPI(responseBean);

            return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.FORBIDDEN);
        }

        courseBean.setId(courseCreated.getId());
        courseBean.setName(courseCreated.getName());

        responseBean.setCodeError("201");
        responseBean.setMsgError("Curso creado exitosamente.");
        courseResponseBean.setAPI(responseBean);
        courseResponseBean.setResult(courseBean);

        return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.CREATED);

    }

    @RequestMapping(value="api/courses/{courseID}", method=RequestMethod.DELETE)
    public ResponseEntity<CoursesResponseBean> deleteCourse(@RequestHeader(value = "X-token") String token, @PathVariable String courseID) {
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


    @RequestMapping(value = "api/courses/{courseID}", method = RequestMethod.GET)
    public ResponseEntity<CourseResponseBean> getCourseById(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID ) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CourseBean courseBean = new CourseBean();

        CourseResponseBean courseResponseBean = new CourseResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            courseResponseBean.setAPI(responseBean);

            return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.FORBIDDEN);

        }

        Courses result = CourseDao.getCourseById(courseID);

        if( result == null ) {
            responseBean.setCodeError("404");
            responseBean.setMsgError("Curso no encontrado");

            courseResponseBean.setAPI(responseBean);

            return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.OK);

        }

        courseBean.setName(result.getName());
        courseBean.setId(result.getId());

        responseBean.setCodeError("200");
        responseBean.setMsgError("Curso encontrado");
        courseResponseBean.setAPI(responseBean);
        courseResponseBean.setResult(courseBean);

        return new ResponseEntity<CourseResponseBean>(courseResponseBean, HttpStatus.OK);

    }



    @RequestMapping(value = "api/my/courses", method = RequestMethod.GET)
    public ResponseEntity<CoursesResponseBean> getMyCourses(@RequestHeader( value = "X-token" ) String token ) {

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

        ArrayList<CourseBean> resultCourses = new ArrayList<CourseBean>();
        List<Courses> result = CourseDao.getMyCourses(parseInt(userID));

        result.forEach(course -> {
            CourseBean courseBean = new CourseBean();
            courseBean.setName(course.getName());
            courseBean.setId(course.getId());
            resultCourses.add(courseBean);
        });

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");
        coursesResponseBean.setAPI(responseBean);
        coursesResponseBean.setResult(resultCourses);

        return new ResponseEntity<CoursesResponseBean>(coursesResponseBean, HttpStatus.OK);

    }


    }
