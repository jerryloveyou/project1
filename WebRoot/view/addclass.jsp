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
    
    <title>My JSP 'addclass.jsp' starting page</title>
    
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
    <form action="addclass.do?methodName=addClass" method="get">
  		<p>${addclass}</p>
  		
  		<table>
  			<tr>
  				<td><p>请输入要添加的班级名字</p></td>
  				<td><input type="text" name="cname"></td>
  			</tr>
  			<tr>
  				<td><input type="submit" value="确定添加"></td>
  				<td><input type="reset" value="重置"></td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
