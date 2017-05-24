package com.company.bo;

import com.company.dao.DaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yzw on 2017/5/23.
 */
@Component
public class SysUserBo {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<Object> findVerifyLogin(Map map) throws Exception {
        return (List<Object>) dao.findForList("sysUserMapper.findVerifyLogin",map);
    }
}
