package com.peng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.peng.entity.Menu;
import com.peng.entity.Page;
import com.peng.entity.Role;
import com.peng.entity.User;
import com.peng.model.UserModel;
import com.peng.model.impl.UserModelMySQLImpl;
import com.peng.util.DBUtil;
import com.peng.vo.MenuVo;

public class MainServlet extends HttpServlet{
	
	//控制层持有一个模型层对象
	private UserModel userModel = new UserModelMySQLImpl();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接受所有请求
//		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("methodName");
		Class c = MainServlet.class;
		try {
			Method m = c.getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, req,resp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public void login (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//1、取出表单数据
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		//2、查询数据库
		System.out.println("LoginServlet控制层");
		User user = userModel.loadUserByName(userName);
		//3、根据第二步的结果判断 返回不同的页面并给出不同的提示
		if(null != user){
			//用户名存在
			if(userPass.equals(user.getUserPass())){
				
				//密码正确 登录成功
				//保存数据  整个项目中都有效
				List<Menu> menulist =  userModel.loadMenusByUid(user.getUid());
				req.getSession().setAttribute("menulist", menulist);
				
				
				req.getSession().setAttribute("loginUser", user);
				req.getRequestDispatcher("view/welcome.jsp").forward(req, resp);
			}else{
				//用户名正确 但密码错误
				req.setAttribute("loginError", "密码错误！");
				req.getRequestDispatcher("view/login.jsp").forward(req, resp);
			}
		}else{
			//用户名不存在
			req.setAttribute("loginError", "用户名不存在！");
			req.getRequestDispatcher("view/login.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 展示用户
	 */
	
	public void showUsers (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
		List<User> userList = userModel.loadAllUsers();
		req.setAttribute("userList", userList);
		req.setAttribute("date", new Date());
		req.getRequestDispatcher("view/showUsers.jsp").forward(req, resp);

	}
	
	/**
	 * 查看用户详细信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uid = req.getParameter("uid");
		User user = userModel.loadUserByName(Integer.valueOf(uid));
		req.setAttribute("user", user);
		req.getRequestDispatcher("view/showDetails.jsp").forward(req, resp);
	}
	
	/**
	 * 用户注册
	 */
	public void reg (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("view/login.jsp");
	}
	
	/**
	 * 用户退出
	 */
	public void logout (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		resp.sendRedirect("view/login.jsp");
	
	}
	
	/**
	 * 删除用户
	 */
	public void deleteUser  (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String sql = "delete from tb_user where uid=?";
		DBUtil.executeDML(sql, new Object[]{Integer.valueOf(uid)});
		req.setAttribute("msg", "删除成功！");
		req.getRequestDispatcher("showUsers.do").forward(req, resp);
	}

	
	
	/**
	 * 展示菜单
	 */
	public void showMenus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		Page<MenuVo> page = userModel.loadAllMenus(pageNo, pageSize);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",page.getDataList());//easyui要求返回分页数据键必须为rows
		map.put("total",page.getTotal());//easyui要求返回分页总行数的键必须为total
		
		//JSONObject.fromObject(object);
		String json = JSONObject.fromObject(map).toString();
		
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
		//req.setAttribute("menuList", menuList);
		//req.getRequestDispatcher("view/showMenus.jsp").forward(req, resp);
	}
	
	/**
	 * 加载一二级菜单
	 */
	public void toAddMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Menu> menuList = userModel.load12Menus();
		
		//req.setAttribute("menuList", menuList);
		//req.getRequestDispatcher("view/addMenu.jsp").forward(req, resp);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(JSONArray.fromObject(menuList).toString());
		resp.getWriter().flush();
	}
	
	/**
	 * 添加菜单
	 */
	public void addMenu(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String name = req.getParameter("name");
		System.out.println(name);
		String url = req.getParameter("url");
		String isshow = req.getParameter("isshow");
		String parentid = req.getParameter("parentid");
		int i = userModel.addMenu(name, url, Integer.valueOf(isshow), Integer.valueOf(parentid));
		if(i == 1){
			req.setAttribute("msg", "添加成功...");
			//添加成功 跳转到菜单列表界面
			this.showMenus(req, resp);
		}else{
			req.setAttribute("msg", "添加失败...");
			//添加失败 跳转到添加界面
			this.toAddMenu(req, resp);
		}
	}
	
	/**
	 * 展示角色列表
	 */
	public void showRoles(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Role> roleList = userModel.loadAllRoles();
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher("view/showRoles.jsp").forward(req, resp);
	}
	
	/**
	 * 加载角色对应的菜单
	 */
	public void loadRoleMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = req.getParameter("rid");
		List<Object[]> roleMenuList = userModel.loadRoleMenuByRoleId(Integer.valueOf(rid));
		req.setAttribute("roleMenuList", roleMenuList);
		
		Role r = userModel.loadRoleById(Integer.valueOf(rid));
		req.setAttribute("role", r);
		req.getRequestDispatcher("view/roleMenu.jsp").forward(req, resp);
	}
	
	/**
	 * 编辑角色权限
	 */
	public void editRoleMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = req.getParameter("rid");
		String[] mids = req.getParameterValues("mids");
		userModel.editRoleMenu(Integer.valueOf(rid), mids);
		req.setAttribute("msg", "修改成功...");
		this.loadRoleMenu(req, resp);
	}

