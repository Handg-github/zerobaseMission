package mc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/wifi/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO memberDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		if (action == null || action.equals("/home.do")) {
			request.setAttribute("lat", "0.0");
			request.setAttribute("lnt", "0.0");
			nextPage = "/sample.jsp";
		} else if (action.equals("/openApi.do")) {
			System.out.println("openApi.do");
			String size = String.valueOf(memberDAO.addWifi());
			request.setAttribute("size", size);
			nextPage = "/load-wifi.jsp";
		} else if (action.equals("/nearWifi.do")) {
			List<MemberVO> wifiList = memberDAO.wifiList();
			request.setAttribute("wifiList", wifiList);
			String lat = (String)request.getParameter("lat");
			String lnt = (String)request.getParameter("lnt");
			request.setAttribute("lat", lat);
			request.setAttribute("lnt", lnt);
			
			int no = memberDAO.countHistory();
			
			memberDAO.addHistory(lat, lnt, no);
			nextPage = "/sample.jsp";
		} else if (action.equals("/history.do")) {
			List<MemberVO2> historyList = memberDAO.historyList();
			
			request.setAttribute("historyList", historyList);
			nextPage = "/history.jsp";
		} else if (action.equals("/delete.do")) {
			String id = request.getParameter("id");
			System.out.println(id);
			memberDAO.delHistory(id);
			List<MemberVO2> historyList = memberDAO.historyList();
			request.setAttribute("historyList", historyList);
			nextPage = "/history.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
