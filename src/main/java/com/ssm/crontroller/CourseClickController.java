package com.ssm.crontroller;

import com.ssm.domain.Course;
import com.ssm.service.CourseClickService;
import net.sf.json.JSONArray;
import org.apache.hadoop.yarn.api.records.impl.pb.PreemptionResourceRequestPBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//课程点击Controller
@Controller
public class CourseClickController {

    @Autowired
    private CourseClickService courseClickService;

    //页面跳转
    @RequestMapping("/courseclick")
    public String toGetCourseClick() {
        return "courseClick";
    }

    /**
     * sponseBody注解的作用是将controller的方法返回的对象通过适当的转换器转
     * 换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseClickCount", method = RequestMethod.GET)
    public JSONArray courseClickCount(String date) {
        //如果url没有传值，传入一个默认值
        if (date == null || "".equals(date)) {
            date = "20190330";
        }
        List<Course> courseClickCount = courseClickService.findCourseClickCount("ns1:courses_clickcount", date);
        //封转json数据
        return JSONArray.fromObject(courseClickCount);
    }
}
