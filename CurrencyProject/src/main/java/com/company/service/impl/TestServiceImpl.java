package com.company.service.impl;

import com.company.bo.TestBo;
import com.company.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yzw on 2017/4/27.
 */
@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestBo testBo;

    @Override
    public List<Object> queryEntity(Map map) throws Exception {
        return testBo.queryEntity(map);
    }
}
