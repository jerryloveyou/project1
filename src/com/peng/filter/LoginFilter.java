package com.peng.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.entity.User;
import com.peng.model.UserModel;
import com.peng.model.impl.UserModelMySQLImpl;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		req.setCharacterEncoding("utf-8");
		String methodName = req.getParameter("methodName");
		if("login".equals(methodName) ||"reg".equals(methodName)||"logout".equals(methodName)){
			chain.doFilter(req, resp);
		}else{
			User user = (User)req.getSession().getAttribute("loginUser");
			if(null != user){
				String uri = req.getServletPath();
				uri = uri.substring(1);
				UserModel um = new UserModelMySQLImpl();
				boolean b = um.checkUserMenu(user.getUid(), uri);
				if(b){
					chain.doFilter(req, resp);
				}else{
					req.setAttribute("noRightError", "对不起,你没有访问此资源的权限!");
					req.getRequestDispatcher("view/noRightError.jsp").forward(req, resp);
				}
			}else{
				req.setAttribute("loginError", "对不起，请先登录！");
				resp.getWriter().write("<script type='text/javascript'>window.top.location.href='view/login.jsp';</script>");
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