	/**
	 * 展示所有班级
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showClass(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String sql = "select * from class order by sj desc ";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		req.setAttribute("classmsg", list);	
		req.getRequestDispatcher("view/showclass.jsp").forward(req, resp);	
	}
	/**
	 * 展示所有学生信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showStudent(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String sql = "select * from student order by sj desc";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		req.setAttribute("studentmsg", list);	
		req.getRequestDispatcher("view/showstudent.jsp").forward(req, resp);
	}
	
	/**
	 * 添加班级
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addClass(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String sql = "insert into class(cname,sj) values (?,?)";
		String cname = req.getParameter("cname");
		Date date = new Date();
		Object[]m={cname,date};
		DBUtil.executeDML(sql, m);
		req.setAttribute("addclassmsg","添加成功");
		req.getRequestDispatcher("view/addclass.jsp").forward(req, resp);
	}
	/**
	 * 添加学生
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addStudent(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String cname = req.getParameter("cname");
		String sql = "select * from class where cname = ?";
		List<Object []> list = DBUtil.executeQuery(sql, new Object[]{cname});
		if(list.size()==0){
			req.setAttribute("addclassError", "没有这个班级，请重新添加班级！");
			req.getRequestDispatcher("view/addstudent.jsp").forward(req, resp);
		}else{
			req.setAttribute("addclassmsg", "添加学生信息成功！");
			Object [] m =list.get(0);
			sql = "insert into student(sname,sj,cid) values (?,?,?)";
			Date date = new Date();
			String sname=req.getParameter("sname");
			Object[] n={sname,date,m[0]};
			DBUtil.executeDML(sql, n);
			req.getRequestDispatcher("view/addstudent.jsp").forward(req, resp);
		}
	}

	
		public void showRoleUser(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
			int rid = Integer.valueOf(req.getParameter("rid"));
			List<Object[]>  userList = userModel.showUserbyRole_Rid(rid);	
			req.setAttribute("userList", userList);
			req.setAttribute("rid", rid);
			req.getRequestDispatcher("view/alterRoleUser.jsp").forward(req, resp);
		}
		
		
		/**
		 * 执行修改角色拥有的用户
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public void executeAlterUser(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
			String [] userSid = req.getParameterValues(" ");
			int roleRid =Integer.valueOf(req.getParameter("rid"));
			int [] usersSid = new int [userSid.length];
			//将返回的字符串数组转化为int数组
			for(int i = 0 ; i< userSid.length;i++){
				usersSid[i]=Integer.valueOf(userSid[i]);
			}
			userModel.executeAlterUser(roleRid, usersSid);
			req.setAttribute("msg", "角色拥有的用户修改成功！！");
			req.getRequestDispatcher("view/alterRoleUser.jsp").forward(req, resp);
		}

		public void testAJAX (HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			System.out.println("测试AJAX！");
			
			resp.setCharacterEncoding("utf-8");
			
			resp.getWriter().write("重庆");
			resp.getWriter().flush();
			
		}
	
	
	
	
}
