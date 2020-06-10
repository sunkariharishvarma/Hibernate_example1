package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Orders;
import model.Product;
import model.User;

public class Daoimple implements Dao
{
	SessionFactory sf=null;
	
	
	public Daoimple()
	{
		Configuration cfg=new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class).addAnnotatedClass( Orders.class);
		sf=cfg.buildSessionFactory();
		
	}
	
	static Connection con=null;
	public static Connection  getConnectionObject() 
    {
	   try {
         
		  Class.forName("org.h2.Driver");
		  con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/MyProject2","sa","sa");
	      }
         catch(Exception e)
	     {
	     System.out.println(e);
	     }		
	     return con;	
    }

/*public boolean adminlogin(User a)
	{ boolean b=false;
	con=Daoimple.getConnectionObject();
	 try {
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from admin where name='"+a.getName()+"'and pwd='"+a.getPwd()+"'");
		if(rs.next()) {
		b=true;
		
		}
	    } 
	 catch (SQLException e)
	   {
		System.out.println(e);
	   }
	 return b;	
	}*/
	
	
	

	public boolean register(User u)
	{
		boolean b=false;
		
		Object  obj=null;
          
        	  if(u.getUtype().equals("reqempreg"))
      		{
        		
               Session session=sf.openSession();
               Transaction tx=session.beginTransaction();
              session.save(u);
              tx.commit();
		     session.close();
      		}
        	  
        	else if (u.getUtype().equals("reqcusreg"))
      		{
        	 Session session=sf.openSession();
               Transaction tx=session.beginTransaction();
               session.save(u);
               tx.commit();
       		   session.close();
        	}
        	  
        	  
		
		return b;
	}
	
	
	public boolean login(User u)
	{
		boolean b=false;
		
	
	
		if(u.getUtype().equals("reqemplogin"))
		{
			 Session session=sf.openSession();
			 u=(User)session.get(User.class, u.getEmail());
			 session.close();
			 
		
		}
		else if(u.getUtype().equals("reqcuslogin"))
		{
			Session session=sf.openSession();
			 u=(User)session.get(User.class, u.getEmail());
			 session.close();
		}
	
	b=true;	
		
	
		
	
		return b;
		
	}
	
	
	public boolean forgot(User u) 
	{
		boolean b=false;
	int  rs=0;
		con=Daoimple.getConnectionObject();
		try {
		Statement s=con.createStatement();
		
	
		if(u.getUtype().equals("reqempfor"))
		{
			 
		rs=s.executeUpdate("update emp1 set pwd='"+u.getPwd()+"' where email='"+u.getEmail()+"'   ");
		}
		else if(u.getUtype().equals("reqcusfor"))
		{
			rs=s.executeUpdate("update  cus1 set pwd='"+u.getPwd()+"' where email='"+u.getEmail()+"'  ");
		}
		
		if(rs >0 )
		{
			b=true;
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	  return b;
	
		
	}
	
    
    public User cusviewbyid(User u,String name) 
	{
		Session session=sf.openSession();
		u=(User)session.get(name, u.getEmail());
		session.close();
	 
		
		
		
	 return u;
	}
	
	public User empviewbyid(User u) 
	{
		Session session=sf.openSession();
		u=(User)session.get(User.class, u.getEmail());
		session.close();
	 
		
		
		
	 return u;
	}


	public boolean addProduct(Product p) {
		
		
		boolean b=false;
		
		Date d=new Date();
		java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
           session.save(p);
         tx.commit();
	     session.close();
		    return b;
	}


	public ArrayList<Product> viewallproduct(Product p) {
		ArrayList<Product> al=new ArrayList<Product>();
		ResultSet rs=null;
	    con=Daoimple.getConnectionObject();
		try {
			
			Statement stmt=con.createStatement();
		rs=stmt.executeQuery("select pid,pname,pprice,filename,productinfo from stock1 ");
			while(rs.next())
			{
				Product p1=new Product();
				p1.setPid(rs.getString(1));
				p1.setPname(rs.getString(2));
				p1.setPprice(rs.getDouble(3));
				p1.setFilename(rs.getString(4));
				p1.setPinfo(rs.getString(5));
				al.add(p1);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return al;
	}


	public ArrayList<Product> searchProduct(Product p) 
	{
		ArrayList<Product> al=new ArrayList<Product>();
		ResultSet rs=null;
	    con=Daoimple.getConnectionObject();
	    
		try {
			
			Statement stmt=con.createStatement();
			
		rs=stmt.executeQuery("select pid,pname,pprice,filename,productinfo from stock1 where pname like '%"+p.getPname().toLowerCase()+"%'");
	
		while(rs.next())
			{
				Product p1=new Product();
				p1.setPid(rs.getString(1));
				p1.setPname(rs.getString(2));
				p1.setPprice(rs.getDouble(3));
				p1.setFilename(rs.getString(4));
				p1.setPinfo(rs.getString(5));
				al.add(p1);
		    }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return al;
	}
	
	public ArrayList<Product> searchProduct(Product p,String req) 
	{
		ArrayList<Product> al=new ArrayList<Product>();
		ResultSet rs=null;
	    con=Daoimple.getConnectionObject();
		try {
			
			Statement stmt=con.createStatement();
		   if(req.equals("lh"))
		   {
			rs=stmt.executeQuery("select pid,pname,pprice,filename,productinfo from stock1 where pname like '%"+p.getPname().toLowerCase()+"%' order by pprice asc");
		   }
		   else if(req.equals("hl"))
		   {
			   rs=stmt.executeQuery("select pid,pname,pprice,filename,productinfo from stock1 where pname like '%"+p.getPname().toLowerCase()+"%' order by pprice desc");
		   }
			while(rs.next())
			{
				Product p1=new Product();
				p1.setPid(rs.getString(1));
				p1.setPname(rs.getString(2));
				p1.setPprice(rs.getDouble(3));
				p1.setFilename(rs.getString(4));
				p1.setPinfo(rs.getString(5));
				al.add(p1);
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return al;
		
	}

	
	
	



	
	
	public Product productInfoByPid(Product p1)
	{
		ResultSet rs=null;
		Product p=new Product();
		con=Daoimple.getConnectionObject();
		try 
		{
			Statement s=con.createStatement();
			rs=s.executeQuery("select * from stock1 where pid='"+p1.getPid()+"'");
			if(rs.next())
			{
				p.setPid(rs.getString(1));
				p.setPname(rs.getString(2));
				p.setPprice(rs.getDouble(3));
			//	p.setPqty(rs.getInt(4));
				p.setFilename(rs.getString(5));
				p.setPinfo(rs.getString(8));
				   
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

  return p;
	}


	public boolean addproducttocart(Product p,String name) 
	{ boolean b=false;
	  User u=new User();  
	
	    ResultSet rs=null;
	System.out.println();
	     Date d=new Date();
	     java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		con=getConnectionObject();
		try {
			PreparedStatement ps=con.prepareStatement("insert into orders11( FILENAME , 	PID , 	PNAME  ,	PPRICE,PQTY    ,	TPRICE  ,	CNAME  ,	ODATE  ) values(?,?,?,?,?,?,?,?)");
			ps.setString(1,p.getFilename());	
			ps.setString(2,p.getPid());
			ps.setString(3,p.getPname());
			ps.setDouble(4,p.getPprice());
			ps.setInt(5,p.getPqty());
			ps.setDouble(6,(p.getPprice())*p.getPqty());
			ps.setString(7,name);  
			ps.setDate(8,d1);
			int i=ps.executeUpdate();
			if(i>0)
	       b=true;
		   } 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
		
		return b;
	}


	public void addTOCart(Product p)
	{
		
		
	}


	public ArrayList<Product> viewproductfromcart(String name)
	{ResultSet rs=null;
		ArrayList<Product> al=new ArrayList<Product>();
		con=getConnectionObject();
		try {
			Statement ps=con.createStatement();
			 rs=ps.executeQuery(" select FILENAME,PID,PNAME,PPRICE,PQTY,TPRICE from orders11 where cname='"+name+"' ");
			while(rs.next())
			{
				
				Product p=new Product();
				
				p.setFilename(rs.getString(1));
				p.setPid(rs.getString(2));
				p.setPname(rs.getString(3));
				p.setPprice(rs.getDouble(4));
				p.setPqty(rs.getInt(5));
			///	p.setTprice(rs.getDouble(6));
				al.add(p);
          }
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return al;
	}

	public Orders sumoftotal(String name)
	{boolean b=false;
		Orders o=new Orders();
	   ResultSet rs=null;
	con=getConnectionObject();
	try {
		Statement ps=con.createStatement();
		 rs=ps.executeQuery(" select count(*),sum(tprice) from orders11 where cname='"+name+"'  ");
		 
		if(rs.next())
		{ 
			o.setCount(rs.getInt(1));
			o.setSumoftotal(rs.getDouble(2));
			
	    }
		
				
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
		return o ;
	
	}
	
	
	
	
	
	

	public boolean updateCus(User u) {
		boolean b=false;
		con=getConnectionObject();
		try {
			Statement ps=con.createStatement();
			int i=ps.executeUpdate("update cus1 set NAME='"+u.getName()+"' ,PWD='"+u.getPwd()+"' ,MOBILE='"+u.getMobile()+"' ,CITY='"+u.getCity()+"'  	FNAME='"+u.getFname()+"' where email='"+u.getEmail()+"'  ");
		if(i>0)
		{
			b=true;
		}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		
		return b;
	}


	public boolean addproducttocart1(Product p, String name)
	{
		boolean b=false;
		  User u=new User();  
		  ArrayList<Product> al=new ArrayList<Product>();
		    ResultSet rs=null;
	        Date d=new Date();
		     java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		  //   java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
			con=getConnectionObject();
			try {
				Statement st=con.createStatement();
				rs=st.executeQuery("select * from orders11 where cname='"+name+"'");
				while(rs.next())
				{ Product p1=new Product();
					p1.setFilename(rs.getString(1));
					p1.setPid(rs.getString(2));
					p1.setPname(rs.getString(3));
					p1.setPprice(rs.getDouble(4));
					p1.setPqty(rs.getInt(5));
					//p1.setTprice(rs.getDouble(6));
					al.add(p1);
				}
				
				for(Product p1:al)
				{
					Statement stmt=con.createStatement();
				int i=	stmt.executeUpdate("insert into orders12( FILENAME , 	PID , 	PNAME  ,	PPRICE,PQTY    ,	TPRICE  ,	CNAME  ,	ODATE,ddate) values('"+p1.getFilename()+"','"+p1.getPid()+"','"+p1.getPname()+"','"+p1.getPprice()+"','"+p1.getPqty()+"','"+(p1.getPprice()*p1.getPqty())+"','"+name+"',now(),now()+5)");                                     
		
				if(i>0)
				{
			
				int j=st.executeUpdate("delete from orders11 where cname='"+name+"'");
				if(j>0)
				{
					b=true;
				}
				
					
				}
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			return b;
		
	
	}


	public ArrayList<Product> viewproductfromMyOrders(String name) {
		
		ResultSet rs=null;
		ArrayList<Product> al=new ArrayList<Product>();
		con=getConnectionObject();
		try {
			Statement ps=con.createStatement();
			 rs=ps.executeQuery(" select FILENAME,PID,PNAME,PPRICE,PQTY,TPRICE from orders12 where cname='"+name+"' ");
			while(rs.next())
			{
				
				Product p=new Product();
				
				p.setFilename(rs.getString(1));
				p.setPid(rs.getString(2));
				p.setPname(rs.getString(3));
				p.setPprice(rs.getDouble(4));
				p.setPqty(rs.getInt(5));
			///s	p.setTprice(rs.getDouble(6));
				al.add(p);
          }
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return al;
	}



	
	
	
	
	
	
	

}
