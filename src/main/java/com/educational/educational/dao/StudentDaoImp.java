package com.educational.educational.dao;

import com.educational.educational.beans.StudentBean;
import com.educational.educational.models.Courses;
import com.educational.educational.models.Students;
import com.educational.educational.models.Users;
import com.educational.educational.schemas.StudentsUserSchema;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class StudentDaoImp implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Users addStudentToCourse(Integer userID, Integer courseID, String studentCode) {

        String queryStudent = "FROM Users WHERE code = :code AND status = 1";

        List<Users> resultStudent = entityManager.createQuery(queryStudent, Users.class)
                .setParameter("code", studentCode)
                .getResultList();

        if( resultStudent.isEmpty() ) {
            return null;
        }

        String queryCourse = "FROM Courses WHERE id = :course AND user = :user AND status = 1";
        List<Courses> resultCourses = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .setParameter("user", userID)
                .getResultList();

        if(resultCourses.isEmpty()) {
            return null;
        }

        String queryStudents = "FROM Students WHERE course = :course AND user = :user";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("course", courseID)
                .setParameter("user", resultStudent.get(0).getId())
                .getResultList();


        if (!resultStudents.isEmpty()) {
            return null;
        }

        Students newStudent = new Students();
        newStudent.setCourse(courseID);
        newStudent.setUser(resultStudent.get(0).getId());

        entityManager.merge(newStudent);

        return resultStudent.get(0);

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

    @Override
    public List<StudentBean> getStudents(Integer courseID) {

        String queryCourse = "FROM Courses WHERE id = :course AND status = 1";
        List<Courses> resultCourses = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .getResultList();

        if( resultCourses.isEmpty() ) {
            return null;
        }

        String queryStudents = "FROM Students WHERE course = :course";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("course", courseID)
                .getResultList();

        Stream<StudentBean> resultUsers = resultStudents.stream().map(students -> {
            String queryUser = "FROM Users WHERE id = :student AND status = 1";

            List<Users> resultUser = entityManager.createQuery(queryUser, Users.class)
                    .setParameter("student", students.getUser())
                    .getResultList();

            StudentBean newStudentUser = new StudentBean();
            if(!resultUser.isEmpty()) {

                newStudentUser.setCode(resultUser.get(0).getCode());
                newStudentUser.setName(resultUser.get(0).getName());
                newStudentUser.setEmail(resultUser.get(0).getEmail());

                return newStudentUser;
            }

            return newStudentUser;
        });

        List<StudentBean> usersList = resultUsers.collect(Collectors.toList());


        return usersList;

    }
}
