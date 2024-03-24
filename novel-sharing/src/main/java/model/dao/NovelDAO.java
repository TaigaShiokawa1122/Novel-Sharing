package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.bean.AuthorBean;
import model.bean.GenreBean;
import model.bean.NovelBean;

public class NovelDAO {
	
	//全ての作家取得
	public List<AuthorBean> getAllAuthors() throws ClassNotFoundException, SQLException {
		List<AuthorBean> authorList = new ArrayList<>();
		AuthorBean author = null;
		String sql = "SELECT * FROM authors ORDER BY furigana ASC";
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
	
	//全てのジャンル取得
		public List<GenreBean> getAllGenres() throws ClassNotFoundException, SQLException {
			List<GenreBean> genreList = new ArrayList<>();
			GenreBean genre = null;
			String sql = "SELECT * FROM genres ORDER BY genre_name ASC";
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
	
	//全ての小説取得
	public List<NovelBean> getAllNovels() throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
		}
		return novelList;
	}
	
	//作家別　小説一覧
	public List<NovelBean> getNovelListByAuthor(int authorId) throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE n.author_id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, authorId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
		}
		return novelList;
	}
	
	//ジャンル別　小説一覧
	public List<NovelBean> getNovelListByGenre(int genreId) throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE n.genre_id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, genreId);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author  = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
		}
		return novelList;
	}
	
	//小説タイトル検索（全ての小説）
	public List<NovelBean> searchAllNovelsByTitle(String searchString) throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON n.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE title LIKE ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, "%" + searchString + "%");
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
			
		}
		return novelList;
	}
	
	//作家別　小説タイトル検索（全ての小説）
	public List<NovelBean> searchAllNovesByAuthor(int authorId, String searchString) throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE n.author_id = ? AND title LIKE ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, authorId);
			pstmt.setString(2, "%" + searchString + "%");
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
		}
		return novelList;
	}
	
	//ジャンル別　小説タイトル検索（全ての小説）
	public List<NovelBean> searchAllNovesByGenre(int genreId, String searchString) throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE n.genre_id = ? AND title LIKE ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, genreId);
			pstmt.setString(2, "%" + searchString + "%");
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				NovelBean novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
				
				novelList.add(novel);
			}
		}
		return novelList;
	}
	
	//小説詳細
	public NovelBean getNovelDetail(int novelId) throws ClassNotFoundException, SQLException{
		NovelBean novel = null;
		String sql = "SELECT * FROM novels n "
				+ "INNER JOIN authors a ON a.author_id = n.author_id "
				+ "INNER JOIN genres g ON g.genre_id = n.genre_id "
				+ "WHERE novel_id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, novelId);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				novel = new NovelBean();
				AuthorBean author = new AuthorBean();
				GenreBean genre = new GenreBean();
				novel.setNovelId(res.getInt("novel_id"));
				novel.setTitle(res.getString("title"));
				novel.setSummary(res.getString("summary"));
				novel.setImage(res.getString("image"));
				author.setAuthorId(res.getInt("author_id"));
				author.setAuthorName(res.getString("author_name"));
				author.setFurigana(res.getString("furigana"));
				genre.setGenreId(res.getInt("genre_id"));
				genre.setGenre_name(res.getString("genre_name"));
				
				novel.setAuthor(author);
				novel.setGenre(genre);
			}
			return novel;
		}
	}
}
