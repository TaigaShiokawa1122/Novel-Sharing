package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashedPassword;
import model.dao.AdminDAO;

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminAddServlet")
public class AdminAddServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-add.jsp").forward(request, response);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		AdminDAO adminDao = new AdminDAO();
		
		String adminName = request.getParameter("adminName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int row = 0;
		
		try {
			String hashedPass = HashedPassword.hashPass(password);
			row = adminDao.addAdmin(adminName,email,hashedPass);
			
			//同じemailの管理者がいないかチェック
			if(row == 1) {
				request.setAttribute("message", "管理者を新規登録しました");
			} else { //新規登録失敗
				request.setAttribute("message", "そのメールアドレスは既に使用されています。");
			}
			
			request.getRequestDispatcher("/WEB-INF/admin-assets/admin-add.jsp").forward(request, response);
			
			//エラーハンドリング未着手
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}//終わり
