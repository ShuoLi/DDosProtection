<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import = "java.util.regex.Pattern" %>
<%@page import = "java.util.regex.Matcher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script type="text/javascript">
function ValidateLoginForm(form)
{
	if(form.userid.length > 15){
		alert("Username cannot be more than 15 charaters!");
		form.userid.focus();
		return false;
	}
	if(form.pwd.length > 15){
		alert("Password cannot be more than 15 charaters!");
		form.userid.focus();
		return false;
	}
	if(form.userid.value == ""){
		alert("Username cannot be empty!");
		form.userid.focus();
		return false;
	}
	if(form.pwd.value == ""){
		alert("Password cannot be empty!");
		form.password.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>

<div align="center">
<form name ="form" method ="post" action="Login.jsp" onSubmit="return ValidateLoginForm(this)">
<p>Username:
   <input type="text" name="userid">
</p>
<p>Password:
   <input type="password" name="pwd">
</p>
<input type="submit" value="Login" name="login">
</form>
</div>

</body>
</html>

<%
String user=request.getParameter("userid"); 
session.putValue("userid",user); 
String pwd=request.getParameter("pwd"); 
//String usertype=request.getParameter("usertype");

Class.forName("com.mysql.jdbc.Driver"); 
//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","gyg19900918"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://dbase.cs.jhu.edu/cs41512_ygong8_db","415_12_ygong8","aberdeen!Cyclops"); 
Statement st= con.createStatement(); 
System.out.println(request.getParameter("login"));
if(request.getParameter("login")!=null){
	
	// check username or password containing illegal characters.
	String regEx = "[^0-9a-zA-Z]";
	Pattern pattern = Pattern.compile(regEx);
	Matcher userMatcher = pattern.matcher(user);
	Matcher pwdMatcher = pattern.matcher(pwd);
	if(userMatcher.find() || pwdMatcher.find()){
		System.out.println("ininininininin");
		out.print("<script>alert('illegal characters!');window.location='Login.jsp';</script>");
	}
	else{
		// check the length of username and password. If it is more than 15 words, then invalid
		System.out.println(user.length() +"    " + pwd.length());
		/*if(user.length() > 15){
			out.print("<script>alert('Length of Username cannot be more than 15 words!');window.location='Login.jsp';</script>");
		}
		else if(pwd.length() > 15){
			out.print("<script>alert('Length of password cannot be more than 15 words!');window.location='Login.jsp';</script>");
		}*/
		//else{
			// User storage procedure to do SQL query.
			String query = "CALL verifyUser('" + user + "','" + pwd +"');";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				// If the value of verification == 1, then user can successfully login his account
				System.out.println("verfication: " + rs.getString("verfication"));
				if(rs.getString("verfication").equals("1")){
					response.sendRedirect("admin.jsp");
				}
				else
					out.print("<script>alert('Wrong password!');window.location='Login.jsp';</script>");
				
			}
			

		rs.close();
		//}
	}
	
	st.close();
	con.close();
}

	


%>