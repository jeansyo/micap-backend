package com.educational.educational.dao;

public interface StudentDao {
    boolean addStudentToCourse(Integer userID, Integer courseID, String studentCode);

    boolean removeStudentToCourse(Integer userID, Integer courseID, String studentCode);
}
