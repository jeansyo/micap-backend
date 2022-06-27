package com.educational.educational.dao;

import ch.qos.logback.core.CoreConstants;
import com.educational.educational.models.Courses;
import com.educational.educational.models.Materials;
import com.educational.educational.models.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MaterialDaoImp implements MaterialDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Materials> getMaterials(Integer userID, Integer courseID) {
        String queryCourse = "FROM Courses WHERE user = :user AND id = :course AND status = 1";

        List<Courses> resultCourse = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("user", userID)
                .setParameter("course", courseID)
                .getResultList();

        if( resultCourse.isEmpty() ) {
            return null;
        }

        if(resultCourse.get(0).getUser() != userID ) {
            return null;
        }

        String queryMaterials = "FROM Materials WHERE course = :course AND status = 1";

        return entityManager.createQuery(queryMaterials, Materials.class)
                .setParameter("course", courseID)
                .getResultList();

    }

    @Override
    public boolean createMaterial(Materials material, Integer userID) {

        String query = "FROM Courses WHERE id = :course AND user = :user AND status = 1";

        List<Courses> resultCourse = entityManager.createQuery(query, Courses.class)
                        .setParameter("course", material.getCourse())
                        .setParameter("user", userID)
                        .getResultList();

        if( resultCourse.isEmpty() ) {
            return false;
        }

        entityManager.merge(material);

        return true;
    }

    @Override
    public boolean deleteMaterial(Integer courseID, Integer materialID, Integer userID) {

        String queryCourse = "FROM Courses WHERE id = :course AND user = :user AND status = 1";
        List<Courses> resultCourse =  entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .setParameter("user", userID)
                .getResultList();

        if(resultCourse.isEmpty()) {
            return false;
        }

        Materials resultMaterial = entityManager.find(Materials.class, materialID);

        if(resultMaterial == null) {
            return false;
        }

        if( resultMaterial.getCourse() != courseID ) {
            return false;
        }

        if(resultMaterial.getStatus() == 0) {
            return false;
        }

        resultMaterial.setStatus(0);
        entityManager.flush();
        return true;

    }


    @Override
    public List<Materials> getMaterialsRecent(Integer userID) {

        String queryUser = "FROM Users WHERE id = :user AND status = 1";
        List<Users> resultUser = entityManager.createQuery(queryUser, Users.class)
                .setParameter("user", userID)
                .getResultList();

        if(resultUser.isEmpty()) {
            return null;
        }

        ArrayList<Materials> resultMaterialRecent = new ArrayList<Materials>();

        String queryCourses = "FROM Courses WHERE user = :user AND status = 1";
        List<Courses> resultCourses = entityManager.createQuery(queryCourses, Courses.class)
                .setParameter("user", userID)
                .getResultList();

        if (resultCourses.isEmpty()) {
            return resultMaterialRecent;
        }

        Integer coursesSize = resultCourses.size();
        System.out.println(coursesSize);

        for(int i = 0; i < coursesSize; i++) {
            System.out.println(i);
            System.out.println(resultCourses.get(i).getName());

            String queryCurrentMaterialOfCourse = "FROM Materials WHERE course = :course AND status = 1";
            List<Materials> resultCurrentMaterialOfCourse = entityManager.createQuery(queryCurrentMaterialOfCourse, Materials.class)
                    .setParameter("course", resultCourses.get(i).getId())
                    .getResultList();

            if (!resultCurrentMaterialOfCourse.isEmpty()) {
                System.out.println(resultCurrentMaterialOfCourse.get(0).getCourse());
                resultMaterialRecent.add(resultCurrentMaterialOfCourse.get(i));
            }

        }

        return resultMaterialRecent;

    }
}
