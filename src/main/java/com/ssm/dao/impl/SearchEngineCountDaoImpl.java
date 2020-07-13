package com.ssm.dao.impl;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 17:56
 */

import com.ssm.dao.SearchEngineCountDao;
import com.ssm.domain.SearchEngine;
import com.ssm.utils.HBaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//搜索引擎统计Dao实现类
public class SearchEngineCountDaoImpl extends SqlSessionDaoSupport implements SearchEngineCountDao {
    @Override
    public List<SearchEngine> findSearchEngineCount(String tableName, String date) {
        List<SearchEngine> list = new ArrayList<>();
        SearchEngine searchEngine = null;
        Map<String, Long> map = HBaseUtils.getInstance().getClickCount(tableName, date);
        //将map转换为set，进行遍历
        Set<Map.Entry<String, Long>> entries = map.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            //取出搜索引擎的名字
            String name = entry.getKey().split("\\.")[1];
            //通过名字查询，将点击数量封进bean
            searchEngine = getSqlSession().selectOne("searchEngine.findEngineName", name);
            searchEngine.setCount(entry.getValue());
            list.add(searchEngine);
        }
        return list;
    }
}
