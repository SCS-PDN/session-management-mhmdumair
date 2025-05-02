<%@ page import="java.util.*, com.example.serverlet.Course" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome to the Dashboard</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }

        List<Course> courses = (List<Course>) request.getAttribute("courses");
        List<Course> enrolledCourses = (List<Course>) request.getAttribute("enrolledCourses");
    %>

    <h3>Available Courses</h3>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Instructor</th><th>Action</th>
        </tr>
        <%
            for (Course c : courses) {
        %>
        <tr>
            <td><%= c.getCourseId() %></td>
            <td><%= c.getCourseName() %></td>
            <td><%= c.getInstructor() %></td>
            <td><a href="EnrollServlet?courseId=<%= c.getCourseId() %>">Enroll</a></td>
        </tr>
        <%
            }
        %>
    </table>

    <h3>Enrolled Courses</h3>
    <%
        if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
    %>
    <ul>
        <%
            for (Course ec : enrolledCourses) {
        %>
            <li><%= ec.getCourseName() %> by <%= ec.getInstructor() %></li>
        <%
            }
        %>
    </ul>
    <%
        } else {
    %>
        <p>No enrolled courses.</p>
    <%
        }
    %>

    <form action="LogoutServlet" method="get">
        <input type="submit" value="Logout"/>
    </form>
</body>
</html>
