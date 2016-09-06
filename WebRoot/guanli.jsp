<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'easyui.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<style type="text/css">
	   .north{background-color:blue;}
	   .north img{height:66px;}
	   .logo {width:100px;float:left;}
	   #logo1{float:right;text-aglin:right;}
	</style>
	<script type="text/javascript">
		function addTab(title,url){
			$('#tabs').tabs('add',{
				title:title,
				selected:true,
				closable:true,
				content:"<iframe style='width:100% ;height:100%' frameborder='0' scrolling='auto' src='"+url+"'></iframe>"
			});
		};
	</script>
  </head>


	<body class="easyui-layout">
		
		<div data-options="region:'north',title:'North Title',split:true"style="height: 100px;" class="north">
		    <img src="p123.jpg">
		    <p id="logo" class="logo1">你好啊</p>
		</div>
		<div data-options="region:'west',title:'系统菜单',split:true"
			style="width: 200px;">
			<div id="aa" class="easyui-accordion" style="width:200px;"data-options="fit:true">
				<div title="Title1" data-options="iconCls:'icon-save'"
					style="overflow: auto; padding: 10px;">
					<h3 style="color: #0099FF;">
						Accordion for jQuery
					</h3>
					<p>
						Accordion is a part of easyui framework for jQuery. It lets you
						define your accordion component on web page more easily.
					</p>
				</div>
				<div title="Title2"
					data-options="iconCls:'icon-reload',selected:true"
					style="padding: 10px;">
					<input id="dd" type="text" class="easyui-datebox" required="required"></input>
					<ul id="tt" class="easyui-tree">
						<li>
							<span>Folder</span>
							<ul>
								<li>
									<span>下拉列表</span>
									<ul>
										<li>
											<span><a href="javascript:addTab('百度地图','http://map.baidu.com/');">百度地图</a></span>
										</li>
										<li>
											<span><a href="javascript:addTab('腾讯地图','http://map.qq.com/');">腾讯地图</a></span>
										</li>
										<li>
											<span><a href="javascript:addTab('猫途鹰','http://www.tripadvisor.cn/');">猫途鹰</a></span>
										</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'Agoni教务管理系统'" id="tabs" class="easyui-tabs" style="width:1200px;heigth:950px;"></div>
	</body>


</html>
