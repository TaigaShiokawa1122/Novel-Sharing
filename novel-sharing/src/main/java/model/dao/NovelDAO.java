package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.bean.AuthorBean;

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
	
}
