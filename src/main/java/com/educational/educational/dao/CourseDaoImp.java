package com.educational.educational.dao;

import com.educational.educational.beans.CourseBean;
import com.educational.educational.models.Courses;
import com.educational.educational.models.Students;
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
}
