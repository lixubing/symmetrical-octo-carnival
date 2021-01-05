package com.example.security;

import com.example.security.service.FrameWork;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseDo{
    final private SqlSessionTemplateManager manager = (SqlSessionTemplateManager) FrameWork.getApplicationContext().getBean("sqlSessionTemplateManager");
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Object getObject(String statement, Object parameter){
        Object o = manager.selectOne(statement, parameter);
        return o;
    }
}
