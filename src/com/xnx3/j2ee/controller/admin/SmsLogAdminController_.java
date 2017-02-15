package com.xnx3.j2ee.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xnx3.j2ee.Global;
import com.xnx3.j2ee.controller.BaseController;
import com.xnx3.j2ee.entity.Permission;
import com.xnx3.j2ee.entity.SmsLog;
import com.xnx3.j2ee.entity.User;
import com.xnx3.j2ee.service.SqlService;
import com.xnx3.j2ee.util.Page;
import com.xnx3.j2ee.util.Sql;

/**
 * 手机号
 * @author 管雷鸣
 */
@Controller
@RequestMapping("/admin/smslog")
public class SmsLogAdminController_ extends BaseController {
	
	@Resource
	private SqlService sqlService;
	
	@RequiresPermissions("adminSmsLogList")
	@RequestMapping("list")
	public String list(HttpServletRequest request,Model model){
//		Sql sql = new Sql();
//		String[] column = {"phone","used=","userid="};
//		String where = sql.generateWhere(request, column, null);
//		int count = sqlService.count("sms_log", where);
//		Page page = new Page(count, Global.PAGE_ADMIN_DEFAULT_EVERYNUMBER, request);
//		page.setDefaultOrderBy("id_DESC");
//		List<User> list = sqlService.findBySqlQuery("SELECT * FROM sms_log", where, page,SmsLog.class);
		Sql sql = new Sql(request);
		sql.setSearchColumn(new String[]{"phone","used=","userid="});
		int count = sqlService.count("sms_log", sql.getWhere());
		Page page = new Page(count, Global.PAGE_ADMIN_DEFAULT_EVERYNUMBER, request);
		sql.setSelectFromAndPage("SELECT * FROM sms_log", page);
		sql.setDefaultOrderBy("sms_log.id DESC");
		List<SmsLog> list = sqlService.findEntityBySql(sql, SmsLog.class);
		
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "/iw/admin/smslog/list";
	}
	
}
