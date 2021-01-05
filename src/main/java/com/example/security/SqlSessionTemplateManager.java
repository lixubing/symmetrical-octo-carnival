package com.example.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("sqlSessionTemplateManager")
public class SqlSessionTemplateManager  extends SqlSessionDaoSupport {
    @Override
    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Object selectOne(String statement, Object parameter){
        return getSqlSessionTemplate().selectOne(statement, parameter);
    }
}
