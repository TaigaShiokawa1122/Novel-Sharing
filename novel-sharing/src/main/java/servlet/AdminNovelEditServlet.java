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

/**
 * Servlet implementation class NovelDetailServlet
 */
@WebServlet("/AdminNovelEditServlet")
public class AdminNovelEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<GenreBean> genreList = new ArrayList<>();
		
		NovelBean novel = new NovelBean();
		NovelDAO novelDao = new NovelDAO();
		AdminDAO adminDao = new AdminDAO();
		int novelId = Integer.parseInt(request.getParameter("novelId"));
		
		try {
			novel = novelDao.getNovelDetail(novelId);
			authorList = adminDao.getAllAuthors();
			genreList = adminDao.getAllGenres();
			//作家一覧
			authorResult(request, response, authorList);
			//ジャンル一覧
			genreResult(request, response, genreList);
			//小説詳細
			novelDetailResult(request, response, novel);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-edit.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			AdminDAO adminDao = new AdminDAO();
			
			String type = request.getParameter("type");
			int novelId = Integer.parseInt(request.getParameter("novelId"));
			int processingNum = 0;
			
			
			
			try {
			
			if(type.equals("title") || type.equals("summary")) {
				String updateText = request.getParameter("updateText");
				processingNum = adminDao.updateNovel(type, updateText, novelId);
			} else if(type.equals("genre_id") || type.equals("author_id")) {
				int updateId = Integer.parseInt(request.getParameter("updateId"));
				processingNum = adminDao.updateNovel(type, updateId, novelId);
			}
			
			if(processingNum == 0) {
				request.setAttribute("message", "更新できませんでした");
			}else if(processingNum == 1){
				request.setAttribute("message", "更新が完了しました");
			}
			
			List<AuthorBean> authorList = new ArrayList<>();
			List<GenreBean> genreList = new ArrayList<>();
			
			NovelBean novel = new NovelBean();
			NovelDAO novelDao = new NovelDAO();
			
			novel = novelDao.getNovelDetail(novelId);
			authorList = novelDao.getAllAuthors();
			genreList = novelDao.getAllGenres();
			//作家一覧
			authorResult(request, response, authorList);
			//ジャンル一覧
			genreResult(request, response, genreList);
			//小説詳細
			novelDetailResult(request, response, novel);
			
			
			
			request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-edit.jsp").forward(request, response);
			
			} catch(Exception e) {
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
		
				//ジャンル一覧の取得確認
				private void genreResult(HttpServletRequest request, HttpServletResponse response, List<GenreBean> genreList) {
					if(genreList == null || genreList.isEmpty()) {
						request.setAttribute("genreUnregistered", "作家が未登録です。");
					} else {
						request.setAttribute("genreList", genreList);
					}
				}
		
		//小説詳細の取得確認
		private void novelDetailResult(HttpServletRequest request, HttpServletResponse response, NovelBean novel) throws ServletException, IOException {
			if (novel == null) {
				request.setAttribute("noDetails", "詳細がありません。");
			} else {
				request.setAttribute("novel", novel);
			}
			request.getRequestDispatcher("/WEB-INF/admin-assets/admin-novel-edit.jsp").forward(request, response);
		}

}