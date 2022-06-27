package com.educational.educational.dao;

import com.educational.educational.models.Courses;
import com.educational.educational.models.Students;
import com.educational.educational.models.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImp implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean addStudentToCourse(Integer userID, Integer courseID, String studentCode) {

        String queryStudent = "FROM Users WHERE code = :code AND status = 1";

        List<Users> resultStudent = entityManager.createQuery(queryStudent, Users.class)
                .setParameter("code", studentCode)
                .getResultList();

        if( resultStudent.isEmpty() ) {
            return false;
        }

        String queryCourse = "FROM Courses WHERE id = :course AND user = :user AND status = 1";
        List<Courses> resultCourses = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .setParameter("user", userID)
                .getResultList();

        if(resultCourses.isEmpty()) {
            return false;
        }

        String queryStudents = "FROM Students WHERE course = :course AND user = :user";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("course", courseID)
                .setParameter("user", resultStudent.get(0).getId())
                .getResultList();

        if (!queryStudents.isEmpty()) {
            return false;
        }

        Students newStudent = new Students();
        newStudent.setCourse(courseID);
        newStudent.setUser(resultStudent.get(0).getId());

        entityManager.merge(newStudent);

        return true;

    }

    @Override
    public boolean removeStudentToCourse(Integer userID, Integer courseID, String studentCode) {
        String queryStudent = "FROM Users WHERE code = :code AND status = 1";

        List<Users> resultStudent = entityManager.createQuery(queryStudent, Users.class)
                .setParameter("code", studentCode)
                .getResultList();

        if( resultStudent.isEmpty() ) {
            return false;
        }

        String queryCourse = "FROM Courses WHERE id = :course AND user = :user AND status = 1";
        List<Courses> resultCourses = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .setParameter("user", userID)
                .getResultList();

        if(resultCourses.isEmpty()) {
            return false;
        }

        String queryStudents = "FROM Students WHERE course = :course AND user = :user";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("course", courseID)
                .setParameter("user", resultStudent.get(0).getId())
                .getResultList();

        if (queryStudents.isEmpty()) {
            return false;
        }

        entityManager.remove(resultStudents.get(0));

        return true;
    }
}
