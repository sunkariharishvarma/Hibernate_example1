<%@ page import="java.util.*" %>
<%@ page import="model.Student" %>
<%
Student s=new Student();
s=(Student)request.getAttribute("Student");
out.print("<br><br><center><form action=requpdate><table border=1><tr><td>Sid<td>input type=text readonly value="+s.getSid()+" name=t1>");
out.print(" <tr><td>Name<td><input type=text value="+s.getName()+" name=t2 > ");
out.print("<tr><td>email<td><input type=text value="+s.getEmail()+" name=t3 > ");
out.print(" <tr><td>marks<td><input type=text value="+s.getMarks()+" name=t4 > ");
out.print(" <tr><td>City<td><input type=text value="+s.getCity()+" name=t5 > ");

out.print("<tr><td colspan=2><center><button>UpdateDetails ");




%>
