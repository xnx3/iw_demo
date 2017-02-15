<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.xnx3.com/java_xnx3/xnx3_tld" prefix="x" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<jsp:include page="../../publicPage/common/head.jsp">
    	<jsp:param name="title" value="我的关注列表"/>
    </jsp:include>
</head>
<body>
<article id="container">
	<jsp:include page="../../publicPage/common/top.jsp"></jsp:include>
	<section id="main">
		
		<div><h1>我的关注列表</h1><a href="<%=basePath %>collect/add.do"  style="color:blue;">添加关注</a></div>
		
		<section id="unseen">
	        <table class="table table-bordered table-striped table-condensed">
	            <thead>
		            <tr>
		                <th>用户ID</th>
		                <th>昵称</th>
		                <th class="numeric">关注时间</th>
		                <th class="numeric">操作</th>
		            </tr>
	            </thead>
	            
	            <tbody>
		            <c:forEach items="${list}" var="collect">
		            	<tr>
			                 <td>${collect['othersid'] }</td>
			                 <td>${collect['nickname'] }</td>
			                 <td class="numeric"><x:time linuxTime="${collect['addtime'] }"></x:time></td>
			                 <td class="numeric">
			                 	<a href="<%=basePath %>collect/cancelCollect.do?othersid=${collect['othersid']}" style="color:blue;">取消关注</a>
			                 </td>
			             </tr>
		            </c:forEach>
	            </tbody>
	        </table>
	    </section>
	    <!-- 通用分页跳转 -->
	    <jsp:include page="../../publicPage/common/page.jsp">
	    	<jsp:param name="page" value="${page }"/>
	    </jsp:include>
		
	</section>
	<jsp:include page="../../publicPage/common/menu.jsp"></jsp:include>
	<footer id="footer"></footer>
</article>

</body>
</html>