package com.isummon.model;

public class HDActivity {
	public static final String tmFormat = "yyyy-MM-dd:hhmmss";
	
	private String hdName;
	private String hdAddress;
	private String hdStartTime;
	private String hdEndTime;
	private int hdOrigin;
	private String hdDesc;
	private String hdTags;
	private int hdNumLimit;
	private int hdCurNum;
	
	
	@Override
	public String toString() {
		return "HDActivity [hdName=" + hdName + ", hdAddress=" + hdAddress
				+ ", hdStartTime=" + hdStartTime + ", hdEndTime=" + hdEndTime
				+ ", hdOrigin=" + hdOrigin + ", hdDesc=" + hdDesc + ", hdTags="
				+ hdTags + ", hdNumLimit=" + hdNumLimit + ", hdCurNum="
				+ hdCurNum + "]";
	}
	public String getHdName() {
		return hdName;
	}
	public void setHdName(String hdName) {
		this.hdName = hdName;
	}
	public String getHdAddress() {
		return hdAddress;
	}
	public void setHdAddress(String hdAddress) {
		this.hdAddress = hdAddress;
	}
	public String getHdStartTime() {
		return hdStartTime;
	}
	public void setHdStartTime(String hdStartTime) {
		this.hdStartTime = hdStartTime;
	}
	public String getHdEndTime() {
		return hdEndTime;
	}
	public void setHdEndTime(String hdEndTime) {
		this.hdEndTime = hdEndTime;
	}
	public int getHdOrigin() {
		return hdOrigin;
	}
	public void setHdOrigin(int hdOrigin) {
		this.hdOrigin = hdOrigin;
	}
	public String getHdDesc() {
		return hdDesc;
	}
	public void setHdDesc(String hdDesc) {
		this.hdDesc = hdDesc;
	}
	public String getHdTags() {
		return hdTags;
	}
	public void setHdTags(String hdTags) {
		this.hdTags = hdTags;
	}
	public int getHdNumLimit() {
		return hdNumLimit;
	}
	public void setHdNumLimit(int hdNumLimit) {
		this.hdNumLimit = hdNumLimit;
	}
	public int getHdCurNum() {
		return hdCurNum;
	}
	public void setHdCurNum(int hdCurNum) {
		this.hdCurNum = hdCurNum;
	}
	public static String getTmformat() {
		return tmFormat;
	}
	
}
