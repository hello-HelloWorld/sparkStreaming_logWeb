package com.ssm.dao.impl;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 17:20
 */

import com.ssm.dao.CourseClickCountDao;
import com.ssm.domain.Course;
import com.ssm.utils.HBaseUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//课程点击统计Dao实现类
@Repository("CourseClickCountDao")
public class CourseClickCountDaoImpl extends SqlSessionDaoSupport implements CourseClickCountDao {
    @Override
    public Set<Course> findCourseCount(String tableName, String date) {
        Map<String, Long> map = new HashMap<>();
        Course course = null;
        Set<Course> set = new TreeSet<>();
        map = HBaseUtils.getInstance().getClickCount(tableName, date);
        Set<Map.Entry<String, Long>> entries = map.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            //Rowkey的结构:20190330_112,112为课程的id
            int id = Integer.parseInt(entry.getKey().substring(9));
            //查询课程名并封装进bean
            course = getSqlSession().selectOne("course.findCourseName", id);
            //将课程点击数封装进bean
            course.setCount(entry.getValue());
            set.add(course);
        }
        return set;
    }
}
