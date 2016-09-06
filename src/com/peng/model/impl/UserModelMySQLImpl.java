package com.peng.model.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.dao.UserDao;
import com.peng.dao.impl.UserDaoImpl;
import com.peng.entity.Menu;
import com.peng.entity.Page;
import com.peng.entity.Role;
import com.peng.entity.User;
import com.peng.model.UserModel;
import com.peng.vo.MenuVo;

public class UserModelMySQLImpl implements UserModel {

	// 模型层持有一个DAO层对象
	private UserDao userDao = new UserDaoImpl();

	/**
	 * 通过用户名加载一个用户对象
	 */
	public User loadUserByName(String userName) {
		System.out.println("UserModelMySQLImpl模型层");
		User user = userDao.loadUserByName(userName);
		return user;
	}

	/**
	 * 通过主键加载一个用户对象（登录）
	 * 
	 * @param uid
	 *            用户主键
	 * @return
	 */
	public User loadUserByName(int uid) {
		return userDao.loadUserByName(uid);
	}

	/**
	 * 加载所有用户
	 * 
	 * @return
	 */
	public List<User> loadAllUsers() {
		return userDao.loadAllUsers();
	}

	/**
	 * 通过帐号主键Id加载此用户拥有的菜单集合
	 * 
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusByUid(int uid) {
		return userDao.loadMenusByUid(uid);
	}

	/**
	 * 删除用户
	 * 
	 * @param uid
	 */
	public void deleteUser(int uid) {
		userDao.deleteUser(uid);
	}

	/**
	 * 加载所有菜单
	 * 
	 * @return
	 */
	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize) {
		return userDao.loadAllMenus(pageNo,pageSize);
	}

	/**
	 * 加载一二级菜单
	 */
	public List<Menu> load12Menus() {
		return userDao.load12Menus();
	}

	/**
	 * 添加菜单
	 */
	public int addMenu(String name, String url, int isshow, int parentid) {
		Menu m = userDao.loadMenuById(parentid);
		if (null != m) {
			userDao.addMenu(name, url, isshow, m.getLevel() + 1, parentid);
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 加载所有角色
	 * 
	 * @return
	 */
	public List<Role> loadAllRoles() {
		return userDao.loadAllRoles();
	}

	/**
	 * 通过角色id查询角色菜单
	 * 
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid) {
		return userDao.loadRoleMenuByRoleId(rid);
	}

	/**
	 * 通过角色id加载角色对象
	 * 
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid) {
		return userDao.loadRoleById(rid);
	}

	/**
	 * 编辑角色权限
	 * 
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids) {
		userDao.editRoleMenu(rid, mids);
	}

	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * 
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri) {
		return userDao.checkUserMenu(uid, uri);
	}
	

	
	/**
	 * 查看所有班级
	 */
	public List<Object[]> loadAllClass(){
		return userDao.loadAllClass();
	}
	/**
	 * 查看所有学生
	 * @return
	 */
	public List<Object[]> loadAllStudent(){
		return userDao.loadAllStudent();
	}
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname){
		userDao.addStudent(sname, cname);
	}
	/**
	 * 添加班级
	 */
	public void addClass(String cname){
		userDao.addClass(cname);
	}

	/**
	 * 通过角色的rid 去找到他对应的所拥有的用户 就是那些用户是这个角色
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<Object[]> showUserbyRole_Rid(int rid){
		return userDao.showUserbyRole_Rid(rid);
		
	}
	
	/**
	 * 执行修改角色拥有的用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void executeAlterUser(int roleRid, int[] usersSid){
		userDao.executeAlterUser(roleRid, usersSid);
	}

}
