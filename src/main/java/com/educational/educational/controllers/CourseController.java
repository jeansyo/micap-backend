package com.educational.educational.controllers;

import com.educational.educational.beans.*;
import com.educational.educational.dao.CourseDao;
import com.educational.educational.dto.*;
import com.educational.educational.models.Courses;
import com.educational.educational.models.Evaluations;
import com.educational.educational.models.Questions;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        courseBean.setRecommended(courseCreated.getRecommended());
        courseBean.setClassname(courseCreated.getClassname());

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
        courseBean.setClassname(result.getClassname());
        courseBean.setRecommended(result.getRecommended());

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

    @RequestMapping(value = "api/course/{courseID}/evaluations", method = RequestMethod.GET)
    public ResponseEntity<List<Evaluations>> getEvalutaions(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<Evaluations>>(new ArrayList<Evaluations>(), HttpStatus.FORBIDDEN);

        }

        List<Evaluations> result = CourseDao.getEvaluations(courseID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<Evaluations>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/course/evaluation", method = RequestMethod.POST)
    public ResponseEntity<Evaluations> createEvalutaion(@RequestHeader( value = "X-token" ) String token, @RequestBody Evaluations evaluations) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<Evaluations>(evaluations, HttpStatus.FORBIDDEN);

        }

        Evaluations result = CourseDao.createEvaluation(evaluations);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<Evaluations>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/course/evaluation/question", method = RequestMethod.POST)
    public ResponseEntity<Questions> createQuestionForEvaluation(@RequestHeader( value = "X-token" ) String token, @RequestBody Questions questions) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<Questions>(questions, HttpStatus.FORBIDDEN);

        }

        Questions result = CourseDao.createQuestionForEvaluation(questions);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<Questions>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/course/evaluation/{evaluationID}/questions/admin", method = RequestMethod.GET)
    public ResponseEntity<List<Questions>> getAnswersOfEvaluationForTeachers(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer evaluationID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<Questions>>(new ArrayList<Questions>(), HttpStatus.FORBIDDEN);

        }

        List<Questions> result = CourseDao.getQuestionsOfEvaluationForTeachers(evaluationID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<Questions>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "api/course/evaluation/{evaluationID}/questions", method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> getAnswersOfEvaluationForStudents(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer evaluationID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        List<QuestionDTO> responseQuestions = new ArrayList<QuestionDTO>();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<QuestionDTO>>(responseQuestions, HttpStatus.FORBIDDEN);

        }

        List<Questions> result = CourseDao.getQuestionsOfEvaluationForTeachers(evaluationID);


        Stream<QuestionDTO> questionList = result.stream().map(prev -> {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(prev.getQuestion());
            questionDTO.setFirstanswer(prev.getFirstanswer());
            questionDTO.setSecondanswer(prev.getSecondanswer());
            questionDTO.setThirdanswer(prev.getThirdanswer());
            questionDTO.setId(prev.getId());
            return questionDTO;
        });
        List<QuestionDTO> questionsList = questionList.collect(Collectors.toList());

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<QuestionDTO>>(questionsList, HttpStatus.OK);
    }


    @RequestMapping(value = "api/course/evaluation/test/{testID}", method = RequestMethod.POST)
    public ResponseEntity<ScoreDTO> getAnswersOfEvaluationForStudents(@RequestHeader( value = "X-token" ) String token, @RequestBody List<EvalDTO> evalDTO, @PathVariable Integer testID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        ScoreDTO scoreDTOTest = new ScoreDTO();

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<ScoreDTO>(scoreDTOTest, HttpStatus.FORBIDDEN);

        }

        ScoreDTO result = CourseDao.makeTest(evalDTO, parseInt(userID), testID);

        if(result==null) {
            return new ResponseEntity<ScoreDTO>(scoreDTOTest, HttpStatus.BAD_REQUEST);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<ScoreDTO>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/course/{courseID}/evaluations/available", method = RequestMethod.GET)
    public ResponseEntity<List<Evaluations>> getEvaluationsAvaliable(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        List<Evaluations> scoreDTOTest = new ArrayList<>();

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<Evaluations>>(scoreDTOTest, HttpStatus.FORBIDDEN);

        }

        List<Evaluations> result = CourseDao.avalaibleEvaluations(parseInt(userID), courseID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<Evaluations>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/course/{courseID}/evaluations/resolved", method = RequestMethod.GET)
    public ResponseEntity<List<EvaluationResolvedDTO>> getEvaluationsResolved(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        List<EvaluationResolvedDTO> scoreDTOTest = new ArrayList<>();

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<EvaluationResolvedDTO>>(scoreDTOTest, HttpStatus.FORBIDDEN);

        }

        List<EvaluationResolvedDTO> result = CourseDao.resolvedEvaluations(parseInt(userID), courseID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<EvaluationResolvedDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "api/course/evaluation/remove/{evaluationID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBean> removeEvaluation(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer evaluationID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        List<EvaluationResolvedDTO> scoreDTOTest = new ArrayList<>();

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.FORBIDDEN);

        }

        boolean result = CourseDao.removeEvaluation(parseInt(userID), evaluationID);

        if( !result ) {
            responseBean.setCodeError("400");
            responseBean.setMsgError("Error al eliminar");
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Evaluacion eliminada");

        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
    }


    @RequestMapping(value = "api/course/evaluation/{evaluationID}/resolved", method = RequestMethod.GET)
    public ResponseEntity<List<EvaluationResultDTO>> getEvaluationsResolvedByStudent(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer evaluationID) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        List<EvaluationResultDTO> scoreDTOTest = new ArrayList<>();

        CoursesResponseBean coursesResponseBean = new CoursesResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            coursesResponseBean.setAPI(responseBean);

            return new ResponseEntity<List<EvaluationResultDTO>>(scoreDTOTest, HttpStatus.FORBIDDEN);

        }

        List<EvaluationResultDTO> result = CourseDao.getResolvedStudentEvaluation(parseInt(userID), evaluationID);

        responseBean.setCodeError("200");
        responseBean.setMsgError("Cursos encontrados");

        return new ResponseEntity<List<EvaluationResultDTO>>(result, HttpStatus.OK);
    }

    }
