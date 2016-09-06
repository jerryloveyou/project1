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
    
    <title>My JSP 'showstudent.jsp' starting page</title>
    
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
      <form action="showStudent.do?methodName=showStudent">
    	<table>
    		<tr>
    			<td>编号</td>
    			<td>学号</td>
    			<td>姓名</td>
    			<td>性别</td>
    			<td>创建时间</td>
    			<td>班级</td>
    		</tr>
    		<c:forEach items="${studentmsg}" var="ss">
    			<tr>
    				<td>${ss[0]}</td>
    				<td>${ss[1]}</td>
    				<td>${ss[2]}</td>
    				<td>${ss[3]}</td>
    				<td>${ss[4]}</td>
    				<td>${ss[5]}</td>
    			</tr>
    		</c:forEach>
    	</table>
    </form>
  </body>
</html>
