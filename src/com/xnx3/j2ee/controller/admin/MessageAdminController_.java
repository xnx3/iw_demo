package com.xnx3.j2ee.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xnx3.j2ee.Global;
import com.xnx3.j2ee.entity.Message;
import com.xnx3.j2ee.service.SqlService;
import com.xnx3.j2ee.service.LogService;
import com.xnx3.j2ee.service.MessageDataService;
import com.xnx3.j2ee.service.MessageService;
import com.xnx3.j2ee.controller.BaseController;
import com.xnx3.j2ee.util.Page;
import com.xnx3.j2ee.util.Sql;
import com.xnx3.j2ee.vo.BaseVO;

/**
 * 信息管理
 * @author 管雷鸣
 *
 */
@Controller
@RequestMapping("/admin/message")
public class MessageAdminController_ extends BaseController {

	@Resource
	private MessageService messageService;
	
	@Resource
	private MessageDataService messageDataService;
	
	@Resource
	private SqlService sqlService;
	
	@Resource
	private LogService logService;
	
	/**
	 * 信息列表
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * @return View
	 */
	@RequiresPermissions("adminMessageList")
	@RequestMapping("list")
	public String list(HttpServletRequest request,Model model){
		Sql sql = new Sql(request);
		sql.setSearchTable("message");
		sql.setSearchColumn(new String[]{"id=","senderid=","recipientid="});
		sql.appendWhere("message.isdelete = "+Message.ISDELETE_NORMAL);
		int count = sqlService.count("message", sql.getWhere());
		Page page = new Page(count, Global.PAGE_ADMIN_DEFAULT_EVERYNUMBER, request);
		sql.setSelectFromAndPage("SELECT message.*,message_data.content, (SELECT user.nickname FROM user WHERE user.id=message.recipientid) AS other_nickname ,(SELECT user.nickname FROM user WHERE user.id=message.senderid) AS self_nickname FROM message ,message_data ,user ", page);
		sql.appendWhere("message.id=message_data.id");
		sql.setGroupBy("message.id");
		sql.setDefaultOrderBy("message.id DESC");
		List<Map<String, Object>> list = sqlService.findMapBySql(sql);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/iw/admin/message/list";
	}
	
	/**
	 * 删除信息
	 * @param id 信息的id，Message.id
	 * @param model {@link Model}
	 * @return View
	 */
	@RequiresPermissions("adminMessageDelete")
	@RequestMapping("delete")
	public String delete(@RequestParam(value = "id", required = true) int id, Model model){
		BaseVO baseVO = messageService.delete(id);
		if(baseVO.getResult() == BaseVO.SUCCESS){
			return success(model, "删除成功！");
		}else{
			return error(model, baseVO.getInfo());
		}
	}
}
