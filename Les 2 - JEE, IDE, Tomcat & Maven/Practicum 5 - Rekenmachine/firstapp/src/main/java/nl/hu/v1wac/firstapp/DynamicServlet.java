package nl.hu.v1wac.firstapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			int resultaat = 0;
			int c1 = Integer.parseInt(req.getParameter("cijferEen"));
			int c2 = Integer.parseInt(req.getParameter("cijferTwee"));
			String operator = req.getParameter("operator");
			
			if (operator.equals("+"))
				resultaat = c1 + c2;
			if (operator.equals("/"))
				resultaat = c1 / c2;
			if (operator.equals("-"))
				resultaat = c1 - c2;
			if (operator.equals("*"))
				resultaat = c1 * c2;

				
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("text/html");
			 
			 out.println("<!DOCTYPE html>");
			 out.println("<html>");
			 out.println(" <title>Rekenmachien</title>");
			 out.println(" <body>");
			 out.println(" <h2>Dynamic webapplication example</h2>");
			 out.println(" <h2>Resultaat is " + resultaat + "!</h2>");
			 out.println(" </body>");
			 out.println("</html>");
			


	}
}