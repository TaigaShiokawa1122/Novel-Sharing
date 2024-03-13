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
 * Servlet implementation class NovelListByAuthorServlet
 */
@WebServlet("/NovelListByAuthorServlet")
public class NovelListByAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		String searchString = request.getParameter("search");
		
		List<AuthorBean> authorList = new ArrayList<>();
		List<NovelBean> novelList = new ArrayList<>(); //小説一覧
		List<NovelBean> listOfSearchedNovels = new ArrayList<>(); //検索
		NovelDAO nDao = new NovelDAO();
		
		try {
			authorList = nDao.getAllAuthors(); 
			novelList = nDao.getNovelListByAuthor(authorId);
			listOfSearchedNovels = nDao.searchAllNovesByAuthor(authorId, searchString);
		
			//作家一覧
			authorResult(request, response, authorList);
			if (searchString == null || searchString.isEmpty()) {
				//小説一覧
				novelListByAuthor(request, response, novelList);
			} else {
				//検索
				listOfSearchedNovelsByAuthorResult(request, response, listOfSearchedNovels);				
			}
			
			request.getSession().setAttribute("authorId", authorId);
		} catch(Exception e) {
			e.printStackTrace();
		}
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

	//作家別小説一覧　取得結果
	private void novelListByAuthor(HttpServletRequest request, HttpServletResponse response, List<NovelBean> novelList) 
			throws ServletException, IOException {
		if (novelList == null || novelList.isEmpty()) {
			request.setAttribute("novelUnregistered", "小説が未登録です。");
		} else {
			request.setAttribute("novelList", novelList);
		}
		request.getRequestDispatcher("novel-list-by-author.jsp").forward(request, response);
	}
	
	//検索　取得結果
	private void listOfSearchedNovelsByAuthorResult(HttpServletRequest request, HttpServletResponse response, List<NovelBean> listOfSearchedNovels) 
			throws ServletException, IOException {
		if (listOfSearchedNovels == null || listOfSearchedNovels.isEmpty()) {
			request.setAttribute("noSearchResult", "検索結果がありませんでしたs。");
		} else {
			request.setAttribute("novelList", listOfSearchedNovels);
		}
		request.getRequestDispatcher("novel-list-by-author.jsp").forward(request, response);
	}
}
