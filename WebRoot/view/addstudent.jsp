<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addstudent.jsp' starting page</title>
    
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
    <form action="addStudent.do?methodName=addStudent" method="post">
  	  <table>
  	  	<p>${addstudent}</p>
  		<tr>
  			<p>学号:<input type="text" name="sno"></p>
  		</tr>
  		<tr>
  			<p>姓名:<input type="text" name="sname"></p>
  		</tr>
  		<tr>
  			<td>性别：<input type="text" name="sex">
  			</td>
  		</tr>
  		<tr>
  			<td>班级：<input type="text" name="cid"></td>
  		</tr>
  		<tr>
  			<td><input type="submit" value="确定添加"><input type="reset" value="重置"></td>
  		</tr>
  	  </table>
  	</form>
  </body>
</html>
