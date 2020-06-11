package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Dao;
import model.Student;
@WebServlet(urlPatterns = {"/reqreg","/reqsearch","/reqviewallusers","/reqUpdate","/updatefrom"})
public class Controller extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter out=res.getWriter();
		String path=req.getServletPath();
		if(path.equals("/reqreg"))
		{
			
			boolean b=false;
			Student s=new Student();
			s.setSid(req.getParameter("t1"));
			s.setName(req.getParameter("t2"));
			s.setEmail(req.getParameter("t3"));
			s.setMarks(req.getParameter("t4"));
			s.setCity(req.getParameter("t5"));
			new Dao().insert(s);
			if(b)
			{
				RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
	               rd.include(req, res);
	               out.print("Record inserted successfully");
				
			}
		}
		else if(path.equals("/reqsearch"))
		{
			Student s=new Student();
			s.setSid(req.getParameter("sid"));
			s=new Dao().search(s);
			req.setAttribute("student", s);
			RequestDispatcher rd=req.getRequestDispatcher("viewStudent.jsp");
			rd.forward(req,res);
			
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out=res.getWriter();
		String path=req.getServletPath();
		if(path.equals("/reqviewallusers"))
		{
			ArrayList<Student> al=new ArrayList<Student>();
			al=new Dao().viewAllStudents();
			req.setAttribute("students", al);
			RequestDispatcher rd=req.getRequestDispatcher("viewStudents.jsp");
            rd.forward(req, res);
		}
		if(path.equals("/updatefrom"))
		{
			Student s=new Student();
		  s.setEmail(req.getParameter("id"));
		  s=new Dao().viewbyid(s);
		  req.setAttribute("student", s);
		  RequestDispatcher rd=req.getRequestDispatcher("updateFrom.jsp");
		  rd.forward(req,res);
	
		}
		if(path.equals("/reqUpdate"))
		{
			boolean b=false;
			Student s=new Student();
			s.setSid(req.getParameter("t1"));
	 		s.setName(req.getParameter("t2"));
	 		s.setEmail(req.getParameter("t3"));
	 		s.setMarks(req.getParameter("t4"));
	 		s.setCity(req.getParameter("t5"));
			b=new Dao().update(s);
	 		RequestDispatcher rd=req.getRequestDispatcher("reqviewallusers");
	         rd.forward(req, res);
	 		
		}
	}

}

