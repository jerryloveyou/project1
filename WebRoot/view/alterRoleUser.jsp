<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'alterRoleMenu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<p>${msg}</p>
    <form action="executeAlterUser.do?methodName=executeAlterUser&rid=${rid}" method="post"> 
    	<c:forEach items="${userList}" var="ml">
    		<input type="checkbox" name = "userName" value="${ml[0]}" <c:if test="${ml[2]==1}">checked</c:if>>${ml[1]}<br/>
    	</c:forEach>
    	<input type = "submit" value="执行修改">
    </form>
  </body>
</html>
