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
@WebServlet("/NovelListServlet")
public class NovelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<NovelBean> novelList = new ArrayList<>();
		List<NovelBean> listOfSearchedNovels = new ArrayList<>();
		NovelDAO nDao = new NovelDAO();
		
		try {
			String searchTitle = request.getParameter("search");
			
			authorList = nDao.getAllAuthors();
			novelList = nDao.getAllNovels();
			listOfSearchedNovels = nDao.searchAllNovelsByTitle(searchTitle);
			
			//作家一覧
			authorResult(request, response, authorList);
			
			if (searchTitle == null) {
				//小説一覧
				novelResult(request, response, novelList);				
			} else {
				//検索結果の小説一覧
				listOfSearchedNovelsResult(request, response, listOfSearchedNovels);
			}
			
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
			request.setAttribute("novelUnregistered", "小説が未登録です。");
		} else {
			request.setAttribute("novelList", novelList);
		}
	}
	
	//検索結果の小説一覧
	private void listOfSearchedNovelsResult(HttpServletRequest request, HttpServletResponse response, List<NovelBean> listOfSearchedNovels) {
		if(listOfSearchedNovels == null || listOfSearchedNovels.isEmpty()) {
			request.setAttribute("noSearchResult", "検索結果がありません。");
		} else {
			request.setAttribute("listOfSearchedNovels", listOfSearchedNovels);
		}
	}
}
