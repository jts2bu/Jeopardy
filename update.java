package jeopardy;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class update
 */
@WebServlet("/jeopardy/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    public ArrayList<String> Qs = new ArrayList<String>();
	public ArrayList<String> As = new ArrayList<String>();
	
	
	/**
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		HttpSession session = request.getSession (true);
		Object currentUser = session.getAttribute("UserID");
		Object gameID = session.getAttribute("GC");
		
		response.setContentType ("text/html");
	    PrintWriter out = response.getWriter();
	    
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		int numQ = 0;
		String question = "";
		String answer = "";
		
		//ArrayList<String> Qs = new ArrayList<String>();
		//ArrayList<String> As = new ArrayList<String>();
		
		Scanner scan = new Scanner (new BufferedReader(new FileReader("/Applications/XAMPP/htdocs/CS4640/data/newData.txt")));

		URL textURL = new URL("http://plato.cs.virginia.edu/~rk2ea/data/data.txt");
		URLConnection yc = textURL.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			// out.println(inputLine);
			if (inputLine.matches(".+")) {
				if (inputLine.contains("?")) {
					numQ++;
					Qs.add(inputLine);
				} 
				else As.add(inputLine);
			}
		}
		
		
		in.close();
		String Ques = "";
		
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.contains(currentUser.toString() + ":" + gameID.toString())) 
			{
				
		out.println("<html>");
		out.println("<body>");
		out.println("<p align=right>" + currentUser + "</p>");
		out.println("<h5> JT Speetjens jts2bu </h3>");
		out.println("<h5> Rohan Koduri rk2ea </h3>");
		out.println("<center> <h3> Jeopardy Board </h3> </center>");
		out.println("<center>");
		out.println("<form method=post>");
		out.println("<table>");
		out.print("<style> table, th, td {border: 1px solid black;} </style>");
		for (int x = 0; x < numQ; x++) {
			line = scan.nextLine();
			out.println("<tr>");
			out.println("<td>");
			out.println("<p>");
			out.println("<b>Question </b>");
			out.println("<br>");
			out.println(Qs.get(x));
			out.println("</p>");
			out.println("<b> Answer </b>");
			out.println("<br>");
			out.println(As.get(x));
			out.println("</td>");
			out.println("<td>");
			out.println("<center> Row </center>");
			out.println("<p>");
			String rowVal = line.substring(line.indexOf(";")+1, line.indexOf("/"));
			out.println("<input type=text name=row value=" + rowVal +">");
			out.println("</p>");
			out.println("</td>");
			out.println("<td>");
			out.println("<center> Column </center>");
			out.println("<p>");
			String colVal = line.substring(line.indexOf("/")+1, line.indexOf("#"));
			out.println("<input type=text name=column value=" + colVal + ">");
			out.println("</p>");
			out.println("</td>");
			out.println("<td>");
			String PointVal = line.substring(line.indexOf("#")+1, line.indexOf("|"));
			out.println("<center> Points </center>");
			out.println("<p>");
			out.println("<input type=text name=points value=" + PointVal + ">");
			out.println("</p>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<input type = hidden name = numQues> </input>");
		    session.setAttribute("numQues", numQ);
		}
		out.println("</table>");
		out.print("<button> <type='submit'> Create Game </button>");
		//out.println("<button> <type='button'> Add Questions </button> <a href=http://plato.cs.virginia.edu/~rk2ea/Assignment2.html/> ");
		//out.println("<button> <type='submit'> Create Game </button>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
			
		
		// out.println("<center>");
		// out.println("<span style=display: inline;>");
		out.print("<center>");
		out.print("<form action=http://plato.cs.virginia.edu/~rk2ea/Assignment2.html> <button> <type='button'> Add Questions </button> </form>");
		out.print("</center>");
		//out.print("<button> <type='submit'> Create Game </button>");
		// out.println("</span>");
		// out.println("</center>");
			}
		}
		}
		
		
		
		
		
		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
