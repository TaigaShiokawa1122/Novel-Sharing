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


@WebServlet("/AdminDeleteGenreServlet")
public class AdminDeleteGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<GenreBean> genreList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		int genreId = Integer.parseInt(request.getParameter("genreId"));
		String genreName = request.getParameter("genreName");
		int row = 0;
		boolean unregistered = false;
		
		try {
			//このジャンルを使用していないか
			unregistered = adminDao.unusedGenre(genreId);
			
			if(unregistered) {
				
				row = adminDao.deleteGenre(genreId);
				
					if(row == 1) {
						request.setAttribute("message", "ジャンル名「" + genreName + "」を削除しました");
					} else { //新規登録失敗
						request.setAttribute("error", "削除できませんでした");
					}
					
			} else {
				request.setAttribute("error", "ジャンル名「" + genreName + "」は使用済みのため削除できません");
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
