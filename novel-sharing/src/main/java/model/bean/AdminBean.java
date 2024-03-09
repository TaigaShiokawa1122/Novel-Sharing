package model.bean;

public class AdminBean {
	
	private int adminId;
	private String nickName;
	private String email;
	private String hashedPassword;
	
	public AdminBean(int adminId,String nickName,String email,String hashedPassword) {
		this.adminId = adminId;
		this.nickName = nickName;
		this.email = email;
		this.hashedPassword = hashedPassword;
	}
	
	public AdminBean() {
		
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}

}
