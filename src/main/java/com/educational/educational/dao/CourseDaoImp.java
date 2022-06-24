package com.educational.educational.dao;

import com.educational.educational.models.Courses;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.Integer.parseInt;

@Repository
@Transactional
public class CourseDaoImp implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Courses> getCoursesByID(String userID) {

        String query = "FROM Courses WHERE user = :user AND status = 1";

        List<Courses> result = entityManager.createQuery(query, Courses.class)
                .setParameter("user", parseInt(userID))
                .getResultList();

//        if(result.isEmpty()) {
//            return null;
//        }

        return result;

    }

    @Override
    public boolean createCourse(Courses course) {

        String query = "FROM Courses WHERE name = :name AND status = 1";

        List<Courses> result = entityManager.createQuery(query, Courses.class)
                .setParameter("name", course.getName())
                .getResultList();

        if(!result.isEmpty()) {
            return false;
        }

        entityManager.merge(course);
        return true;
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
}
