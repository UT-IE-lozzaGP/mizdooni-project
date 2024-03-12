<%@ page import="org.lozza.business.entry.user.Manager" %>
<%@ page import="org.lozza.business.services.RestaurantService" %>
<%@ page import="org.lozza.business.entry.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lozza.business.services.UtilsService" %>
<%@ page import="org.lozza.business.entry.Table" %>
<%@ page import="org.lozza.business.services.TableService" %>
<%
    Manager manager = (Manager) request.getSession().getAttribute("user");
%>

<html lang="en"><head>
    <meta charset="UTF-8">
    <title>Manager Home</title>
</head>
<body>
    <h1>Welcome <%=manager.username()%>> <a href="${pageContext.request.contextPath}/logout" style="color: red">Log Out</a></h1>
    <h2>Your Restaurants Information:</h2>
    <%
        List<Restaurant> restaurants = RestaurantService.searchRestaurantByManager(manager);
        for(Restaurant restaurant : restaurants) {
    %>
    <ul>
        <li id="id">Name: <%=restaurant.id()%></li>
        <li id="name">Name: <%=restaurant.name()%></li>
        <li id="type">Type: <%=restaurant.type()%></li>
        <li id="time">Time: <%=UtilsService.convertTime(restaurant.startTime())%> - <%=UtilsService.convertTime(restaurant.endTime())%></li>
        <li id="description">Description: "<%=restaurant.description()%>"</li>
        <li id="address">Address: <%=restaurant.address().street()%>, <%=restaurant.address().city()%>, <%=restaurant.address().country()%></li>
        <li id="tables">Tables:</li>
        <li>
            <ul>
                <%
                    List<Table> tables = TableService.getAllTablesByRestaurant(restaurant);
                    for(Table table : tables) {
                %>
                <li>Table Number: <%=table.tableNumber()%></li>
                <%
                    }
                %>
            </ul>
        </li>

    </ul>

    <table style="border: 1px">
        <tr>
            <td style="padding: 10px">

                <h3>Add Table:</h3>
                <form method="post" action="${pageContext.request.contextPath}/tables">
                    <label for="table_number">Table Number:</label>
                    <input id="table_number" name="table_number" type="number" min="0"/>
                    <br>
                    <label for="seats_number">Seats Number:</label>
                    <input id="seats_number" name="seats_number" type="number" min="1"/>
                    <br>
                    <input type="hidden" name="restaurant_name" value="<%=restaurant.name()%>">
                    <button type="submit">Add</button>
                </form>

            </td>
        </tr>
    </table>
    <%
        }
    %>
    

</body></html>