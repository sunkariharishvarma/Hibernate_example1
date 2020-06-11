 <%@ page import="java.util.*" %>
<%@ page import="model.Student" %>

<%
ArrayList<Student> al=new ArrayList<Student>();

al=(ArrayList<Student>)request.getAttribute("students");

out.print("<body bgcolor=orange><br><br><center><table border=1 bgcolor=white cellpadding=10><tr><th>Email<th>Name<th>Sid<th>marks<th>city<th>Update<th>Delete");
for(Student s:al)
{
	out.print("<tr><th>"+s.getSid()+"<Th>"+s.getName()+"<th>"+ s.getEmail()+" <th> "+s.getMarks()+" <th>  "+s.getCity()+"<th><b><a href=updatefrom?id="+s.getEmail()+">Update</a><th><b><a href=reqdelete?id="+s.getEmail()+">Delete</a>");
 
}

%>