package com.ssm.dao;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 17:14
 */

import com.ssm.domain.Course;

import java.util.Set;

//课程统计结果dao
public interface CourseClickCountDao {
    public Set<Course> findCourseCount(String tableName, String date);
}
