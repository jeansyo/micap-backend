package com.educational.educational.dao;

import com.educational.educational.beans.MaterialRecentBean;
import com.educational.educational.beans.PercentageBean;
import com.educational.educational.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class MaterialDaoImp implements MaterialDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Materials> getMaterials(Integer userID, Integer courseID) {

        String queryMaterials = "FROM Materials WHERE course = :course AND status = 1";

        return entityManager.createQuery(queryMaterials, Materials.class)
                .setParameter("course", courseID)
                .getResultList();

    }

    @Override
    public Materials createMaterial(Materials material, Integer userID) {

        String query = "FROM Courses WHERE id = :course AND user = :user AND status = 1";

        List<Courses> resultCourse = entityManager.createQuery(query, Courses.class)
                        .setParameter("course", material.getCourse())
                        .setParameter("user", userID)
                        .getResultList();

        if( resultCourse.isEmpty() ) {
            return null;
        }

        Materials newMaterial = entityManager.merge(material);

        return newMaterial;

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
    public List<MaterialRecentBean> getMaterialsRecent(Integer userID) {

        String queryUser = "FROM Users WHERE id = :user AND status = 1";
        List<Users> resultUser = entityManager.createQuery(queryUser, Users.class)
                .setParameter("user", userID)
                .getResultList();

        if(resultUser.isEmpty()) {
            return null;
        }

        ArrayList<MaterialRecentBean> resultsMaterialByCourse = new ArrayList<MaterialRecentBean>();

        String queryStudents = "FROM Students WHERE user = :user";
        List<Students> resultStudents = entityManager.createQuery(queryStudents, Students.class)
                .setParameter("user", userID)
                .getResultList();

        if (resultStudents.isEmpty()) {
            return resultsMaterialByCourse;
        }

        resultStudents.forEach(student -> {

            String queryCurrentCourse = "FROM Courses WHERE id = :course AND status = 1";
            String queryCurrentMaterialOfCourse = "FROM Materials WHERE course = :course AND status = 1";


            List<Materials> resultCurrentMaterialOfCourse = entityManager.createQuery(queryCurrentMaterialOfCourse, Materials.class)
                    .setParameter("course", student.getCourse())
                    .getResultList();

            List<Courses> resultCurrentCourse = entityManager.createQuery(queryCurrentCourse, Courses.class)
                    .setParameter("course", student.getCourse())
                    .getResultList();

            if( !resultCurrentMaterialOfCourse.isEmpty() ) {

                resultCurrentMaterialOfCourse.forEach(material -> {

                    MaterialRecentBean materialRecentBean = new MaterialRecentBean();

                    materialRecentBean.setCourseID(material.getCourse());
                    materialRecentBean.setCourseName(resultCurrentCourse.get(0).getName());

                    materialRecentBean.setDate(material.getDate());
                    materialRecentBean.setId(material.getId());
                    materialRecentBean.setLink(material.getLink());
                    materialRecentBean.setName(material.getName());
                    materialRecentBean.setType(material.getType());
                    materialRecentBean.setFilename(material.getFilename());

                    resultsMaterialByCourse.add(materialRecentBean);

                });
            }
        });

        return resultsMaterialByCourse;

    }

    @Override
    public boolean downloadMaterial(Downloads newDownload) {

        String queryCourse = "FROM Courses WHERE id = :course AND status = 1";
        List<Courses> responseCourse = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", newDownload.getCourse())
                .getResultList();

        if(responseCourse.isEmpty()) {
            return false;
        }

        String queryMaterial = "FROM Materials WHERE id = :material AND status=1";
        List<Materials> responseMaterial = entityManager.createQuery(queryMaterial, Materials.class)
                .setParameter("material", newDownload.getMaterial())
                .getResultList();

        if( responseMaterial.isEmpty() ) {
            return false;
        }

        String queryDownload = "FROM Downloads WHERE user = :user AND course = :course AND material = :material";
        List<Downloads> responseDownload = entityManager.createQuery(queryDownload, Downloads.class)
                .setParameter("user", newDownload.getUser())
                .setParameter("course", newDownload.getCourse())
                .setParameter("material", newDownload.getMaterial())
                .getResultList();

        if(responseDownload.size() > 0) {
            return false;
        }

        Downloads saveDownload = entityManager.merge(newDownload);
        return true;
    }

    @Override
    public PercentageBean downloadPercentageMaterial(Integer userID, Integer courseID) {

        String queryCourse = "FROM Courses WHERE id = :course AND status=1";
        List<Courses> responseCourse = entityManager.createQuery(queryCourse, Courses.class)
                .setParameter("course", courseID)
                .getResultList();

        if(responseCourse.isEmpty()) {
            return null;
        }

        String queryMaterials = "FROM Materials WHERE course = :course AND status=1";
        List<Materials> responseMaterial = entityManager.createQuery(queryMaterials, Materials.class)
                .setParameter("course", courseID)
                .getResultList();

        String queryDownload = "FROM Downloads WHERE user = :user AND course = :course";
        List<Downloads> responseDownloads = entityManager.createQuery(queryDownload, Downloads.class)
                .setParameter("user", userID)
                .setParameter("course", courseID)
                .getResultList();

        Integer sizeMaterials = responseMaterial.size();
        Integer sizeDownload = responseDownloads.size();

        PercentageBean percentageBean = new PercentageBean();
        percentageBean.setTotal(sizeMaterials);
        percentageBean.setDownloads(sizeDownload);

        return percentageBean;
    }
}
