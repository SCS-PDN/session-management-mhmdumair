package com.example.serverlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, String> users = new HashMap<>();
        users.put("student1", "pass1");
        users.put("student2", "pass2");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 30); // 30 minutes
            res.addCookie(cookie);

            res.sendRedirect("DashboardServlet");
        } else {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<p>Invalid username or password.</p>");
            req.getRequestDispatcher("login.html").include(req, res);
        }
    }
}
