package com.company.bo;

import com.company.dao.DaoSupport;
import com.company.utils.PageData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yzw on 2017/5/23.
 */
@Component
public class SysUserBo {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<PageData> findUserInfoByUserName(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserInfoMapper.findUserInfoByUserName" , pd);
    }
}
