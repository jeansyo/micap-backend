package com.educational.educational.dao;

import com.educational.educational.beans.CourseBean;
import com.educational.educational.models.Courses;

import java.util.List;

public interface CourseDao {
    List<CourseBean> getCourses(String userID);

    Courses createCourse(Courses course);

    boolean deleteCourse(Integer userID, Integer courseID);

    Courses getCourseById(Integer courseID);

    List<Courses> getMyCourses(Integer userID);
}
