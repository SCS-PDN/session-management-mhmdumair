package com.example.serverlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("login.html");
            return;
        }

        List<com.example.serverlet.Course> enrolledCourses = (List<com.example.serverlet.Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        Map<String, com.example.serverlet.Course> courseMap = new HashMap<>();
        courseMap.put("101", new com.example.serverlet.Course("101", "Java Programing", "Dr. Smith"));
        courseMap.put("102", new com.example.serverlet.Course("102", "Web Development", "Prof. Jane"));
        courseMap.put("103", new com.example.serverlet.Course("103", "Data Structures", "Dr. Ray"));

        com.example.serverlet.Course selectedCourse = courseMap.get(courseId);
        if (selectedCourse != null && !enrolledCourses.contains(selectedCourse)) {
            enrolledCourses.add(selectedCourse);
        }

        session.setAttribute("enrolledCourses", enrolledCourses);
        res.sendRedirect("DashboardServlet?message=Enroled+Succesfully");
    }
}
