package ctrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import resourcesCatalog.jaxb.Bookings;
import resourcesCatalog.jaxb.Resources;
import resourcesCatalog.jaxb.Users;

import model.ResourcesCatalogModel;



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
		String rCatalogFile = this.getServletConfig().getInitParameter("ResourcesCatalog");
    	String realRCatalogFilePath = this.getServletContext().getRealPath(rCatalogFile);
    	
    	System.out.println(realRCatalogFilePath);  //TODO: use System.getProperty("file.separator");
    	
    	try {
			ResourcesCatalogModel model = new ResourcesCatalogModel(realRCatalogFilePath);
			this.getServletContext().setAttribute("model", model);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.err.println("JAXB exception");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("FileNotFound exception");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IOException");
			e.printStackTrace();
		}
    	
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourcesCatalogModel model = (ResourcesCatalogModel) this.getServletContext().getAttribute("model");
		Iterator <Users> uItr =  model.getUsers().values().iterator();
		request.getSession().setAttribute("users", uItr);
		Iterator <Resources> rItr = model.getResources().values().iterator();
		request.getSession().setAttribute("resources", rItr);
		Iterator <Bookings> bItr = model.getBookings().values().iterator();
		request.getSession().setAttribute("bookings", bItr);
		
		String q = request.getParameter("q");
		String login = request.getParameter("login");
		String rid = request.getParameter("rid");
		//String res = request.getParameter("res");
		
		RequestDispatcher rd;
		
		
		//shant integration
		String date = request.getParameter("date");
		String res = request.getParameter("res");
		
		
		
		
		if((date != null) || (res != null)){

			System.out.println(res +"\t"+date);
			request.getSession().setAttribute("Room", res);
			if(date != null){request.getSession().setAttribute("Date", date);}
			rd = request.getRequestDispatcher("views/Rooms/Room1.jspx");
			rd.forward(request, response);

		}
		else 
		if(q != null && q.equals("viewUsers"))
		{
			rd = request.getRequestDispatcher("/views/viewUser.jspx");
			rd.forward(request, response);
		}
		else if(q != null && q.equals("viewResource"))
		{
			//rd = request.getRequestDispatcher("/views/viewUser.jspx");
			rd = request.getRequestDispatcher("/views/bookResourceView.jspx");
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
