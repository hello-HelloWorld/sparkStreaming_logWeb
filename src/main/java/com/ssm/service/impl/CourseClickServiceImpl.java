package com.ssm.service.impl;

import com.ssm.dao.CourseClickCountDao;
import com.ssm.domain.Course;
import com.ssm.service.CourseClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//课程点击统计Service实现类
@Service("CourseClickService")
public class CourseClickServiceImpl implements CourseClickService {

    @Autowired
    private CourseClickCountDao courseClickCountDao;

    @Override
    public List<Course> findCourseClickCount(String table, String date) {
        List<Course> list = new ArrayList<>();
        Set<Course> set = courseClickCountDao.findCourseCount(table, date);
        Iterator<Course> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext() && i++ < 5) {
            list.add(iterator.next());
        }
        return list;
    }
}
