package com.junlong.zkguard.service.impl;

import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.dao.Dao;
import com.junlong.zkguard.domain.Function;
import com.junlong.zkguard.domain.QueryRequest;
import com.junlong.zkguard.service.FunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niuniu on 2016/3/29.
 */
@Transactional
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
    @Resource(name="daoSupport")
    private Dao<Function> dao;
    @Override
    public void saveFunctionBatch(List<Function> list) {
        dao.savebatch("FunctionMapper.saveFunctionBatch",list);
    }

    @Override
    public List<Function> getFunctionByType(int type) {
        QueryRequest queryRequest = new QueryRequest();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("functionType",type);
        queryRequest.setFields(param);
        Map<String,String> sortMap = new HashMap<String,String>();
        sortMap.put("sort", ZkConstants.SORT_ASC);
        queryRequest.setSortItemMap(sortMap);
        System.out.println(queryRequest);
        return dao.findForList("FunctionMapper.getFunctionByType",queryRequest);
    }
}
