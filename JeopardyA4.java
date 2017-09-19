package jeopardy;

import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.*;
import java.util.*;

/**
 * Servlet implementation class JeopardyA4
 */
@WebServlet("/jeopardy/JeopardyA4")
public class JeopardyA4 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * Default constructor.
	 */
	/*
	 * public JeopardyA4() { // TODO Auto-generated constructor stub }
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 *     
	 */
	
	public ArrayList<String> Qs = new ArrayList<String>();
	public ArrayList<String> As = new ArrayList<String>();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession (true);
		Object currentUser = session.getAttribute("UserID");
		
		response.setContentType ("text/html");
	    PrintWriter out = response.getWriter();
	    
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		int numQ = 0;
		String question = "";
		String answer = "";
		
		//ArrayList<String> Qs = new ArrayList<String>();
		//ArrayList<String> As = new ArrayList<String>();

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
			out.println("<input type=text name=row />");
			out.println("</p>");
			out.println("</td>");
			out.println("<td>");
			out.println("<center> Column </center>");
			out.println("<p>");
			out.println("<input type=text name=column />");
			out.println("</p>");
			out.println("</td>");
			out.println("<td>");
			out.println("<center> Points </center>");
			out.println("<p>");
			out.println("<input type=text name=points />");
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
		
/*		URLConnection yc2 = textURL.openConnection();
		BufferedReader in2 = new BufferedReader(new InputStreamReader(yc2.getInputStream()));
		String inputLine2;
		while ((inputLine2 = in.readLine()) != null) {
			// out.println(inputLine);
			if (inputLine2 != "") {
				if (inputLine2.contains("?")) {
					question = inputLine2;
				} 
				else answer = inputLine2;
			}
		}
		in.close();
		
*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession (true);
		Object currentUser = session.getAttribute("UserID");
		Object gameID = session.getAttribute("GC");
		
		int gameIDint = Integer.parseInt(gameID.toString());
		
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		//doGet(request, response);
		FileWriter fw = new FileWriter("/Applications/XAMPP/htdocs/CS4640/data/newData.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		
		
		String[] byRow = request.getParameterValues("row");
		String[] byCol = request.getParameterValues("column");
		String[] byPoints = request.getParameterValues("points");
		
		pw.write(currentUser.toString() + ":");
		gameIDint++;
		pw.write(gameIDint + "\n");
		
		
		for(int i = 0, j = 0; i < Qs.size() && j < byRow.length; i++, j++){
			// System.out.println(Qs.get(i));
			pw.write(Qs.get(i) + ",");
			// System.out.println(As.get(i));
			pw.write(As.get(i) + ";");
			// System.out.println(byRow[i]);
			pw.write(byRow[j] + "/");
			// System.out.println(byCol[i]);
			pw.write(byCol[j] + "#");
			// System.out.println(byPoints[i]);
			pw.write(byPoints[j] + "||" + "\n");
		}
		pw.write("~" + "\n");
		
		pw.close();
		bw.close();
		fw.close();
		
		String maxRow = byRow[0];
		for (int r = 0; r < byRow.length; r++) {
			if (maxRow.compareTo(byRow[r]) < 0) {
				maxRow = byRow[r];
			}
		}
		
		String maxCol = byCol[0];
		for (int c = 0; c < byCol.length; c++) {
			if (maxCol.compareTo(byCol[c]) < 0) {
				maxCol = byCol[c];
			}
		}
		
		//System.out.println(maxRow);
		//System.out.println(maxCol);
		
		int maxRowInt = Integer.parseInt(maxRow);
		int maxColInt = Integer.parseInt(maxCol);
		
		//System.out.println("_____________");
		
		String [][] tbl = new String[100][100];
		
		for (int find = 0; find < byRow.length; find++) {
			int rowFind = Integer.parseInt(byRow[find]);
			int colFind = Integer.parseInt(byCol[find]);
			
			tbl[rowFind][colFind] = byPoints[find];
			/*
			System.out.print(rowFind + ",");
			System.out.print(colFind + ",");
			System.out.println(byPoints[find]);
			*/
		}
		
		for (int x1 = 0; x1 < 100; x1++) {
			for (int x2 = 0; x2 < 100; x2++) {
				if (tbl[x1][x2] == null) {
					tbl[x1][x2] = "__";
				}
			}
		}
		
		out.println("<html>");
		out.println("<body bgcolor=blue>");
		out.println("<center>");
		out.println("<font face = Calibri color = white>");
		out.println("<h4> JT Speetens and Rohan Koduri </h4>");
		out.println("<h4> jts2bu and r2kea </h4>");
		out.println("</font>");
		out.println("<font face = Calibri color = yellow size = 60>");
		out.println("<h3> Jeopardy! </h3>");
		out.println("</font>");
		out.println("<form>");
		out.println("<table>");
		out.print("<style> table, th, td {border: 1px solid yellow;} </style>");
		for (int rowNum = 0; rowNum <= maxRowInt; rowNum++) {
			out.println("<tr>");
			for (int colNum = 0; colNum <= maxColInt; colNum++) {
				out.println("<td>");
				out.println("<center>");
				out.println("<font size = 40 color = yellow face = Calibri>");
				out.println(tbl[rowNum][colNum]);
				out.println("</font>");
				out.println("</center>");
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</form>");
		out.print("<form action=http://plato.cs.virginia.edu/~rk2ea/Assignment2.html> <button> <type='button'> Add Questions </button> </form>");
		out.print("<form action=http://localhost:8080/Assignment4/jeopardy/JeopardyA4> <button> <type=button> Go Back </button>");
		out.println("</center>");
		out.println("</body>");
		
		
		
		/* out.println("<html>");
		out.println("<body>");
		out.println("<form>");
		out.println("<table>");
		out.print("<style> table, th, td {border: 1px solid black;} </style>");
		for (int rowCreator = 0; rowCreator < maxRowInt; rowCreator++) {
			for(int cellCreator = 0; cellCreator < 4; cellCreator++){
			out.println("<tr>");
			out.println("<td>");
			out.println(rowCreator);
			out.println(rowCreator.insertCell(cellCreator));
			out.println("</td>");
			out.println("</tr>");
			}
		}
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		 */
		
	}

}
