package com.ssm.domain;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 17:09
 */

/**
 * 课程类,实现Comparable接口用于排序
 */
public class Course implements Comparable<Course> {

    private int id;//课程编号
    private String name;//课程名
    private Long count;//点击次数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    //降序排序
    @Override
    public int compareTo(Course course) {
        return course.count.intValue() - this.count.intValue();
    }
}
