package com.isummon.model;

public class UserModel {
	private int userId;
	private String userName;
	private String nickName;
	private String passwd;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName
				+ ", nickName=" + nickName + ", passwd=" + passwd + "]";
	}
	
}
