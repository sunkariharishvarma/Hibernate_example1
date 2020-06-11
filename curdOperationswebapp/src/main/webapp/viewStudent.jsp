 <%@ page import="model.Student"%>
 <%
 Student s=new Student();
 s=(Student)request.getAttribute("student");
 out.print("SID  City  Email  Marks Sname <br>");
 out.print(s.getSid()+"   "+s.getCity()+"  "+ s.getEmail()+"  "+s.getMarks()+"   "+s.getName()+"<br>" );
 %>