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
	
	//全ての小説取得
	public List<NovelBean> getAllNovels() throws ClassNotFoundException, SQLException {
		List<NovelBean> novelList = new ArrayList<>();
		String sql = "SELECT * FROM novels";
		
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
	
}
