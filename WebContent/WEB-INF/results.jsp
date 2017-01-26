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
<th>contact</th>
<th>name</th>
<th>net marks</th>

<th>ryt ans</th>
<th>wrng ans</th>
<th>not ans</th>
</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();%>
<% 
String sql ="SELECT * FROM Result ";
resultSet = statement.executeQuery(sql); 
while(resultSet.next()){
%>



<tr>
<td><%=resultSet.getString("rollno") %></td>
<td><%=resultSet.getString("contact") %></td>
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("netMarks") %></td>
<td><%=resultSet.getString("rgtAns") %></td>
<td><%=resultSet.getString("wngAns") %></td>
<td><%=resultSet.getString("notAns") %></td>
</tr>



<% 
}
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>