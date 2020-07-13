package com.ssm.crontroller;

import com.ssm.domain.SearchEngine;
import com.ssm.service.SearchEngineService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//搜索引擎点击Controller
@Controller
public class SearchEngineController {

    @Autowired
    private SearchEngineService service;

    //页面跳转
    @RequestMapping("/searchclick")
    public String toGetCourseClick() {
        return "searchclick";
    }

    /**
     *  sponseBody注解的作用是将controller的方法返回的对象通过适当的转换器转
     *  换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML
     */
    @ResponseBody
    @RequestMapping(value = "/getSearchClickCount",method = RequestMethod.GET)
    public JSONArray searchClick(String date){
        //如果url没有传值，传入一个默认值
        if(date == null || date.equals("")){
            date = "20190330";
        }
        List<SearchEngine> searchEngine = service.findSearchEngine("ns1:courses_search_clickcount", date);
        return JSONArray.fromObject(searchEngine);
    }
}
