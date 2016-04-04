package com.junlong.zkguard.dao.impl;

import com.junlong.zkguard.dao.Dao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by niuniu on 2016/3/28.
 */
@Repository("daoSupport")
public class DaoSupport<E> implements Dao<E> {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Object save(String str, Object obj) {
        return sqlSessionTemplate.insert(str, obj);
    }

    @Override
    public Object saveOrUpdate(String str, Object obj) {
        return sqlSessionTemplate.insert(str, obj);
    }

    @Override
    public Object savebatch(String str, List<E> ks) {
        return sqlSessionTemplate.insert(str, ks);
    }


    @Override
    public Object update(String str, Object obj) {
        return sqlSessionTemplate.update(str, obj);
    }

    @Override
    public Object delete(String str, Object obj) {
        return sqlSessionTemplate.delete(str, obj);
    }

    @Override
    public E findForParam(String str, Object obj) {
        return sqlSessionTemplate.selectOne(str, obj);
    }

    @Override
    public List<E> findForList(String str, Object obj) {
        return sqlSessionTemplate.selectList(str, obj);
    }

    @Override
    public Long getCount(String str) {
        return sqlSessionTemplate.selectOne(str);
    }
}
