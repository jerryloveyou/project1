<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>漂亮支持响应式多终端适配的网站登录页面模板 - JS代码网</title>
		<meta name="keywords" content="网站模板,手机网站模板,手机登录页面,登录页面HTML,免费网站模板下载" />
		<meta name="description" content="JS代码网提供高质量手机网站模板下载" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="<%=basePath%>/view/assets/css/reset.css">
        <link rel="stylesheet" href="<%=basePath%>/view/assets/css/supersized.css">
        <link rel="stylesheet" href="<%=basePath%>/view/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script type="text/javascript">
            function sub(){
				var b = true;
				var un = document.getElementById("un");
				//账号要求字母开头，6-12位字母数字下划线
				b = unblur(un);
				
				//密码要求6-12位任意字符
				var up = document.getElementById("up");
				b = upblur(up);
				//提交表单
				if(b){
					document.getElementById("fff").submit();
				}	
		    }
		    function unblur(o){
				if(!/^[a-zA-Z]{1}[_a-zA-Z0-9]{3,6}$/.test(o.value)){
					o.style.border="2px solid red";
					document.getElementById("userNameError").innerHTML ="<img src='icon_error.png'/>"+"";
					return false;
				}else{
					o.style.border="2px solid blue";
					document.getElementById("userNameError").innerHTML = "<img src='icon_correct.png'/>"+"";
					return true;
				}
			}
			function upblur(o){
				if(!/^[\w\W]{3,8}$/.test(o.value)){
					o.style.border="2px solid red";
					document.getElementById("userPassError").innerHTML ="<img src='icon_error.png'/>"+"";
					return false;
				}else{
					o.style.border="2px solid blue";
					document.getElementById("userPassError").innerHTML = "<img src='icon_correct.png'/>"+"";
					return true;
				}
			}
        
        </script>

    </head>

    <body>

        <div class="page-container">
            <img src="p123.jpg"  width="210px" height="110px"  style="position:relative; top:-20px; left:0px;right:200px;bottom:5px">
            <font color="blue"><h1>Agoni教务管理系统后台登录</h1></font>
            <form action="<%=basePath%>login.do" method="post" id="fff">
                <input type="hidden" name="methodName" value= "login"/>
                <table>
                <tr>
					<td><input type="text" name="userName"  placeholder="用户名" id="un" class="in" onblur="unblur(this);" /></td>
					<td><span id="userNameError" class="errorMsg"></span></td>
				</tr>
				<tr>
					<td><input type="password" name="userPass"  placeholder="密码" id="up" class="in" onblur="upblur(this);"  /></td>
					<td><span id="userPassError" class="errorMsg"></span></td>
				</tr>
               </table>
                
                <p style="test-align:center;"></p>
                <%
             String loginError = (String)application.getAttribute("loginError");
             if(loginError != null) out.println(loginError);
              %>
                <br/>
                <font color="red"><p style="test-align:center;">${loginError}</p></font>
                <button type="submit">登录</button>
                <br/><br/><br/>
                 <span>还没有账号？<a href = "view/reg.jsp">注册</a></span>
                <div class="error"><span>+</span></div>
                
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
		
        <!-- Javascript -->
        <script src="<%=basePath%>/view/assets/js/jquery-1.8.2.min.js"></script>
        <script src="<%=basePath%>/view/assets/js/supersized.3.2.7.min.js"></script>
        <script src="<%=basePath%>/view/assets/js/supersized-init.js"></script>
        <script src="<%=basePath%>/view/assets/js/scripts.js"></script>

    </body>

</html>
