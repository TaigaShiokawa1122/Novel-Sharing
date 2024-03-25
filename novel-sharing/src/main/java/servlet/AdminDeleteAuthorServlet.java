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


@WebServlet("/AdminDeleteAuthorServlet")
public class AdminDeleteAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<AuthorBean> authorList = new ArrayList<>();
		AdminDAO adminDao = new AdminDAO();
		
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		int row = 0;
		boolean unregistered = false;
		
		try {
			//このジャンルを使用していないか
			unregistered = adminDao.unusedAuthor(authorId);
			
			if(unregistered) {
				
				row = adminDao.deleteAuthor(authorId);
				
					if(row == 1) {
						request.setAttribute("message", "著者名「" + authorName + "」を削除しました");
					} else { //新規登録失敗
						request.setAttribute("error", "削除できませんでした");
					}
					
			} else {
				request.setAttribute("error", "著者名「" +authorName + "」は使用済みのため削除できません");
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
}
