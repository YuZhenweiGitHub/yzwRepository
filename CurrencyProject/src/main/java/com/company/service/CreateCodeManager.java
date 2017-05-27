package com.company.service;

import com.company.utils.Page;
import com.company.utils.PageData;

import java.util.List;

/**
 * 类名称：代码生成器接口类
 * @version
 */
public interface CreateCodeManager {
	
	/**
	 * 新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**
	 * 列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**
	 * 通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**
	 * 批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	public List<PageData> columnlist(String tablename)throws Exception;
	
	public List<PageData> tablelist(String tablename)throws Exception;
	
}
