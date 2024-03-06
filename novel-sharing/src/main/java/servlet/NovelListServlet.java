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
		NovelDAO nDao = new NovelDAO();
		
		try {
			authorList = nDao.getAllAuthors();
			
			if(authorList == null || authorList.isEmpty()) {
				request.setAttribute("authorUnregistered", "作家が未登録です。");
			} else {
				request.setAttribute("authorList", authorList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("novel-list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
