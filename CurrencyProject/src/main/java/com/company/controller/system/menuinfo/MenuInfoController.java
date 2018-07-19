package com.company.controller.system.menuinfo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.company.service.menuinfo.MenuInfoManager;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.company.controller.system.BaseController;
import com.company.utils.Page;
import com.company.utils.AppUtil;
import com.company.utils.PageData;
import com.company.utils.Jurisdiction;

/** 
 * 说明：菜单表
 * 创建时间：2017-08-12
 */
@Controller
@RequestMapping(value="/menuinfo")
public class MenuInfoController extends BaseController {
	
	String menuUrl = "menuinfo/list.do"; //菜单地址(权限用)

	@Resource(name="menuinfoService")
	private MenuInfoManager menuinfoService;
	
	/**
	 * 保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增MenuInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ID_", this.get32UUID());	//主键
		if(null != pd.get("MENU_ID_")){
			pd.put("MENU_ID_", pd.get("MENU_ID_"));
		}
		if(null != pd.get("MENU_NAME_")){
			pd.put("MENU_NAME_", pd.get("MENU_NAME_"));
		}
		if(null != pd.get("MENU_LEVEL_")){
			pd.put("MENU_LEVEL_", pd.get("MENU_LEVEL_"));
		}
		if(null != pd.get("MENU_ADDRESS_")){
			pd.put("MENU_ADDRESS_", pd.get("MENU_ADDRESS_"));
		}
		if(null != pd.get("PARENT_ID_")){
			pd.put("PARENT_ID_", pd.get("PARENT_ID_"));
		}
		if(null != pd.get("CREATE_TIME_")){
			pd.put("CREATE_TIME_", pd.get("CREATE_TIME_"));
		}
		if(null != pd.get("CREEATE_USER_ID_")){
			pd.put("CREEATE_USER_ID_", pd.get("CREEATE_USER_ID_"));
		}
		if(null != pd.get("UPDATE_TIME_")){
			pd.put("UPDATE_TIME_", pd.get("UPDATE_TIME_"));
		}
		if(null != pd.get("UPDATE_USER_ID_")){
			pd.put("UPDATE_USER_ID_", pd.get("UPDATE_USER_ID_"));
		}
		menuinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return new ModelAndView("redirect:/menuinfo/list");
	}
	
	/**
     * 删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除MenuInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		menuinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**
	 * 修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改MenuInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		menuinfoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return new ModelAndView("redirect:/menuinfo/list");
	}
	
	/**
	 * 列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表MenuInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = menuinfoService.list(page);	//列出MenuInfo列表
		mv.setViewName("system/menuinfo/menuinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**
     * 去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/menuinfo/menuinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**
     * 去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = menuinfoService.findById(pd);	//根据ID读取
		mv.setViewName("system/menuinfo/menuinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**
     * 批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除MenuInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			menuinfoService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
