package com.ssm.service;

import com.ssm.domain.SearchEngine;

import java.util.List;

//搜索引擎点击Service实现类
public interface SearchEngineService {
    public List<SearchEngine> findSearchEngine(String tableName, String date);
}
