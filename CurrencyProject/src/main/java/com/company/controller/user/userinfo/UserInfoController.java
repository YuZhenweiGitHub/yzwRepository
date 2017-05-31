package com.company.controller.user.userinfo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
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
import com.company.service.user.userinfo.UserInfoManager;

/**
 * 说明：用户表
 * 创建时间：2017-05-31
 */
@Controller
@RequestMapping(value="/userinfo")
public class UserInfoController extends BaseController {

	String menuUrl = "userinfo/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;

	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ID_", this.get32UUID());	//主键
		if(null != pd.get("UU_ID_")){
			pd.put("UU_ID_", pd.get("UU_ID_"));
		}
		if(null != pd.get("USER_NAME_")){
			pd.put("USER_NAME_", pd.get("USER_NAME_"));
		}
		if(null != pd.get("USERE_NICK_NAME_")){
			pd.put("USERE_NICK_NAME_", pd.get("USERE_NICK_NAME_"));
		}
		if(null != pd.get("PASS_WORD_")){
			pd.put("PASS_WORD_", pd.get("PASS_WORD_"));
		}
		if(null != pd.get("USERE_EMAIL_")){
			pd.put("USERE_EMAIL_", pd.get("USERE_EMAIL_"));
		}
		if(null != pd.get("USER_MOBILE_")){
			pd.put("USER_MOBILE_", pd.get("USER_MOBILE_"));
		}
		if(null != pd.get("SYS_ROLE_ID_")){
			pd.put("SYS_ROLE_ID_", pd.get("SYS_ROLE_ID_"));
		}
		if(null != pd.get("SEX_")){
			pd.put("SEX_", pd.get("SEX_"));
		}
		if(null != pd.get("AVATAR_")){
			pd.put("AVATAR_", pd.get("AVATAR_"));
		}
		if(null != pd.get("USER_STATUS_")){
			pd.put("USER_STATUS_", pd.get("USER_STATUS_"));
		}
		if(null != pd.get("REGISTER_TIME_")){
			pd.put("REGISTER_TIME_", pd.get("REGISTER_TIME_"));
		}
		if(null != pd.get("LAST_LOGIN_IP_")){
			pd.put("LAST_LOGIN_IP_", pd.get("LAST_LOGIN_IP_"));
		}
		if(null != pd.get("LAST_LOGIN_TIME_")){
			pd.put("LAST_LOGIN_TIME_", pd.get("LAST_LOGIN_TIME_"));
		}
		if(null != pd.get("DELTE_FLAG_")){
			pd.put("DELTE_FLAG_", pd.get("DELTE_FLAG_"));
		}
		userinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return new ModelAndView("redirect:/userinfo/list");
	}

	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.delete(pd);
		out.write("success");
		out.close();
	}

	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return new ModelAndView("redirect:/userinfo/list");
	}

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表UserInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = userinfoService.list(page);	//列出UserInfo列表
		mv.setViewName("user/userinfo/userinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("user/userinfo/userinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}

	/**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = userinfoService.findById(pd);	//根据ID读取
		mv.setViewName("user/userinfo/userinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			userinfoService.deleteAll(ArrayDATA_IDS);
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
