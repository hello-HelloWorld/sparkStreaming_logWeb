package com.ssm.service;

import com.ssm.domain.Course;

import java.util.List;

//课程点击统计Service类
public interface CourseClickService {
    public List<Course> findCourseClickCount(String table, String date);
}
