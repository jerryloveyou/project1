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
    
    <title>My JSP 'showclass.jsp' starting page</title>
    
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
    <form action="showClass.do?methodName=showClass">
  	  <table>
  		<tr>
  			<td>编号</td>
  			<td>班级名称</td>
  			<td>创建时间</td>
  		</tr>
  		<c:forEach items="${classmsg}" var="sc">
  		<tr>
  			<td>${sc[0]}</td>
  			<td>${sc[1]}</td>
  			<td>${sc[2]}</td>
  		</tr>
  		</c:forEach>
  	  </table>
  	</form>
  </body>
</html>
