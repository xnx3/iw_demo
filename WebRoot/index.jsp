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
	<div>
		<a href="login.do" target="_black">登陆</a>
		&nbsp; 初始账号：admin &nbsp;&nbsp; 密码admin
	</div>
	<div>GitHub &nbsp;
		<a href="https://github.com/xnx3/iw" target="_black"> iw 使用说明</a> &nbsp;
		<a href="https://github.com/xnx3/iw_demo" target="_black"> iw_demo</a>
	</div>
	<div>
		<img src="http://www.xnx3.com/d/file/software/iw/20170215/6daab4364cda11b1a44d103c00110e28.jpg" style="width:100%;" />
	</div>
</body>
</html>