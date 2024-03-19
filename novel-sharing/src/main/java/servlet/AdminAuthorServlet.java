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
import model.dao.AdminDAO;

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminAuthorServlet")
public class AdminAuthorServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AuthorBean> authorList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		try {
			authorList = adminDao.getAllAuthors();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("authorList",authorList);
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-author.jsp").forward(request, response);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<AuthorBean> authorList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		String authorName = request.getParameter("authorName");
		String furigana = request.getParameter("furigana");
		authorName = authorName.replaceAll("\\s", "");
		furigana = furigana.replaceAll("\\s", "");
		
		int row = 0;
		boolean unregistered = false;
		
		try {
			//同じジャンル名が既に存在しているか
			unregistered = adminDao.getAuthor(authorName);
			
			if(unregistered) {
				row = adminDao.addAuthor(authorName,furigana);
				
					if(row == 1) {
						request.setAttribute("message", "著者「" + authorName + "」を新規登録しました");
					
					} else { //新規登録失敗
						request.setAttribute("error", "登録できませんでした");
					}
					
			} else {
				request.setAttribute("error", "ジャンル名「" + authorName + "」は既に存在しています");
			}
			

			try {
				authorList = adminDao.getAllAuthors();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("authorList",authorList);
			request.getRequestDispatcher("/WEB-INF/admin-assets/admin-author.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}//終わり
