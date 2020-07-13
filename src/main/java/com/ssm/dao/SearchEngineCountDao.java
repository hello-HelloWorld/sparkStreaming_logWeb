package com.ssm.dao;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 17:17
 */

import com.ssm.domain.SearchEngine;

import java.util.List;

//搜索引擎dao
public interface SearchEngineCountDao {
    public List<SearchEngine> findSearchEngineCount(String tableName, String date);
}
