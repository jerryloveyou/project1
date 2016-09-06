<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'roleMenu.jsp' starting page</title>
    
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
    <p style="color:#c00;">${msg}</p>
  	  <p>角色名称：<b style="color:#c00;">${role.rname}</b>，编辑角色菜单：</p>
      <form action="<%=basePath%>editRoleMenu.do" method="post">
      	  <input type="hidden" name="methodName" value="editRoleMenu">
      	  <input type="hidden" name="rid" value="${role.rid}">
      	  <c:forEach items="${roleMenuList}" var="rm">
      	  	  <input type="checkbox" name="mids" value="${rm[0]}" <c:if test="${rm[3]==1}">checked</c:if> />${rm[1]}<br/>
      	  </c:forEach>
      	  <input type="submit" value="确认修改">
      </form>
  </body>
</html>
