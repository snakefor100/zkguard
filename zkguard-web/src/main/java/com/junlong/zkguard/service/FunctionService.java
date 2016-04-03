package com.junlong.zkguard.service;

import com.junlong.zkguard.domain.Function;

import java.util.List;

/**
 * Created by niuniu on 2016/3/29.
 */
public interface FunctionService {
    void saveFunctionBatch(List<Function> list);
    List<Function> getFunctionByType(int type);
}
