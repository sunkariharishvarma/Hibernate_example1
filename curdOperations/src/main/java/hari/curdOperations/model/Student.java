package hari.curdOperations.model;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Student 
{
	
	public String toString()
	{
		return "Student[sid="+sid+",name="+name+",email="+email+",city="+city+",marks="+marks+"]";
		
	}
	@Id
	private String sid;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	private String name,email,city;
	private int marks;

}
