package com.wyy.myhealth.bean;

public class NearFoodBean extends Foods{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String distance;

	private String visitcount;
	
	public String getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
