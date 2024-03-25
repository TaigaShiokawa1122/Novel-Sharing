package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.bean.AdminBean;
import model.bean.AuthorBean;
import model.bean.GenreBean;

public class AdminDAO {
	
		//管理者ログイン
		public AdminBean adminLogin(String email, String password) throws ClassNotFoundException, SQLException {
			AdminBean admin = null;
			String sql = "SELECT * FROM admins WHERE email = ? AND hashed_password = ?";
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet res = pstmt.executeQuery();
				if (res.next()) {
					admin = new AdminBean();
					admin.setAdminId(res.getInt("admin_id"));
					admin.setNickName(res.getString("nickname"));
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
		
		public int deleteGenre( int genreId ) throws ClassNotFoundException, SQLException {
			String sql = "DELETE FROM genres WHERE genre_id = ?";
			int processingNum = 0;
			
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, genreId);
				processingNum = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
	                               ", SQLステート: " + e.getSQLState() + 
	                               ", エラーコード: " + e.getErrorCode());
			}
			return processingNum;
		}
		
		
		//このカテゴリーを選択している小説が今あるかどうか
		public boolean unusedGenre( int genreId ) throws ClassNotFoundException, SQLException {
			
			String sql = "SELECT COUNT( genre_id = ? ) AS c FROM novels WHERE genre_id = ?";//postsテーブルの全データをsqlに格納
			int count = -1;
			boolean unused = false;
			
			try(Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setInt(1, genreId);
				pstmt.setInt(2, genreId);
			    ResultSet res = pstmt.executeQuery();
			    
			    while (res.next()) {
			    	count = res.getInt("c");
			    }
				
			    if( count == 0 ) {
			    	unused = true;
				}
			    
			} catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
	                               ", SQLステート: " + e.getSQLState() + 
	                               ", エラーコード: " + e.getErrorCode());
			}
			return unused;
		}
		
		//著者追加
				public int addAuthor(String authorName, String furigana ) throws ClassNotFoundException, SQLException {
					
						String sql = "INSERT INTO authors (author_name , furigana) VALUES (? , ?)";
						int processingNum = 0;
						try (Connection con = DBConnection.getConnection(); 
								PreparedStatement pstmt = con.prepareStatement(sql)) {
							pstmt.setString(1, authorName);
							pstmt.setString(2, furigana);
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
				

				//追加しようとしている著者が既に登録されていないか
				public boolean getAuthor(String authorName) throws ClassNotFoundException, SQLException {
					String sql = "SELECT COUNT(author_name = ? ) AS c FROM authors WHERE author_name = ?"; 
					
				    int count = -1;
				    boolean author = false;    
				    try(Connection con = DBConnection.getConnection();
				    		PreparedStatement pstmt = con.prepareStatement(sql)) {
				        pstmt.setString(1,authorName);
						pstmt.setString(2,authorName);
						ResultSet res = pstmt.executeQuery();
						
				        while (res.next()) { 
				        	count = res.getInt("c");
						}
				        
						if( count == 0 ) {
							author = true;
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
					return author;
				}
				
				public int deleteAuthor( int authorId ) throws ClassNotFoundException, SQLException {
					String sql = "DELETE FROM authors WHERE author_id = ?";
					int processingNum = 0;
					
					try (Connection con = DBConnection.getConnection(); 
							PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setInt(1, authorId);
						processingNum = pstmt.executeUpdate();
					} catch (SQLException e) {
						System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
			                               ", SQLステート: " + e.getSQLState() + 
			                               ", エラーコード: " + e.getErrorCode());
					}
					return processingNum;
				}
				
				//この著者を選択している小説が今あるかどうか
				public boolean unusedAuthor( int authorId ) throws ClassNotFoundException, SQLException {
					
					String sql = "SELECT COUNT( author_id = ? ) AS c FROM novels WHERE author_id = ?";//postsテーブルの全データをsqlに格納
					int count = -1;
					boolean unused = false;
					
					try(Connection con = DBConnection.getConnection();
							PreparedStatement pstmt = con.prepareStatement(sql)){
						pstmt.setInt(1, authorId);
						pstmt.setInt(2, authorId);
					    ResultSet res = pstmt.executeQuery();
					    
					    while (res.next()) {
					    	count = res.getInt("c");
					    }
						
					    if( count == 0 ) {
					    	unused = true;
						}
					    
					} catch (SQLException e) {
						System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
			                               ", SQLステート: " + e.getSQLState() + 
			                               ", エラーコード: " + e.getErrorCode());
					}
					return unused;
				}
		
				//管理者ID（まだ未作成のメソッド、というかこのメソッドいらんかも？）	
				public List<AuthorBean> getAllAuthors() throws ClassNotFoundException, SQLException {
					List<AuthorBean> authorList = new ArrayList<>();
					AuthorBean author = null;
					String sql = "SELECT * FROM authors";
					try (Connection con = DBConnection.getConnection(); 
							PreparedStatement pstmt = con.prepareStatement(sql)) {
							ResultSet res = pstmt.executeQuery();
							while(res.next()) {
								author = new AuthorBean();
								author.setAuthorId(res.getInt("author_id"));
								author.setAuthorName(res.getString("author_name"));
								author.setFurigana(res.getString("furigana"));
								authorList.add(author);
							}
					}
					return authorList;
				}
				
				//小説更新(テキストベース)
				public int updateNovel(String type , String text,  int novelId) throws ClassNotFoundException, SQLException {
					
					String sql = "UPDATE novels SET " + type + " = ? WHERE novel_id = ?";
					int processingNum = 0;
					

					
					System.out.println(sql + "です");
					
					try (Connection con = DBConnection.getConnection(); 
							PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setString(1, text);
						pstmt.setInt(2, novelId);
						processingNum = pstmt.executeUpdate();
					} catch (SQLException e) {
						System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
			                               ", SQLステート: " + e.getSQLState() + 
			                               ", エラーコード: " + e.getErrorCode());
					}
					return processingNum;
				}
				
				
				//小説更新(IDベース)
				public int updateNovel(String type , int id , int novelId) throws ClassNotFoundException, SQLException {
					
					String sql = "UPDATE novels SET " + type + " = ? WHERE novel_id = ?";
					int processingNum = 0;
					
					try (Connection con = DBConnection.getConnection(); 
							PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setInt(1, id);
						pstmt.setInt(2, novelId);
						processingNum = pstmt.executeUpdate();
					} catch (SQLException e) {
						System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
			                               ", SQLステート: " + e.getSQLState() + 
			                               ", エラーコード: " + e.getErrorCode());
					}
					return processingNum;
				}
				
				
				//小説削除
				public int deleteNovel( int novelId ) throws ClassNotFoundException, SQLException {
					String sql = "DELETE FROM novels WHERE novel_id = ?";
					int processingNum = 0;
					
					try (Connection con = DBConnection.getConnection(); 
							PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setInt(1, novelId);
						processingNum = pstmt.executeUpdate();
					} catch (SQLException e) {
						System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
			                               ", SQLステート: " + e.getSQLState() + 
			                               ", エラーコード: " + e.getErrorCode());
					}
					return processingNum;
				}
				
				
}
