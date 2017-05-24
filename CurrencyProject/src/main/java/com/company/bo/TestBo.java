package com.company.bo;

import com.company.dao.DaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yzw on 2017/4/27.
 */
@Component
public class TestBo {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<Object> queryEntity(Map map) throws Exception {
        return (List<Object>) dao.findForList("PublicMapper.getNativeList",map);
    }

}
