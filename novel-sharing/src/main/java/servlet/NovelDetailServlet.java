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
 * Servlet implementation class NovelDetailServlet
 */
@WebServlet("/NovelDetailServlet")
public class NovelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		
		NovelBean novel = new NovelBean();
		
		NovelDAO nDao = new NovelDAO();
		
		Integer novelId = Integer.parseInt(request.getParameter("novelId"));
		
		try {
			novel = nDao.getNovelDetail(novelId);
			authorList = nDao.getAllAuthors();
			//作家一覧
			authorResult(request, response, authorList);
			//小説詳細
			novelDetailResult(request, response, novel);
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
	
	//小説詳細の取得確認
	private void novelDetailResult(HttpServletRequest request, HttpServletResponse response, NovelBean novel) throws ServletException, IOException {
		if (novel == null) {
			request.setAttribute("noDetails", "詳細がありません。");
		} else {
			request.setAttribute("novel", novel);
		}
		request.getRequestDispatcher("novel-detail.jsp").forward(request, response);
	}
}