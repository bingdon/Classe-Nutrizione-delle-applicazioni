package com.wyy.myhealth.bean;

import java.io.Serializable;

public class IceBoxFoodBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -811812954692307477L;

	
	private String createtime;
	
	private String energy;
	
	private String foodpic;
	
	private String id;
	
	private String isexpire;
	
	private String name;
	
	private String numday;
	
	private String source;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type;
	
	private String userid;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public String getFoodpic() {
		return foodpic;
	}

	public void setFoodpic(String foodpic) {
		this.foodpic = foodpic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsexpire() {
		return isexpire;
	}

	public void setIsexpire(String isexpire) {
		this.isexpire = isexpire;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumday() {
		return numday;
	}

	public void setNumday(String numday) {
		this.numday = numday;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
