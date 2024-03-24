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
import model.bean.NovelBean;
import model.dao.AdminDAO;
import model.dao.NovelDAO;


@WebServlet("/AdminDeleteNovelServlet")
public class AdminDeleteNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<GenreBean> genreList = new ArrayList<>();
		List<NovelBean> novelList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		NovelDAO novelDao = new NovelDAO();
		
		int novelId = Integer.parseInt(request.getParameter("novelId"));
		String novelName = request.getParameter("novelName");
		int row = 0;
		
		try {
				row = adminDao.deleteNovel(novelId);
				
					if(row == 1) {
						request.setAttribute("message", "「" + novelName + "」を削除しました");
					} else { //新規登録失敗
						request.setAttribute("error", "削除できませんでした");
					}
					
					authorList = adminDao.getAllAuthors();
					genreList = adminDao.getAllGenres();
					novelList = novelDao.getAllNovels();
					
					//作家一覧
					authorResult(request, response, authorList);

				request.setAttribute("genreList",genreList);
				novelResult(request, response, novelList);	
			
			
				request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-list.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	//作家一覧の取得確認
			private void authorResult(HttpServletRequest request, HttpServletResponse response, List<AuthorBean> authorList) {
				if(authorList == null || authorList.isEmpty()) {
					request.setAttribute("authorUnregistered", "作家が未登録です。");
				} else {
					request.setAttribute("authorList", authorList);
				}
			}
			
			//小説一覧の取得確認
			private void novelResult(HttpServletRequest request, HttpServletResponse response, List<NovelBean> novelList) {
				if(novelList == null || novelList.isEmpty()) {
					request.setAttribute("novelUnregistered", "小説が未登録です。");
				} else {
					request.setAttribute("novelList", novelList);
				}
			}
}
