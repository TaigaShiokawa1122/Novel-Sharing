package model.dao;

import java.sql.SQLException;

import model.bean.AdminBean;

public class AdminDAO {
	
		//管理者追加（まだ未作成のメソッド）	
		public int addAdmin(String adminName,String email,String hashedPass) throws ClassNotFoundException, SQLException {
			return 1;
		}
		
		//ジャンル追加（まだ未作成のメソッド）	
		public int addGanre(String ganre ) throws ClassNotFoundException, SQLException {
			return 1;
		}
				
		//管理者ログイン（まだ未作成のメソッド）	
		public AdminBean adminLogin(String email,String hashedPass) throws ClassNotFoundException, SQLException {
			AdminBean admin = new AdminBean();
			admin = null;
			return admin;
		}
		
		//管理者ID（まだ未作成のメソッド、というかこのメソッドいらんかも？）	
		public int getAdminId(String email ) throws ClassNotFoundException, SQLException {
			return 1;
		}
}
