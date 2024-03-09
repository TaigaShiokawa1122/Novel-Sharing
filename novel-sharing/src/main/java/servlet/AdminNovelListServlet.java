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

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminNovelListServlet")
public class AdminNovelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<NovelBean> novelList = new ArrayList<>();
		NovelDAO nDao = new NovelDAO();
		
		try {
			authorList = nDao.getAllAuthors();
			novelList = nDao.getAllNovels();
			
			//作家一覧
			authorResult(request, response, authorList);
			//小説一覧
			novelResult(request, response, novelList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("novel-list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
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
			request.setAttribute("novelUnregistered", "作家が未登録です。");
		} else {
			request.setAttribute("novelList", novelList);
		}
	}

}
