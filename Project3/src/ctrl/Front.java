package ctrl;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

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
		
		String q = request.getParameter("q");
		String login = request.getParameter("login");
		
		
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
