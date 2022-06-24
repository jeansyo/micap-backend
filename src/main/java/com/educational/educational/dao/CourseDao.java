package com.educational.educational.dao;

import com.educational.educational.models.Courses;

import java.util.List;

public interface CourseDao {
    List<Courses> getCoursesByID(String userID);

    boolean createCourse(Courses course);

    boolean deleteCourse(Integer userID, Integer courseID);
}
