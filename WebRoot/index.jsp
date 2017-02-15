<%@page import="com.xnx3.j2ee.Global"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><%=Global.get("SITE_NAME") %></title>
</head>
<body>
	<h1 style="text-align: center;">iw快速开发框架</h1>
	<p>src/com/xnx3/j2ee/ ：iw本身的控制器(controller)、实体类(entity)、以及系统级操作(system)</p>
	<p>WebRoot/WEB-INF/lib ：iw开发所依赖的类库，已集成好</p>
	<p>WebRoot/WEB-INF/view ：Spring MVC 的 view 层显示，其中，其内的 iw文件夹下为iw本身自带功能的前端展示；publicPage 文件夹下为公共的模版，比如公共头部、尾部、提示页面</p>
	<p></p>
	<p></p>
	
</body>
</html>