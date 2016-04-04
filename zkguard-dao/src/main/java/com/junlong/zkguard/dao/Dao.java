package com.junlong.zkguard.dao;

import java.util.List;

/**
 * Created by niuniu on 2016/3/28.
 */
public interface Dao<E> {
    Object save(String str,Object obj);
    Object saveOrUpdate(String str,Object obj);
    Object savebatch(String str,List<E> list);
    Object update(String str,Object obj);
    Object delete(String str,Object obj);
    E findForParam(String str,Object obj);
    List<E> findForList(String str,Object obj);
    Long getCount(String str);
}
