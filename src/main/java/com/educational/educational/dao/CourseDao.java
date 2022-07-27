package com.educational.educational.dao;

import com.educational.educational.beans.CourseBean;
import com.educational.educational.dto.EvalDTO;
import com.educational.educational.dto.EvaluationResolvedDTO;
import com.educational.educational.dto.EvaluationResultDTO;
import com.educational.educational.dto.ScoreDTO;
import com.educational.educational.models.Courses;
import com.educational.educational.models.Evaluations;
import com.educational.educational.models.Questions;

import java.util.List;

public interface CourseDao {
    List<CourseBean> getCourses(String userID);

    Courses createCourse(Courses course);

    boolean deleteCourse(Integer userID, Integer courseID);

    Courses getCourseById(Integer courseID);

    List<Courses> getMyCourses(Integer userID);

    Evaluations createEvaluation(Evaluations evaluations);

    Questions createQuestionForEvaluation(Questions questions);

    List<Evaluations> getEvaluations(Integer courseID);

    List<Questions> getQuestionsOfEvaluationForTeachers(Integer evaluationID);

    ScoreDTO makeTest(List<EvalDTO> evalDTO, Integer userID, Integer testID);

    List<Evaluations> avalaibleEvaluations(Integer userID, Integer courseID);

    List<EvaluationResolvedDTO> resolvedEvaluations(Integer userID, Integer courseID);

    boolean removeEvaluation(int parseInt, Integer evaluationID);

    List<EvaluationResultDTO> getResolvedStudentEvaluation(int parseInt, Integer evaluationID);
}
