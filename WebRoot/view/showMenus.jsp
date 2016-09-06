<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMenus.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
  	    #ff table{
  		    width:100%;margin:auto;
  	    }
  	    #ff .labelTD{
  		    text-align: right;
  		    font-size: 14px;
  	    }
  	    #ff .in{width:200px;}
  	</style>
	<script type="text/javascript">
	    $(function(){
	        $('#win').window('close');  // close a window 
	        $('#dg').datagrid({    
                url:'showMenus.do?methodName=showMenus&pageNo=1&pageSize=10',
                frozenColumns:[[{field:'hhhh',checkbox:true}]],
                rownumbers:true,
                striped:true,
                pagination:true,    
                columns:[[    
                    {field:'mid',title:'编号',width:50,align:'center'},    
                    {field:'name',title:'菜单名称',width:100,align:'center'},    
                    {field:'url',title:'URL',width:300,align:'center'},
                    {field:'isshow',title:'是否展示',width:100,align:'center'}, 
                    {field:'level',title:'菜单级别',width:100,align:'center'}, 
                    {field:'parentName',title:'父级菜单',width:100,align:'center'}     
                ]],
                toolbar: [{
		  		    text:'添加',
	  			    iconCls: 'icon-add2',
	  			    handler: function(){
	  				$('#win').window('open');  // open a window 
	  			    }
	  		    },'-',{
		  		text:'修改',
	  			    iconCls: 'icon-modify',
	  			    handler: function(){
	  				alert('修改按钮');
	  			    }
	  		    },'-',{
		  		text:'删除',
	  			    iconCls: 'icon-delete',
	  			    handler: function(){
	  				alert('删除按钮');
	  			    }
	  		    },'-',{
		  		text:'刷新',
	  			    iconCls: 'icon-refresh',
	  			    handler: function(){
	  				refreshData(1, 10);
	  			    }
	  		    }]    
            });  
            var pager = $('#dg').datagrid("getPager");
  		        pager.pagination({
  			    onSelectPage:function(pageNumber, pageSize){
  				refreshData(pageNumber, pageSize);
  			    }
  		    });

  		//添加菜单时加载父级菜单列表
  		    $("#parentid").combobox({
			    url:'toAddMenu.do?methodName=toAddMenu',
			    textField:'name',
			    valueField:'mid',
			    value:'-1'
  	  	    });
  	    });
  	//刷新表格数据
  	     function refreshData(pageNo, pageSize){
  		    $('#dg').datagrid('loading');
		    $.post("showMenus.do",{
			    methodName:'showMenus',
			    pageNo:pageNo,
			    pageSize:pageSize
		    },function(data){
			    $("#dg").datagrid("loadData",{
				    rows:data.rows,
				    total:data.total
			});
			    $('#dg').datagrid('loaded');
  		},"json");
		
  	}
	</script>

  </head>
  
  <body>
      <table id="dg"></table>
      <div id="win" class="easyui-window" title="新增菜单" style="width:400px;height:300px;top:50px;" data-options="iconCls:'icon-add2',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">  
          <form id="ff">
          	  <table>
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label for="name">菜单名称：</label></td>
	      	  	  	  <td><input type="text" name="name" id="name" class="easyui-validatebox in" required /></td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label for="url">菜单URL：</label></td>
	      	  	  	  <td><input type="text" name="url" id="url" class="easyui-validatebox in" required/></td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label>是否展示：</label></td>
	      	  	  	  <td>
	      	  	  	  	  <input type="radio" name="isshow" value="1"/>展示
	      	  	  	  	  <input type="radio" name="isshow" value="0"/>不展示
	      	  	  	  </td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label for="parentid">父级菜单：</label></td>
	      	  	  	  <td>
	      	  	  	  	  <select name="parentid" id="parentid" class="easyui-combobox in" style="width:205px;"></select>
	      	  	  	  </td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td colspan="2" style="text-align:center;">
	      	  	  	  	  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add2'">添加菜单</a>
	      	  	  	  </td>
	      	  	  </tr>
	      	  </table>
          </form> 
	  </div>
  </body>
</html>
