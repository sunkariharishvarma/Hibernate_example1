 package dao;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import model.Student;
public class Dao 
{
	SessionFactory sf=null;
	public Dao()
	{
		Configuration  cfg=new Configuration().configure().addAnnotatedClass(Student.class);
		sf=cfg.buildSessionFactory();
		
	}
	public void insert(Student s)
	{
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(s);
		tx.commit();
		sf.close();
	}
	public ArrayList<Student> viewAllStudents()
	{
		ArrayList<Student> al=new ArrayList<Student>();
		Session session=sf.openSession();
		al=(ArrayList<Student>)session.createQuery("from Student").list();
		
		return al;
		
	}
	public Student search(Student s)
	{

		Student s1=new Student();
		
		Session session=sf.openSession();
		s1=(Student)session.get(Student.class, s.getSid());
		return s1;
	}

	
	public Student viewbyid(Student s)
	{ 
		
		Student s1=new Student();
		Session session=sf.openSession();
		s1=(Student)session.get(Student.class, s.getEmail());
		
		return s1;
		
	}
	public boolean update(Student s)
	{
		boolean b=false;
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.update(s);
		b=true;
		tx.commit();
		session.close();
		
		return b;
	}

}
