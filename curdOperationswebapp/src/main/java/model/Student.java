 package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student 
{
	@Id
  private String sid;
  private String name,email,city;
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

public String getMarks() {
	return marks;
}
public void setMarks(String marks) {
	this.marks = marks;
}

private String marks;
  
}
