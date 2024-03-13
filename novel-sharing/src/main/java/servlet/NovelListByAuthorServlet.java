package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.NovelBean;
import model.dao.NovelDAO;

/**
 * Servlet implementation class NovelListByAuthorServlet
 */
@WebServlet("/NovelListByAuthorServlet")
public class NovelListByAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		String searchString = request.getParameter("searchString");
		
		List<NovelBean> novelList = new ArrayList<>(); //小説一覧
		List<NovelBean> listOfSearchedNovels = new ArrayList<>(); //検索
		NovelDAO nDao = new NovelDAO();
		
		try {
			novelList = nDao.getNovelListByAuthor(authorId);
			listOfSearchedNovels = nDao.searchAllNovesByAuthor(authorId, searchString);
		
			//小説一覧
			novelListByAuthor(request, response, novelList, authorId);
			//検索
			listOfSearchedNovelsByAuthorResult(request, response, listOfSearchedNovels, authorId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	//作家別小説一覧　取得結果
	private void novelListByAuthor(HttpServletRequest request, HttpServletResponse response, List<NovelBean> novelList ,int authorId) 
			throws ServletException, IOException {
		if (novelList == null || novelList.isEmpty()) {
			request.setAttribute("novelUnregistered", "小説が未登録です。");
		} else {
			request.setAttribute("novelList", novelList);
			request.getSession().setAttribute("authorId", authorId);
		}
		request.getRequestDispatcher("novel-list-by-author.jsp").forward(request, response);
	}
	
	//検索　取得結果
		private void listOfSearchedNovelsByAuthorResult(HttpServletRequest request, HttpServletResponse response, List<NovelBean> listOfSearchedNovels, int authorId) 
				throws ServletException, IOException {
			if (listOfSearchedNovels == null || listOfSearchedNovels.isEmpty()) {
				request.setAttribute("noSearchResult", "検索結果がありませんでしたs。");
			} else {
				request.setAttribute("novelList", listOfSearchedNovels);
				request.getSession().setAttribute("authorId", authorId);
			}
			request.getRequestDispatcher("novel-list-by-author.jsp").forward(request, response);
		}
}
