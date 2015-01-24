package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.GraphManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GenerateGraph
 */
@WebServlet("/GenerateGraph.do")
public class GenerateGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateGraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
				
		BufferedReader bufferedReaderFromUserInput = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String a = bufferedReaderFromUserInput.readLine();
		String ean = null;
		JSONObject inputHardware = null;
		try {
			inputHardware = new JSONObject(a);
			ean = (String) inputHardware.getJSONObject("all").getString("EAN");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		GraphManager graphMan = new GraphManager();
		String imageLocation = graphMan.generateGraphByEan(ean);
		//TODO write image to frontend
		PrintWriter out = response.getWriter(); 
		out.print("<html>");
		out.print("<body>");
		out.print("<img src='"+imageLocation+"'>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}
}
