package jeopardy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class delete
 */
@WebServlet("/jeopardy/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession (true);
		Object currentUser = session.getAttribute("UserID");
		Object gameCount = session.getAttribute("GC");

		response.setContentType("text/html");
	    PrintWriter out = response.getWriter ();
		
		File data = new File("/Applications/XAMPP/htdocs/cs4640/data/newData.txt");
		File data2 = new File("/Applications/XAMPP/htdocs/cs4640/data/newData2.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(data));
		BufferedWriter writer = new BufferedWriter(new FileWriter(data2));
		System.out.println(currentUser);
		System.out.println(gameCount);
		String lineToRemove = currentUser.toString();
		

		String currentLine;
		
		while((currentLine = reader.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(trimmedLine.contains(lineToRemove)) continue;
			writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = data2.renameTo(data);
		
			 out.println("<html>");
			    out.println("<head>");
			    out.println("  <title>Jeopardy Delete Page</title>");
			    out.println("</head>");
			    out.println("<body bgcolor = blue>");
			    out.println("<font face = Calibri color = yellow>");
			    out.println("<b><center><h1>Game Deleted!<h1></center></b>");
			    out.println("<p align=right>" + currentUser + "</p>");
			   // out.println("<p align= right>");
			    out.println("<form align=right action=http://localhost:8080/Assignment4/jeopardy/login />");
		    	out.println("<button> <type=button name=logOut>Log Out </button>");
		    	out.println("</form>");
		    	out.println("</body>");
		
		/*Scanner scan = new Scanner (new BufferedReader(new FileReader(data)));
		while(scan.hasNextLine()) {
		String line = scan.nextLine();
		System.out.println(currentUser.toString());
		System.out.println(gameCount.toString());
		
		if (!(line.contains(currentUser.toString()+":"+gameCount.toString()))) {
			othertext = othertext +"\n";
			othertext += line;
			System.out.println(othertext);
			
		}
		}
		pw.write(othertext);
		pw.close();
		
		
		 */
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
