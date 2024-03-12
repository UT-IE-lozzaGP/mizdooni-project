<%@ page import="org.lozza.business.entry.user.Client" %>
<%
    Client client = (Client) request.getSession().getAttribute("user");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client Home</title>
</head>
<body>

    <h1>Welcome <%=client.username()%>> <a href="${pageContext.request.contextPath}/logout" style="color: red">Log Out</a></h1>
    

    <ul style="list-style:square">
        <li>
            <a href="${pageContext.request.contextPath}/restaurants">Restaurants</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/reservations">Reservations</a>
        </li>
    </ul>
    

</body></html>