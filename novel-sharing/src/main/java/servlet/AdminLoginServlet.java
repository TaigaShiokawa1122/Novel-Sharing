package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashedPassword;
import model.bean.AdminBean;
import model.dao.AdminDAO;

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/admin-assets/admin-login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO adminDao = new AdminDAO();
		AdminBean admin = new AdminBean();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			String hashedPass = HashedPassword.hashPass(password);
			admin = adminDao.adminLogin(email,hashedPass);
			if(admin == null) {
				request.setAttribute("notFound", "ログインに失敗しました。");
				request.getRequestDispatcher("/WEB-INF/admin-assets/admin-login.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("admin", admin);
				request.getSession().setAttribute("adminId", adminDao.getAdminId(email));
				response.sendRedirect("AdminNovelListServlet");
			}
		//Dao未着手
		//エラーハンドリング未着手
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}