<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "mcq_portal";
String userId = "root";
String password = "sar115";
%>

<%
try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Retrieve data from database </strong></font></h2>
<table border="2cm">
<tr>
<th>roll no</th>
<th>name</th>
<th>pass</th>
<th>attempt</th>
<th>branch</th>
<th>college</th>
<th>contact</th>
<th>email</th>
</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();%>
<% 
String sql ="SELECT * FROM Registration ";
resultSet = statement.executeQuery(sql); 
while(resultSet.next()){
%>



<tr>
<td><%=resultSet.getString("rollno") %></td>
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("pass") %></td>
<td><%=resultSet.getString("attempt") %></td>
<td><%=resultSet.getString("branch") %></td>
<td><%=resultSet.getString("college") %></td>
<td><%=resultSet.getString("contact") %></td>
<td><%=resultSet.getString("email") %></td>
</tr>



<% 
}
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>