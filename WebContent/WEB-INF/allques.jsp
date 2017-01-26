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
<th>ques id</th>
<th>ques</th>
<th>option a</th>
<th>option b</th>
<th>option c</th>
<th>option d</th>
<th>ans</th>
</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();%>
<% 
String sql ="SELECT * FROM Questions ";
resultSet = statement.executeQuery(sql); 
while(resultSet.next()){
%>



<tr>
<td><%=resultSet.getString("questionId") %></td>
<td><%=resultSet.getString("question") %></td>
<td><%=resultSet.getString("optionA") %></td>
<td><%=resultSet.getString("optionB") %></td>
<td><%=resultSet.getString("optionC") %></td>
<td><%=resultSet.getString("optionD") %></td>
<td><%=resultSet.getString("answer") %></td>
</tr>



<% 
}
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>