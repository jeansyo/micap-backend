package com.educational.educational.dao;

import com.educational.educational.beans.StudentBean;
import com.educational.educational.models.Users;
import com.educational.educational.schemas.StudentsUserSchema;

import java.util.List;

public interface StudentDao {
    Users addStudentToCourse(Integer userID, Integer courseID, String studentCode);

    boolean removeStudentToCourse(Integer userID, Integer courseID, String studentCode);

    List<StudentBean> getStudents(Integer courseID);
}
