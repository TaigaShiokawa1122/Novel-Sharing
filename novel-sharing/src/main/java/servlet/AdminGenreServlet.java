package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.GenreBean;
import model.dao.AdminDAO;


/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminGenreServlet")
public class AdminGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GenreBean> genreList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		try {
			genreList = adminDao.getAllGenres();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("genreList",genreList);
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-genre.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<GenreBean> genreList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		
		
		String genre = request.getParameter("genre");
		int row = 0;
		boolean unregistered = false;
		
		try {
			//同じジャンル名が既に存在しているか
			unregistered = adminDao.getGenre(genre);
			
			if(unregistered) {
				row = adminDao.addGenre(genre);
				
					if(row == 1) {
						request.setAttribute("message", "ジャンル名「" + genre + "」を新規登録しました");
					
					} else { //新規登録失敗
						request.setAttribute("error", "登録できませんでした");
					}
					
			} else {
				request.setAttribute("error", "ジャンル名「" + genre + "」は既に存在しています");
			}
			

			try {
				genreList = adminDao.getAllGenres();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("genreList",genreList);
			request.getRequestDispatcher("/WEB-INF/admin-assets/admin-genre.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
