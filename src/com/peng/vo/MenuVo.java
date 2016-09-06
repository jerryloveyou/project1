package com.peng.vo;

public class MenuVo {
	
    private int mid;
	
	private String name;
	
	private String url;
	
	// 1表示要展示 0表示不展示
	private int isshow = 1;
	
	private int level;
	
	private String parentName;

	public MenuVo(int mid, String name, String url, int isshow, int level,
			String parentName) {
		this.mid = mid;
		this.name = name;
		this.url = url;
		this.isshow = isshow;
		this.level = level;
		this.parentName = parentName;
	}
	
	public String toString(){
		return "{\"mid\":"+mid+",\"name\":"+name+",\"url\":"+url+",\"isshow\":"+isshow+",\"level\":"+level+",\"parentName\":"+parentName+"}"; 
	}

	public MenuVo() {}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
