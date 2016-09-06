package com.peng.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.dao.UserDao;
import com.peng.entity.Menu;
import com.peng.entity.Page;
import com.peng.entity.Role;
import com.peng.entity.User;
import com.peng.util.DBUtil;
import com.peng.vo.MenuVo;

public class UserDaoImpl implements UserDao{

	/**
	 * 通过用户名加载一个用户对象（登录）
	 * @param userName 用户输入的帐号
	 * @return 返回null表示用户的帐号不存在 
	 */
	public User loadUserByName(String userName){
		System.out.println("UserDaoImpl数据层");
		String sql = "select * from tb_user where userName=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{userName});
		User user = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			user = new User((Integer)os[0], userName, String.valueOf(os[2]), (Integer)os[3], (String)os[4],(Integer)os[5],(String)os[6],(String)os[7]);
			// user=new User((Integer)os[0],String.valueOf(os[1]),String.valueOf(os[2]),(Integer)os[3],(String)os[4],(String)os[5],(Integer)os[6],(String)os[7]);
		}
		return user;
	}
	
	/**
	 * 加载所有用户
	 */
	@Override
	public List<User> loadAllUsers() {
		String sql = "select * from tb_user";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		List<User> userList = new ArrayList<User>();
		User user = null;
		if(null != list && list.size()>0){
			for(Object[]os:list){
			user = new User((Integer)os[0],String.valueOf(os[1]),String.valueOf(os[2]),(Integer)os[3],(String)os[4],(Integer)os[5],(String)os[6],(String)os[7]);
			userList.add(user);
		}
		}
		return userList;
	}
	
	
	/**
	 * 删除某一个用户
	 */
	public void deleteUser(String userName) {
		String sql = "delete from user where userName = ?";
		Object[]m={userName};
		DBUtil.executeDML(sql, m);
	}
	/**
	 * 
	 * 权限管理 
	 */
	public List<Menu> loadAllMenu(int uid) {
		String sql = "select m.* from userrole ur,role r ,rolemenu rm ,menu m where m.mid = rm.mid and rm.rid = r.rid and r.rid = ur .rid and ur.uid =?";
		List<Object[]>list = DBUtil.executeQuery(sql, new Object[]{uid});
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(null != list && list.size()>0){
			for(Object[]os:list){
			m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
			menuList.add(m);
		}
		}
		return menuList;
	}
	
	/**
	 * 通过帐号主键Id加载此用户拥有的菜单集合
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusByUid(int uid){
		String sql = "select m.* from userrole ur,rolemenu rm,menu m where ur.rid=rm.rid and rm.mid=m.mid and m.isshow=1 and ur.uid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{uid});
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
			}
		}
		return menuList;
	}
	
	/**
	 * 加载所有菜单
	 */
	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize){
		String sql = "select m.mid,m.name,m.url,m.isshow,m.level,(select m2.name from menu m2 where m2.mid=m.parentid) from menu m limit ?,?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{(pageNo-1)*pageSize,pageSize});
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		MenuVo m = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				m = new MenuVo((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (String)os[5]);
				menuList.add(m);
			}
		}
		sql = "select count(*) from menu";
		list = DBUtil.executeQuery(sql, null);
		long total = (Long)list.get(0)[0];
		return new Page<MenuVo>(pageNo,pageSize,menuList,total);
	}
	
	/**
	 * 加载一二级菜单
	 */
	public List<Menu> load12Menus(){
		String sql = "select m.* from menu m where m.level in(1,2)";
		List<Object[]> list = DBUtil.executeQuery(sql, null);
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
			}
		}
		return menuList;
	}
	
	/**
	 * 通过主键id加载一个菜单对象
	 */
	public Menu loadMenuById(int mid){
		String sql = "select * from menu where mid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{mid});
		Menu m = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
		}
		return m;
	}
	
	/**
	 * 添加菜单
	 */
	public void addMenu(String name,String url,int isshow,int level,int parentid){
		String sql = "insert into menu(name,url,isshow,level,parentid) values(?,?,?,?,?)";
		DBUtil.executeDML(sql, new Object[]{name,url,isshow,level,parentid});
	}
		
	/**
	 * 加载所有角色
	 * @return
	 */
	public List<Role> loadAllRoles(){
		String sql = "select * from role";
		List<Object[]> list = DBUtil.executeQuery(sql, null);
		List<Role> roleList = new ArrayList<Role>();
		Role r = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				r = new Role((Integer)os[0], String.valueOf(os[1]));
				roleList.add(r);
			}
		}
		return roleList;
	}
	
	/**
	 * 通过角色id查询角色菜单
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid){
		String sql = "select m.mid,m.name,m.parentid,(select 1 from rolemenu rm where rm.mid=m.mid and rm.rid=?) from menu m";
		return DBUtil.executeQuery(sql, new Object[]{rid});
	}
	
	/**
	 * 通过角色id加载角色对象
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid){
		String sql = "select * from role where rid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{rid});
		Role r = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			r = new Role((Integer)os[0], String.valueOf(os[1]));
		}
		return r;
	}
	
	
	/**
	 * 编辑角色权限
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids){
		/*
		 * 先删除原来已有的菜单关联
		 */
		String sql = "delete from rolemenu where rid=?";
		DBUtil.executeDML(sql, new Object[]{rid});
		
		/*
		 * 再重新添加
		 */
		sql = "insert into rolemenu(rid,mid) values(?,?)";
		if(null != mids && mids.length > 0){
			for(String mid : mids){
				DBUtil.executeDML(sql, new Object[]{rid, Integer.valueOf(mid)});
			}
		}
	}
	
	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri){
		String sql = "select 1 from userrole ur,rolemenu rm,menu m where ur.rid=rm.rid and rm.mid=m.mid and ur.uid=? and m.url like '%" + uri + "%'";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{uid});
		System.out.println(uri + "============>"+list.size());
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}

	/**
	 * 删除用户
	 * @param uid
	 */
	public void deleteUser(int uid){
		String sql = "delete from tb_user where uid=?";
		DBUtil.executeDML(sql, new Object[]{uid});
	}

	/**
	 * 通过主键加载一个用户对象（登录）
	 * @param uid 用户主键
	 * @return  
	 */
	public User loadUserByName(int uid){
		String sql = "select * from tb_user where uid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{Integer.valueOf(uid)});
		User user = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			user = new User((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (String)os[4], (Integer)os[5], (String)os[6], (String)os[7]);
		}
		return user;
	}
	/**
	 * 查询所有班级列表
	 * @return
	 */
	public List<Object[]> loadAllClass() {
		String sql = "select * from class order by sj desc ";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		return list;
	}
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Object[]> loadAllStudent() {
		String sql = "select * from student order by sj desc";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		return list;
	}
	
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname) {
		Date date = new Date();
		String sql = "select * from class where cid =?";
		List<Object []> list = DBUtil.executeQuery(sql, new Object[]{cname});
		Object [] o = list.get(0);
		sql = "insert into student(sname,sdate,cid) values (?,?,?)";
		Object[] os ={sname,date,o[0]};
		DBUtil.executeDML(sql, os);
	}
	
	/**
	 * 添加班级
	 */
	public void addClass(String cname){
		Date date = new Date();
		Object[]m={cname,date};
		String sql = "insert into class(cname,sj) values (?,?)";
		DBUtil.executeDML(sql, m);
		
	}
	/**
	 * 通过角色去查询用户，就是是当前这个角色的所用用户，通过rid来擦
	 */
	public List<Object[]> showUserbyRole_Rid(int rid){
		String sql ="select l.uid,l.userName,(select 1 from userrole lr  where lr.uid = l.uid and lr.rid = ?) from tb_user l";
		List<Object[]>userList = DBUtil.executeQuery(sql, new Object[]{rid});
		return userList;	
	}
	
	/**
	 * 执行修改角色所拥有的用户
	 */
	public void executeAlterUser(int roleRid,int [] usersSid){
		//先执行删除原来角色所对应的用户
		String sql = "delete from userrole where rid = ?";
		DBUtil.executeDML(sql, new Object []{roleRid});
		
		//先执行删除原来用户所对应的角色 那一行全删除
		  sql = "delete from userrole where uid = ?";
		for(int i = 0 ;i <usersSid.length;i++ ){
			DBUtil.executeDML(sql,new Object[]{usersSid[i]});
		}
		//然后执行将不同的用户赋予给他相同的角色
		sql ="insert into userrole(uid,rid) values(?,?) ";
		for(int i = 0 ;i <usersSid.length;i++ ){
			DBUtil.executeDML(sql,new Object[]{usersSid[i],roleRid});
		}
	}

	

}