package com.abhishek.DemoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDaoDemo {

	public static void main(String args[]) throws Exception {
		
		StudentDao dao = new StudentDao();
		Student s1 = dao.getStudent(3);
		System.out.println(s1.sname);
		
		Student s2 = new Student();
		s2.rollno = 5;
		s2.sname = "Arjun";
		dao.connect();
		dao.addStudent(s2);
	}
}

class StudentDao{
	Connection con = null; 
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alien?useSSL=false","root","12345");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public Student getStudent(int rollno) throws Exception {
  		String query = "select sname from student where rollno = " + rollno;
		Student s = new Student();
		s.rollno = rollno;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alien?useSSL=false","root","12345");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String name = rs.getString(1);
		s.sname = name;
		return s;
	}
	
	public void addStudent(Student s) {
		String query = "insert into Student values (?,?)";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, s.rollno);
			pst.setString(2, s.sname);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Student{
	int rollno;
	String sname;
}
