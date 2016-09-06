package com.peng.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {
	

private static String DRIVERCLASS;
	
	private static String URL;
	
	private static String USER;
	
	private static String PASSWORD;
	/**
	 * 静态初始化块
	 */
	static{
		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = DBUtil.class.getResourceAsStream("/com/peng/util/pen.properties");
			pro.load(in);
			DRIVERCLASS = pro.getProperty("driverclass");
			URL = pro.getProperty("url");
			USER = pro.getProperty("user");
			PASSWORD = pro.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接对象
	 * @return
	 */
	public static Connection getConnection(){
		
		Connection con= null;
		try {
			Class.forName(DRIVERCLASS);
			con= DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	/**
	 * 通用的sql查询方法
	 * @param sql
	 * @return
	 */
	public static List<Object[]> executeQuery(String sql,Object[] o){
		Connection con= null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<Object[]> list=new ArrayList<Object[]>();
		try {
			
			con=DBUtil.getConnection();
			ps=con.prepareStatement(sql);
			//SQL语句中带有问号，需要设置参数
			if(null!=o && o.length>0){
				for(int i=0;i<o.length;i++){
					ps.setObject((i+1), o[i]);
				}
			}
			rs = ps.executeQuery();
			int columnCount=rs.getMetaData().getColumnCount();
			Object[] os=null;
			while(rs.next()){
				os = new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					os[i] =rs.getObject(i+1);
				}
				list.add(os);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null)ps.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	
	
	/**
	 * 通用的DML执行方法，支持预处理
	 * @param sql
	 * @return 执行成功返回true
	 */
	public static boolean executeDML(String sql,Object[] o){
		Connection con = null;
		PreparedStatement ps = null;
		boolean b = false;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//传入数组参数时
			if(null != o && o.length>0){
				for(int i=0;i<o.length;i++){
					ps.setObject((i+1), o[i]);
				}
			}
			b = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null)ps.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	
	
	
	

}
