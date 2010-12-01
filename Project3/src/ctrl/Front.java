package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Resource;

/**
 * Servlet implementation class Front
 */
@WebServlet("/Front")
public class Front extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Front() {
        super();
       
    }
    @Override
	public void init() throws ServletException
	{
		//do nothing for now..
    	Resource rsrc = new Resource("CSE30333");
    	this.getServletContext().setAttribute("rsrc", rsrc);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String q = request.getParameter("q");
		String login = request.getParameter("login");
		Resource resource = (Resource)this.getServletContext().getAttribute("rsrc");
		request.getSession().setAttribute("resource", resource);
		
		RequestDispatcher rd;
		//rd = request.getRequestDispatcher("/views/login.jspx");
		
		if(q != null && q.equals("login"))
		{
			rd = request.getRequestDispatcher("/views/login.jspx");
			rd.forward(request, response);
			
		}
		else if(login != null && login.equals("go"))
		{
			String str = (String)request.getParameter("userPassword");
			System.out.println(str);
			rd = request.getRequestDispatcher("/views/bookResourceView.jspx");
			rd.forward(request, response);
		}else
		{
			rd = request.getRequestDispatcher("index.jspx");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
