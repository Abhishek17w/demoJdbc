package com.abhishek.DemoJdbc;

import java.sql.*;
/**
 * Hello world!
 * 1. import ---> java.sql.*
 * 2. load and register the driver --> com.mysql.jdbc.Driver
 * 3. Create Connection --> Connection
 * 4. Create a statement --> statement
 * 5. execute a query
 * 6. process the results
 * 7. close
 */


public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String url = "jdbc:mysql://localhost:3306/alien?useSSL=false";
    	String uname = "root";
    	String pass = "12345";
    	
    	String query = "select sname from student where rollno = 2";
    	String query1 = "insert into student values (3,'maddy')";
    	String query2 = "insert into student values (?,?)";
    	
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pass);
        Statement st = con.createStatement();
        
        //DQL
        //ResultSet rs = st.executeQuery(query);
        
        //rs.next();
        //String name = rs.getString("sname");
        //System.out.println(name);
        
        //DML
        //int count  = st.executeUpdate(query1);
        //System.out.println("rows affected " + count);
        
        int rollno = 4;
        String sname = "Pravin";
        PreparedStatement ps = con.prepareStatement(query2);
        ps.setInt(1, rollno);
        ps.setString(2, sname);
        int count1  = ps.executeUpdate();
        
        System.out.println("rows affected " + count1);
        
        st.close();
        con.close();
    }
}
