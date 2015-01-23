package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.MySqlManager;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO get EAN
		response.setContentType("application/json");
		BufferedReader read = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String componentJson = read.readLine();
		System.out.println(componentJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	MySqlManager mysqlMan = new MySqlManager();
    	
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
           
        String username =request.getParameter("username");    
        String password =request.getParameter("userpass");   
             
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        	session.setAttribute("name", username);  
        
        if(mysqlMan.validateLogin(username, password)){    
           RequestDispatcher rd=request.getRequestDispatcher("/welcome.jsp");    
           rd.forward(request,response);    
        }else{    
            out.print("<p style=\"color:red\">Sorry username or password error</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("/admin.html");    
            rd.include(request,response);    
        }    
      
        out.close();  
	}

}
