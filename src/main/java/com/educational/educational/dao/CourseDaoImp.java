package com.educational.educational.dao;

import com.educational.educational.beans.CourseBean;
import com.educational.educational.dto.EvalDTO;
import com.educational.educational.dto.EvaluationResolvedDTO;
import com.educational.educational.dto.EvaluationResultDTO;
import com.educational.educational.dto.ScoreDTO;
import com.educational.educational.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Repository
@Transactional
public class CourseDaoImp implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CourseBean> getCourses(String userID) {

        String query = "FROM Courses WHERE user = :user AND status = 1";

        List<Courses> result = entityManager.createQuery(query, Courses.class)
                .setParameter("user", parseInt(userID))
                .getResultList();

        ArrayList<CourseBean> courseBeansList = new ArrayList<CourseBean>();

        result.forEach(courses -> {
            String queryStudents = "FROM Students WHERE course = :course";
            List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                    .setParameter("course", courses.getId())
                    .getResultList();

            CourseBean courseBean = new CourseBean();
            courseBean.setId(courses.getId());
            courseBean.setName(courses.getName());
            courseBean.setStudents(resultStudents.size());

            courseBeansList.add(courseBean);

        });

        return courseBeansList;

    }

    @Override
    public Courses createCourse(Courses course) {

        String query = "FROM Courses WHERE name = :name AND status = 1";

        List<Courses> result = entityManager.createQuery(query, Courses.class)
                .setParameter("name", course.getName())
                .getResultList();

        if(!result.isEmpty()) {
            return null;
        }

        Courses newCourse = entityManager.merge(course);
        return newCourse;
    }

    @Override
    public boolean deleteCourse(Integer userID, Integer courseID) {

        Courses result = entityManager.find(Courses.class, courseID);

        if(result.getStatus() == 0) {
            return false;
        }

        if(result == null) {
            return false;
        }


        if( result.getUser() != userID ) {
            return false;
        }

        result.setStatus(0);
        entityManager.flush();
        return true;

    }

    @Override
    public Courses getCourseById(Integer courseID) {

        String query = "FROM Courses WHERE id = :course AND status = 1";
        List<Courses> result = entityManager.createQuery(query, Courses.class)
                .setParameter("course", courseID)
                .getResultList();

        if ( result.isEmpty() ) {
            return null;
        }

        return result.get(0);

    }

    @Override
    public List<Courses> getMyCourses(Integer userID) {

        String queryStudents = "FROM Students WHERE user = :user";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("user", userID)
                .getResultList();

        ArrayList<Courses> resultOfQuery = new ArrayList<Courses>();

        if( resultStudents.isEmpty() ) {
            return resultOfQuery;
        }

        resultStudents.forEach(student -> {

            String queryCourse = "FROM Courses WHERE id = :course AND status = 1";
            List<Courses> resultCourse = entityManager.createQuery(queryCourse, Courses.class)
                    .setParameter("course", student.getCourse())
                    .getResultList();

            if( !resultCourse.isEmpty() ) {
                resultOfQuery.add(resultCourse.get(0));
            }

        });

        return resultOfQuery;

    }

    @Override
    public Evaluations createEvaluation(Evaluations evaluations) {
        Evaluations newEvaluation = entityManager.merge(evaluations);
        return newEvaluation;
    }

    @Override
    public Questions createQuestionForEvaluation(Questions questions) {
        Questions newQuestion = entityManager.merge(questions);
        return newQuestion;
    }

    @Override
    public List<Evaluations> getEvaluations(Integer courseID) {

        String queryEvaluations = "FROM Evaluations WHERE status=1 AND course = :course";
        List<Evaluations> resultEvaluations = entityManager.createQuery(queryEvaluations, Evaluations.class)
                .setParameter("course", courseID)
                .getResultList();

        return resultEvaluations;
    }

    @Override
    public List<Questions> getQuestionsOfEvaluationForTeachers(Integer evaluationID) {

        String query = "FROM Questions WHERE evaluation=:evaluation";
        List<Questions> result = entityManager.createQuery(query, Questions.class)
                .setParameter("evaluation",evaluationID)
                .getResultList();

        return result;
    }

    @Override
    public ScoreDTO makeTest(List<EvalDTO> evalDTO, Integer userID, Integer testID) {

        String queryScores = "FROM Scores WHERE evaluation=:evaluation AND user=:user";
        List<Scores> resultScores = entityManager.createQuery(queryScores, Scores.class)
                .setParameter("evaluation", testID)
                .setParameter("user", userID)
                .getResultList();

        if(!resultScores.isEmpty()) {
            return null;
        }


        String queryy = "FROM Questions WHERE evaluation=:evaluation";
        List<Questions> results = entityManager.createQuery(queryy, Questions.class)
                        .setParameter("evaluation", testID)
                                .getResultList();

        Integer total = results.size();
        List<Integer> score = new ArrayList<>();

        evalDTO.forEach(eval -> {
            String query = "FROM Questions WHERE evaluation=:evaluation AND id=:question";

            Questions result = entityManager.createQuery(query, Questions.class)
                    .setParameter("evaluation",testID)
                    .setParameter("question", eval.getId())
                    .getSingleResult();

            if( result.getAnswer().equals(eval.getResponse()) ) {
                score.add(1);
            }

        });


        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setEvaluation(testID);

        int percent = (int)((score.size() * 100.0f) / total);
        scoreDTO.setScore(percent);

        Scores newScore = new Scores();
        newScore.setScore(percent);
        newScore.setUser(userID);
        newScore.setEvaluation(testID);

        entityManager.merge(newScore);

        return scoreDTO;
    }

    @Override
    public List<Evaluations> avalaibleEvaluations(Integer userID, Integer courseID) {

        String queryEvaluations = "FROM Evaluations WHERE course=:course AND status=1";
        List<Evaluations> resultEvaluations = entityManager.createQuery(queryEvaluations, Evaluations.class)
                .setParameter("course", courseID)
                .getResultList();

        System.out.println(resultEvaluations.size());


        List<Evaluations> responseEvaluations = new ArrayList<>();
        List<Integer> evals = new ArrayList<>();

        resultEvaluations.forEach(evaluations -> {
            String queryScores = "FROM Scores WHERE user=:user AND evaluation=:evaluation";
            List<Scores> resultScores = entityManager.createQuery(queryScores, Scores.class)
                    .setParameter("user", userID)
                    .setParameter("evaluation", evaluations.getId())
                    .getResultList();
            if(resultScores.isEmpty()) {
                evals.add(evaluations.getId());
            }
        });


        resultEvaluations.forEach(evale -> {
            if(evals.contains(evale.getId())) {
                responseEvaluations.add(evale);
            }
        });
        System.out.println(evals.size());

        return responseEvaluations;
    }

    @Override
    public List<EvaluationResolvedDTO> resolvedEvaluations(Integer userID, Integer courseID) {
        String queryEvaluations = "FROM Evaluations WHERE course=:course AND status=1";
        List<Evaluations> resultEvaluations = entityManager.createQuery(queryEvaluations, Evaluations.class)
                .setParameter("course", courseID)
                .getResultList();


        List<EvaluationResolvedDTO> responseEvaluations = new ArrayList<>();

        resultEvaluations.forEach(evaluations -> {
            String queryScores = "FROM Scores WHERE user=:user AND evaluation=:evaluation";
            List<Scores> resultScores = entityManager.createQuery(queryScores, Scores.class)
                    .setParameter("user", userID)
                    .setParameter("evaluation", evaluations.getId())
                    .getResultList();
            if(!resultScores.isEmpty()){
                EvaluationResolvedDTO evaluationResolvedDTO = new EvaluationResolvedDTO();
                evaluationResolvedDTO.setEnd(evaluations.getEnd());
                evaluationResolvedDTO.setStart(evaluations.getStart());
                evaluationResolvedDTO.setId(evaluations.getId());
                evaluationResolvedDTO.setScore(resultScores.get(0).getScore());
                responseEvaluations.add(evaluationResolvedDTO);
            }
        });

        return responseEvaluations;
    }

    @Override
    public boolean removeEvaluation(int parseInt, Integer evaluationID) {
        Evaluations result = entityManager.find(Evaluations.class, evaluationID);
        result.setStatus(0);
        entityManager.flush();
        return true;

    }

    @Override
    public List<EvaluationResultDTO> getResolvedStudentEvaluation(int parseInt, Integer evaluationID) {
        String queryScore = "FROM Scores WHERE evaluation=:evaluation";
        List<Scores> resultScores = entityManager.createQuery(queryScore, Scores.class)
                .setParameter("evaluation", evaluationID)
                .getResultList();

        List<EvaluationResultDTO> evaluationsResult = new ArrayList<>();

        resultScores.forEach(score -> {
            String queryUser = "FROM Users WHERE id=:user and status=1";
            List<Users> resultUser = entityManager.createQuery(queryUser, Users.class)
                    .setParameter("user", score.getUser())
                    .getResultList();

            if(!resultUser.isEmpty()) {
                EvaluationResultDTO evaluationResultDTO = new EvaluationResultDTO();
                evaluationResultDTO.setCodUsr(resultUser.get(0).getCode());
                evaluationResultDTO.setUsername(resultUser.get(0).getName());
                evaluationResultDTO.setScore(score.getScore());
                evaluationsResult.add(evaluationResultDTO);
            }

        });
        return evaluationsResult;
    }


}
