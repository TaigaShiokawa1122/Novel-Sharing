package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.bean.AdminBean;
import model.bean.GenreBean;

public class AdminDAO {
	
		//管理者ログイン
		public AdminBean adminLogin(String email, String password) throws ClassNotFoundException, SQLException {
			AdminBean admin = null;
			String sql = "SELECT * FROM admins WHERE email = ? AND password = ?";
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet res = pstmt.executeQuery();
				if (res.next()) {
					admin = new AdminBean();
					admin.setAdminId(res.getInt("admin_id"));
					admin.setNickName(res.getString("nick_name"));
					admin.setEmail(res.getString("email"));
				}
			}
			return admin;
		}

		//管理者IDの取得
		public int getAdminId(String email) throws ClassNotFoundException, SQLException {
			int adminId = -1;
			String sql = "SELECT * FROM admins WHERE email = ?";
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, email);

				ResultSet res = pstmt.executeQuery();
				if (res.next()) {
					adminId = res.getInt("admin_id");
				}
			}
			return adminId;
		}

	
		//管理者追加
		public int addAdmin(String adminName,String email,String hashedPass) throws ClassNotFoundException, SQLException {
			
			String sql = "INSERT INTO admins (nickname,email,hashed_password) VALUES (? , ? , ?)";
			int processingNum = 0;
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, adminName);
				pstmt.setString(2, email);
				pstmt.setString(3, hashedPass);
				processingNum = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
	                               ", SQLステート: " + e.getSQLState() + 
	                               ", エラーコード: " + e.getErrorCode());
			} catch (Exception e) {
				System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
	                               ", メッセージ: " + e.getMessage() + 
	                               ", スタックトレース: " + e.getStackTrace());
			}
			
			return processingNum;
		}
		
		//ジャンル追加（まだ未作成のメソッド）	
		public int addGenre(String genreName ) throws ClassNotFoundException, SQLException {
			
				String sql = "INSERT INTO genres (genre_name) VALUES (?)";
				int processingNum = 0;
				try (Connection con = DBConnection.getConnection(); 
						PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, genreName);
					processingNum = pstmt.executeUpdate();
				} catch (SQLException e) {
					System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
		                               ", SQLステート: " + e.getSQLState() + 
		                               ", エラーコード: " + e.getErrorCode());
				} catch (Exception e) {
					System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
		                               ", メッセージ: " + e.getMessage() + 
		                               ", スタックトレース: " + e.getStackTrace());
				}
				
				return processingNum;
		
		}
		

		//管理者ID（まだ未作成のメソッド、というかこのメソッドいらんかも？）	
		public List<GenreBean> getAllGenres() throws ClassNotFoundException, SQLException {
			List<GenreBean> genreList = new ArrayList<>();
			GenreBean genre = null;
			String sql = "SELECT * FROM genres";
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
					ResultSet res = pstmt.executeQuery();
					while(res.next()) {
						genre = new GenreBean();
						genre.setGenreId(res.getInt("genre_id"));
						genre.setGenre_name(res.getString("genre_name"));
						genreList.add(genre);
					}
			}
			return genreList;
		}
		
		//追加しようとしているジャンル名が既に登録されていないか
		//カテゴリーが今あるかどうか
		public boolean getGenre(String genreName) throws ClassNotFoundException, SQLException {
			String sql = "SELECT COUNT(genre_name = ? ) AS c FROM genres WHERE genre_name = ?"; 
			
		    int count = -1;
		    boolean genre = false;    
		    try(Connection con = DBConnection.getConnection();
		    		PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1,genreName);
				pstmt.setString(2,genreName);
				ResultSet res = pstmt.executeQuery();
				
		        while (res.next()) { 
		        	count = res.getInt("c");
				}
		        
				if( count == 0 ) {
					genre = true;
				}
				
		    } catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
	                               ", SQLステート: " + e.getSQLState() + 
	                               ", エラーコード: " + e.getErrorCode());
			} catch (Exception e) {
				System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
	                               ", メッセージ: " + e.getMessage() + 
	                               ", スタックトレース: " + e.getStackTrace());
			}
			return genre;
		}
}
