package com.example.serverlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            res.sendRedirect("login.html");
            return;
        }

        List<com.example.serverlet.Course> courses = Arrays.asList(
                new com.example.serverlet.Course("101", "Java Programming", "Dr. Smith"),
                new com.example.serverlet.Course("102", "Web Development", "Prof. Jane"),
                new com.example.serverlet.Course("103", "Data Structures", "Dr. Ray")
        );

        req.setAttribute("courses", courses);

        @SuppressWarnings("unchecked")
        List<com.example.serverlet.Course> enrolledCourses = (List<com.example.serverlet.Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        req.setAttribute("enrolledCourses", enrolledCourses);

        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(req, res);
    }
}
