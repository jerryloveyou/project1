package com.peng.model;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.entity.Menu;
import com.peng.entity.Page;
import com.peng.entity.Role;
import com.peng.entity.User;
import com.peng.vo.MenuVo;

public interface UserModel {
	
	/**
	 * 通过用户名加载一个用户对象（登录）
	 * @param userName 用户输入的帐号
	 * @return 返回null表示用户的帐号不存在 
	 */
	public User loadUserByName(String userName);
	
	/**
	 * 通过主键加载一个用户对象（登录）
	 * @param uid 用户主键
	 * @return  
	 */
	public User loadUserByName(int uid);
	
	public List<User> loadAllUsers();

	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize);
	
	public List<Menu> load12Menus();
	
	public int addMenu(String name,String url,int isshow,int parentid);
	
	/**
	 * 加载所有角色
	 * @return
	 */
	public List<Role> loadAllRoles();
	
	/**
	 * 通过角色id查询角色菜单
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid);
	
	/**
	 * 通过角色id加载角色对象
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid);
	
	/**
	 * 编辑角色权限
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids);
	
	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri);
	/**
	 * 通过帐号主键Id加载此用户拥有的菜单集合
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusByUid(int uid);
	
	
	
	
	/**
	 * 查看所有班级
	 * @return
	 */
	public List<Object[]> loadAllClass();
	/**
	 * 查看所有学生
	 * @return
	 */
	public List<Object[]> loadAllStudent();
	
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname);
	/**
	 * 添加班级
	 */
	public void addClass(String cname);
	/**
	 * 修改角色用户
	 */
	public void executeAlterUser(int roleRid, int[] usersSid);

	public List<Object[]> showUserbyRole_Rid(int rid);
	
}
