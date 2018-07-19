package com.company.service.menuinfo.impl;

import java.util.List;
import javax.annotation.Resource;

import com.company.service.menuinfo.MenuInfoManager;
import org.springframework.stereotype.Service;
import com.company.dao.DaoSupport;
import com.company.utils.Page;
import com.company.utils.PageData;

/** 
 * 说明： 菜单表
 * 创建时间：2017-08-12
 * @version
 */
@Service("menuinfoService")
public class MenuInfoService implements MenuInfoManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MenuInfoMapper.save", pd);
	}
	
	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MenuInfoMapper.delete", pd);
	}
	
	/**
     * 修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MenuInfoMapper.edit", pd);
	}
	
	/**
	 * 列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MenuInfoMapper.datalistPage", page);
	}
	
	/**
	 * 列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MenuInfoMapper.listAll", pd);
	}
	
	/**
     * 通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MenuInfoMapper.findById", pd);
	}
	
	/**
     * 批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MenuInfoMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

