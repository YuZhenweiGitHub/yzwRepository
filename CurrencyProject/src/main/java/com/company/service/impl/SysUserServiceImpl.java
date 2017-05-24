package com.company.service.impl;

import com.company.bo.SysUserBo;
import com.company.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yzw on 2017/4/27.
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserBo sysUserBo;

    @Override
    public List<Object> findVerifyLogin(Map map) throws Exception {
        return sysUserBo.findVerifyLogin(map);
    }
}
