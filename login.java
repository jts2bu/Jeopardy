package jeopardy;
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.util.Scanner;
 
//*********************************************************************

@WebServlet("/jeopardy/login")
public class login extends HttpServlet
{
   private static final long serialVersionUID = 2L;
   
 //**** setting for local  ****/ 
   private static String LoginServlet = "http://localhost:8080/Assignment4/jeopardy/login";   
   private static String BrowseServlet = "http://localhost:8080/Assignment4/jeopardy/browse";

   private static String classWebsite = "http://www.cs.virginia.edu/upsorn/cs4640/";

   
   // a data file containing username and password 
   // note: this is a simple login information without encryption. 
   // In reality, credential must be encrypted for security purpose
   public static String user_info = "/Users/JT/Documents/user-info.txt";
   
   public static String survey_info = "/Applications/apache/webapps/cs4640/WEB-INF/data/survey-info.txt";
   

   // Form parameters.
   public String btn;
   // info for returning users
   public String userID;
   public String pwd;
   // info for new users
   public String newUserID;
   public String newPwd;
   
   
   // doPost() tells doGet() when the login is invalid.
   private static boolean invalidID = false;
   
   private int number_attempts = 0;

   /** *****************************************************
    *  Overrides HttpServlet's doGet().
    *  prints the login form.
   ********************************************************* */
   public void doGet (HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
   {
	  HttpSession session = request.getSession (true);
	  session.invalidate();
	  
      response.setContentType("text/html");
      PrintWriter out = response.getWriter ();

      out.println("<html>");
   
      out.println("<head>");
      out.println("  <title>Jeopardy Login</title>");
   
      out.println("   <style>");
      out.println("      body, html {");
      out.println("         margin: 10 auto;");
      out.println("         padding: 10;");
      out.println("         color: #202020;");
      out.println("         background-color: #c7fdc1;");
      out.println("         font-family: 'Lucida Grande',Verdana,Helvetica,Arial,Geneva,'Bitstream Vera Sans',Sans-Serif;");
      out.println("         font-size: 12px;");
      out.println("      }");     
         
      out.println("      input[type=text] {");  
      out.println("         border: 1px solid #cccccc;");
      out.println("         font: 11px Verdana;"); 
      out.println("         color: black;"); 
      out.println("         line-height: 1.4em;"); 
      out.println("      }");

      out.println("   </style>");

      out.println("</head>");

      out.println("<body onLoad=\"setFocus()\" >");
      out.println("<b><center><h1>Jeopardy Login<h1></center></b>");
   
      out.println("<br /><br />");
      
      if (invalidID)
      {  // called from doPost(), invalid ID entered.
         invalidID = false;
         out.println("<br><font color=\"red\">Invalid user ID, password pair. Please try again.</font><br><br>");
      }      
            
      // Returning users
      out.println("<form method=post");
   //   out.println("        action=\"" + LoginServlet + "\" id=\"form1\" name=\"form1\">");                      
      out.println("  <table Cellspacing=0 Cellpadding=3 Border=0 >");
      out.println("    <tr><td colspan=4><b>Returning Users:</b></td></tr>");
      out.println("    <tr>");
      out.println("      <td>UserID:</td>");
      out.println("      <td><input autofocus type=text name=UserID size=15 maxLength=20><td>");
      out.println("      <td>Password:</td>");
      out.println("      <td><input type=password name=pwd size=15 maxlength=20></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <td colspan=4><input type=submit value=\"Log In\" name=btn></input");
      out.println("    </tr>");
      out.println("  </table>");      
      out.println("</form>");
      
      out.println("<br />");
      out.println("<hr />");
      out.println("<br />");
      
      // Register new user
      out.println("<form method=post>");                     
      out.println("  <table Cellspacing=0 Cellpadding=3 Border=0>");
      out.println("    <tr><td colspan=4><b>Register New User:</b></td></tr>");
      out.println("    <tr>");
      out.println("      <td>UserID:</td>");
      out.println("      <td><input type=text name=newUserID size=15 maxLength=20></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <td>Password:</td>");
      out.println("      <td><input type=password name=newPwd size=15 maxlength=20></td>");        
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <td colspan=4><input type=submit value=Register name=btn></input>");
      out.println("    </tr>");
      out.println("  </table>");      
      out.println("</form>");
      
      out.println("<br />");
      out.println("<hr />");
      out.println("<br />");
    //  out.println("<a href=\"" + classWebsite + "\">CS4640 &mdash; class website</a>");
      out.println("<br />");
      
      out.println("</body>");
      out.println("</html>");

      out.close();
   }

   /*******************************************************
    *  Overrides HttpServlet's doPost().
    *            
      // assume an account will locked after 3 failed attempts
      // write code to check and handle this business logic 
      // (optional) 

   ********************************************************* */
   public void doPost (HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
   {
      HttpSession session = request.getSession (true);
	      
      btn = request.getParameter("btn");
	   
      userID = request.getParameter("UserID");
      pwd = request.getParameter("pwd");
      
      newUserID = request.getParameter("newUserID");
      newPwd = request.getParameter("newPwd");
            
      // need a more sophisticated input validation (not implemented yet)
            
    
      if(btn.equals("Register")){
    	  registerNewUser(newUserID, newPwd);
    	  response.sendRedirect(BrowseServlet);
      }
      
      else if (isValid(userID, pwd))
      {  // successful
         session.setAttribute("isLogin", "Yes");
         session.setAttribute("UserID", userID);
         response.sendRedirect(BrowseServlet);
      }
      else
      {  // unsuccessful
         session.setAttribute("isLogin", "No");
         session.setAttribute("UserID", ""); 
         response.sendRedirect(LoginServlet);
         
      }
     
   } 
   
      
   private boolean isValid(String userid, String password)
   {
      boolean is_valid = false;
      if (userid.length() == 0 || password.length() ==0)
         return false;
      
      try 
      {
         //BufferedWriter fileOut = new BufferedWriter(new FileWriter(user_info, true));    	  
         Scanner scanner = new Scanner (new BufferedReader(new FileReader(user_info))); 

         
         while (scanner.hasNextLine())
         {
            String line = scanner.nextLine(); 
            String existing_user = line.substring(0, line.indexOf(","));
            String existing_pwd = line.substring(line.indexOf(",")+1, line.lastIndexOf(","));
            if (userid.equals(existing_user) && password.equals(existing_pwd))
            {
               is_valid = true;
            }
         }
      
         scanner.close();
        // fileOut.close();
      
      } catch (Exception e) {
         System.out.println("Unable to verify the user information ");
         
         // One potential cause of this exception is the path to the data file
         // To verify, open the data file in a web browser
         // Use the path you saw in the browser's address bar to access the data file
         // (note: excluding "file:///")
      
      }
      
      return is_valid;	   
   }
   

   private void registerNewUser(String userid, String pwd) throws IOException  
   {
	   FileWriter fw = new FileWriter(user_info, true);
	   BufferedWriter bw = new BufferedWriter(fw);
	   PrintWriter pw = new PrintWriter(bw);
	 
	   pw.write(userid);
	   pw.write(",");
	   pw.write(pwd);
	   pw.write(", \n");
	   
	   pw.close();
	   
   }
   
} 