package com.campusnetwork.models;

import java.io.Serializable;

public class PerformanceRange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269949892924815538L;
	
	private int range1;
	private int range2;
	private int range3;
	private int range4;
	private int range5;
	
	public int getRange1() {
		return range1;
	}
	public void setRange1(int range1) {
		this.range1 = range1;
	}
	public int getRange2() {
		return range2;
	}
	public void setRange2(int range2) {
		this.range2 = range2;
	}
	public int getRange3() {
		return range3;
	}
	public void setRange3(int range3) {
		this.range3 = range3;
	}
	public int getRange4() {
		return range4;
	}
	public void setRange4(int range4) {
		this.range4 = range4;
	}
	public int getRange5() {
		return range5;
	}
	public void setRange5(int range5) {
		this.range5 = range5;
	}
}
