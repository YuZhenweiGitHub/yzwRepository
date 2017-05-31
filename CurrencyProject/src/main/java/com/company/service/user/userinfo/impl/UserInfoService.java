package com.company.service.user.userinfo.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.company.bo.SysUserBo;
import com.company.dao.DaoSupport;
import com.company.service.user.userinfo.UserInfoManager;
import com.company.utils.Page;
import com.company.utils.PageData;
import org.springframework.stereotype.Service;

/** 
 * 说明： 用户表
 * 创建时间：2017-05-31
 * @version
 */
@Service("userInfoService")
public class UserInfoService implements UserInfoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource
	private SysUserBo sysUserBo;

	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("UserInfoMapper.save", pd);
	}
	
	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("UserInfoMapper.delete", pd);
	}
	
	/**
	 * 修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("UserInfoMapper.edit", pd);
	}
	
	/**
	 * 列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("UserInfoMapper.datalistPage", page);
	}
	
	/**
	 * 列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("UserInfoMapper.listAll", pd);
	}
	
	/**
	 * 通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserInfoMapper.findById", pd);
	}
	
	/**
	 * 批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("UserInfoMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public List<Object> findVerifyLogin(Map map) throws Exception {
		return sysUserBo.findVerifyLogin(map);
	}
}

