<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<% 
  String id = request.getParameter("regId");
	String dbURL = "jdbc:mysql://localhost:3306/must";
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(dbURL,"must","1111");
	Statement stmt = conn.createStatement();
	
	stmt.executeUpdate(
		  "INSERT INTO USERS(UEMAIL,UPW,REGID)"
			+ " VALUES('suwoocher@naver.com','tjdncjf1','" + id + "')" 
			);
	
	//쿼리를 실행해서 ResultSet으로 받습니다.
	stmt.close();
	conn.close();
%>
