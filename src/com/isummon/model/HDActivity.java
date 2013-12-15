package com.isummon.model;

public class HDActivity {
	public static final String tmFormat = "yyyy-MM-dd:hhmmss";

    private int hdId;
	private String hdName;
	private String hdAddress;
    private double longitude;
    private double latitude;
	private String hdStartTime;
	private String hdEndTime;
	private int hdOrigin;
	private String hdDesc;
	private HDType hdType;
	private int hdNumLimit;
	private int hdCurNum;

    private HDProperty hdProperty;
	private HDStatus hdStatus;

	@Override
	public String toString() {
		return "HDActivity [hdName=" + hdName + ", hdAddress=" + hdAddress
				+ ", hdStartTime=" + hdStartTime + ", hdEndTime=" + hdEndTime
				+ ", hdOrigin=" + hdOrigin + ", hdDesc=" + hdDesc + ", hdTags="
				+ hdType + ", hdNumLimit=" + hdNumLimit + ", hdCurNum="
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getHdId() {
        return hdId;
    }

    public HDType getHdType() {
        return hdType;
    }

    public void setHdType(HDType hdType) {
        this.hdType = hdType;
    }

    public HDStatus getHdStatus() {
        return hdStatus;
    }

    public void setHdStatus(HDStatus hdStatus) {
        this.hdStatus = hdStatus;
    }

    public HDProperty getHdProperty() {
        return hdProperty;
    }

    public void setHdProperty(HDProperty hdProperty) {
        this.hdProperty = hdProperty;
    }
}
