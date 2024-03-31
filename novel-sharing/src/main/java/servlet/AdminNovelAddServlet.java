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
import model.bean.GenreBean;
import model.dao.AdminDAO;
import model.dao.NovelDAO;

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminNovelAddServlet")
public class AdminNovelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<GenreBean> genreList = new ArrayList<>();
		NovelDAO nDao = new NovelDAO();
		
		try {
			
			authorList = nDao.getAllAuthors();
			genreList = nDao.getAllGenres();
			
			//作家一覧
			authorResult(request, response, authorList);
			request.setAttribute("genreList",genreList);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-add.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		int novelId = Integer.parseInt(request.getParameter("genreId"));
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		int processingNum = 0;
		AdminDAO adminDao = new AdminDAO();
		List<AuthorBean> authorList = new ArrayList<>();
		List<GenreBean> genreList = new ArrayList<>();
		NovelDAO nDao = new NovelDAO();
		
		try {
			processingNum = adminDao.addNovel(title,summary,authorId,novelId);
			authorList = nDao.getAllAuthors();
			genreList = nDao.getAllGenres();
			
			//作家一覧
			authorResult(request, response, authorList);
			request.setAttribute("genreList",genreList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(processingNum == 0) {
			request.setAttribute("message","小説を追加できませんでした");
		} else if(processingNum == 1) {
			request.setAttribute("message","小説「 " + title + " 」を追加しました");
		}
		
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-add.jsp").forward(request, response);
		
	}

	
	//作家一覧の取得確認
		private void authorResult(HttpServletRequest request, HttpServletResponse response, List<AuthorBean> authorList) {
			if(authorList == null || authorList.isEmpty()) {
				request.setAttribute("authorUnregistered", "作家が未登録です。");
			} else {
				request.setAttribute("authorList", authorList);
			}
		}
		
}
