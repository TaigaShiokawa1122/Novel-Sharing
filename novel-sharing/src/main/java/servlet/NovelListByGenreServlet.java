package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AuthorBean;
import model.bean.NovelBean;
import model.dao.NovelDAO;

@WebServlet("/NovelListByGenreServlet")
public class NovelListByGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		String search = request.getParameter("search");
		
		List<NovelBean> novelList = new ArrayList<>();
		List<AuthorBean> authorList = new ArrayList<>();
		
		NovelDAO nDao = new NovelDAO();
		
		try {
			
			authorList = nDao.getAllAuthors();
			novelList = nDao.getNovelListByGenre(genreId);
			
			genreResult(request, response, authorList);
			novelListByGenreResult(request, response, novelList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
	//作家一覧の取得確認
	private void genreResult(HttpServletRequest request, HttpServletResponse response, List<AuthorBean> authorList) {
		if(authorList == null || authorList.isEmpty()) {
			request.setAttribute("authorUnregistered", "作家が未登録です。");
		} else {
			request.setAttribute("authorList", authorList);
		}
	}

	//ジャンル別小説一覧　取得結果
	private void novelListByGenreResult(HttpServletRequest request, HttpServletResponse response, List<NovelBean> novelList) 
			throws ServletException, IOException {
		if (novelList == null || novelList.isEmpty()) {
			request.setAttribute("novelUnregistered", "小説が未登録です。");
		} else {
			request.setAttribute("novelList", novelList);
		}
		request.getRequestDispatcher("novel-list-by-genre.jsp").forward(request, response);
	}
}
