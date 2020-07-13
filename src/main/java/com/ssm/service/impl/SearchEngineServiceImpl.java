package com.ssm.service.impl;

import com.ssm.dao.SearchEngineCountDao;
import com.ssm.domain.SearchEngine;
import com.ssm.service.SearchEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEngineServiceImpl implements SearchEngineService {

    @Autowired
    private SearchEngineCountDao searchEngineCountDao;

    @Override
    public List<SearchEngine> findSearchEngine(String tableName, String date) {
        List<SearchEngine> list = searchEngineCountDao.findSearchEngineCount(tableName, date);
        return list;
    }
}
