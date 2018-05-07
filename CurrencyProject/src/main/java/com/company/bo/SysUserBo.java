package com.company.bo;

import com.company.dao.DaoSupport;
import com.company.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;
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

    public PageInfo<PageData> findUserInfoByUserName_PageHelper(PageData pd) throws Exception {

        Integer pageNo = pd.get("pageNo") == null ? 1 : pd.getInt("pageNo");
        Integer pageSize = pd.get("pageSize") == null ? 10 : pd.getInt("pageSize");
        PageHelper.startPage(pageNo, pageSize);
        List<PageData> list = (List<PageData>) dao.findForList("UserInfoMapper.datalistPage" , pd);
        //用PageInfo对结果进行包装
        PageInfo<PageData> page = new PageInfo<PageData>(list);
        //测试PageInfo全部属性
        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getFirstPage());
        System.out.println(page.getLastPage());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
        return page;
    }

    public Boolean checkUserName(String userName) throws Exception {
        Integer count = (Integer) dao.findForObject("UserInfoMapper.checkUserName",userName);
        if (count>0) {
            return false;
        } else {
            return true;
        }
    }

}
