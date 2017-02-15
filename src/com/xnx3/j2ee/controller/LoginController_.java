package com.xnx3.j2ee.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.http.HttpRequest;
import com.xnx3.QRCodeUtil;
import com.xnx3.j2ee.entity.User;
import com.xnx3.j2ee.service.CaptchaService;
import com.xnx3.j2ee.service.QRCodeService;
import com.xnx3.j2ee.service.SmsLogService;
import com.xnx3.j2ee.service.UserService;
import com.xnx3.j2ee.vo.BaseVO;
import com.xnx3.media.ImageUtil;

/**
 * 登录、注册
 * @author 管雷鸣
 */
@Controller
@RequestMapping("/")
public class LoginController_ extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private SmsLogService smsLogService;
	@Resource
	private QRCodeService qRCodeService;
	@Resource
	private CaptchaService captchaService;
	
	/**
	 * 注册页面 
	 * @param request {@link HttpServletRequest}
	 * @return View
	 */
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request){
		userService.regInit(request);
		return "iw/login/reg";
	}
	
	/**
	 * 验证码图片显示，直接访问此地址可查看图片
	 */
	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest request,HttpServletResponse response) throws IOException{
		captchaService.showImage(request, response);
	}
	
	/**
	 * 注册相应请求地址
	 * @param user {@link User}
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * @return View
	 */
	@RequestMapping("/regSubmit")
	public String regSubmits(User user ,HttpServletRequest request,Model model){
		BaseVO baseVO = userService.reg(user, request);
		if(baseVO.getResult() == BaseVO.SUCCESS){
			return "redirect:/login.do";
		}else{
			return error(model, baseVO.getInfo());
		}
	}
	
	/**
	 * 登陆请求验证
	 * @param user user.getUsername() 包含用户名/邮箱/id
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * @return View
	 */
	@RequestMapping("login")
	public String login(User user,HttpServletRequest request,Model model){
		if(getUser() != null){
			return redirect("user/info.do");
		}
		return "iw/login/login";
	}

	/**
	 * 登陆请求验证
	 * @param request {@link HttpServletRequest} 
	 * 		<br/>登陆时form表单需提交两个参数：username(用户名/邮箱)、password(密码)
	 * @return 
	 */
	@RequestMapping("loginSubmit")
	public String loginSubmit(HttpServletRequest request,Model model){
		BaseVO baseVO =  userService.loginByUsernameAndPassword(request);
		if(baseVO.getResult() == BaseVO.SUCCESS){
			return success(model, "登陆成功","user/info.do");
		}else{
			return error(model, baseVO.getInfo());
		}
	}

	/**
	 * 手机号－验证码方式登陆
	 */
	@RequestMapping("phoneVerifyLogin")
	public String phoneVerifyLogin(){
		if(getUser() != null){
			return redirect("user/info.do");
		}
		
		return "iw/login/phoneVerifyLogin";
	}
	
	/**
	 * 登陆请求验证
	 * @param phone 登录的手机号
	 * @param code 发送到手机的验证码
	 * @param response {@link HttpServletRequest}
	 * @param model {@link Model}
	 * @return {@link BaseVO}
	 */
	@RequestMapping("phoneVerifyLoginSubmit")
	@ResponseBody
	public BaseVO loginByPhoneAndCode(HttpServletRequest request){
		BaseVO baseVO = userService.loginByPhoneAndCode(request);
		return baseVO;
	}
	
	/**
	 * 发送手机号登录的验证码
	 * @param request {@link HttpServletRequest}
	 * 			<br/>form表单需提交参数：phone(发送到的手机号)
	 * @return {@link BaseVO}
	 */
	@RequestMapping("sendPhoneLoginCode")
	@ResponseBody
	public BaseVO sendPhoneLoginCode(HttpServletRequest request){
		return smsLogService.sendPhoneLoginCode(request);
	}
	
	@RequestMapping("404")
	public String error404(){
		return "/publicPage/404";
	}
	
	@RequestMapping("500")
	public String error500(){
		return "/publicPage/500";
	}
	@RequestMapping("test")
	public void test(HttpServletRequest reuqest, HttpServletResponse response) throws IOException{
		qRCodeService.showQRCodeForPage("http://www.baidu.com", response);
	}
	
}
