package jeopardy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class browse
 */
@WebServlet("/jeopardy/browse")
public class browse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public browse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(
				new BufferedReader(new FileReader("/Applications/XAMPP/htdocs/CS4640/data/newData.txt")));

		int gameCount = 0;
		String line = "";
		String gameUsername;

		HttpSession session = request.getSession(true);
		Object currentUser = session.getAttribute("UserID");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (session.getAttribute("isLogin").toString().equals("Yes")) {
			out.println("<html>");
			out.println("<head>");
			out.println("  <title>Jeopardy Browse Page</title>");
			out.println("</head>");
			out.println("<body bgcolor = blue>");
			out.println("<font face = Calibri color = yellow>");
			out.println("<b><center><h1>Browse Games<h1></center></b>");
			out.println("<p align=right>" + currentUser + "</p>");
			// out.println("<p align= right>");
			out.println("<form align=right action=http://localhost:8080/Assignment4/jeopardy/login />");
			out.println("<button> <type=button name=logOut>Log Out </button>");
			out.println("</form>");
			// out.println("</p>");
			out.println("<input type=hidden name=GC>");
			session.setAttribute("GC", gameCount);
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				if (line.indexOf(":") >= 0) {
					gameCount++;
					gameUsername = line.substring(0, line.indexOf(":"));
					out.print("<h2> Game " + gameCount + "-" + gameUsername + "</h2>");
					if (gameUsername.equals(currentUser.toString())) {
						out.println("<form action=http://localhost:8080/Assignment4/jeopardy/update>");
						out.println("<button> <type=button name=Update> Update </button>");
						out.println("</form>");
						out.println("<form action=http://localhost:8080/Assignment4/jeopardy/delete />");
						out.println("<button> <type=button name=Delete> Delete </button>");
						out.println("</form>");
						

				
					}
					out.println("<input type=hidden name=GC>");
					session.setAttribute("GC", gameCount);
					out.println("<form action=http://localhost:8080/Assignment4/startGame.jsp />");
					out.println("<button> <type=button name=play> Play </button>");
					out.println("</form>");
				}
			}
			out.println("<center>");
			out.println("<form action=http://localhost:8080/Assignment4/jeopardy/JeopardyA4 />");
			out.println("<button> <type=button name=createGame>Create Game </button>");
			out.println("</form>");
			out.println("</center>");

		}

		else
			response.sendRedirect("http://localhost:8080/Assignment4/jeopardy/login");
		// out.println("<a
		// href=http://localhost:8080/cs4640/jeopardy/jeopardy4A.java
		// class=button>Create Game</a>");

		/*
		 * out.println("<font face = Calibri color = yellow>");
		 * out.println("<b><center><h1>Browse Your Games<h1></center></b>");
		 * out.println("<br />"); out.println("<h2>Game 1");
		 * out.println("<input type=button value=Update name=btn1 >");
		 * out.println("<input type=button value=Create name=btn2 >");
		 * out.println("<input type=button value=Play name=btn3 >");
		 * out.println("<br /><br />"); out.println("Game 2");
		 * out.println("<input type=button value=Update name=btn1 >");
		 * out.println("<input type=button value=Create name=btn2 >");
		 * out.println("<input type=button value=Play name=btn3 >");
		 * out.println("<br /><br />"); out.println("Game 3");
		 * out.println("<input type=button value=Update name=btn1 >");
		 * out.println("<input type=button value=Create name=btn2 >");
		 * out.println("<input type=button value=Play name=btn3 >");
		 * out.println("<br /><br />"); out.println("Game 4");
		 * out.println("<input type=button value=Update name=btn1 >");
		 * out.println("<input type=button value=Create name=btn2 >");
		 * out.println("<input type=button value=Play name=btn3 >");
		 * out.println("</h2>"); out.println("<br /><br />");
		 * out.println("</body>");
		 * 
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
