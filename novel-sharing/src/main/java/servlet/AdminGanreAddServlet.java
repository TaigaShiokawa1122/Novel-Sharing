package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AdminDAO;

/**
 * Servlet implementation class NovelListServlet
 */
@WebServlet("/AdminGanreAddServlet")
public class AdminGanreAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminDAO adminDao = new AdminDAO();
		String ganre = request.getParameter("ganre");
		
		int row = 0;
		
		try {
			row = adminDao.addGanre(ganre);
			
			//同じジャンル名が既に存在しているか
			if(row == 1) {
				request.setAttribute("message", "ジャンルを新規登録しました");
				request.getRequestDispatcher("AdminNovelListServlet").forward(request, response);
				return;
			} else { //新規登録失敗
				request.setAttribute("error", "そのジャンル名は既に使用されています。");
				request.getRequestDispatcher("admin-ganre-add.jsp").forward(request, response);
			}
		//Dao未着手
		//エラーハンドリング未着手
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}