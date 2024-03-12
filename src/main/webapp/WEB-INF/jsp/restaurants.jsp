<%@ page import="org.lozza.business.entry.user.Client" %>
<%@ page import="org.lozza.business.entry.Restaurant" %>
<%@ page import="org.lozza.business.services.UtilsService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lozza.business.services.RestaurantService" %>
<%@ page import="org.lozza.business.services.ReviewService" %>
<%@ page import="java.util.ArrayList" %>
<%
    Client client = (Client) request.getSession().getAttribute("user");
    List<Restaurant> restaurants;
    try {
        restaurants = ((List<?>) request.getSession().getAttribute("restaurants"))
                .stream()
                .map(o -> (Restaurant) o)
                .toList();
    } catch (ClassCastException e) {
        restaurants = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurants</title>
</head>
<body>
    <p id="username">username: <%=client.username()%> <a href="${pageContext.request.contextPath}/">Home</a> <a href="${pageContext.request.contextPath}/logout" style="color: red">Log Out</a></p>
    <br><br>
    <form action="${pageContext.request.contextPath}/restaurants" method="post">
        <label for="search">Search:</label>
        <input id="search" type="text" name="search" value="">
        <button type="submit" name="action" value="search_by_type">Search By Type</button>
        <button type="submit" name="action" value="search_by_name">Search By Name</button>
        <button type="submit" name="action" value="search_by_city">Search By City</button>
        <button type="submit" name="action" value="clear">Clear Search</button>
    </form>
    <br><br>
    <form action="${pageContext.request.contextPath}/restaurants" method="post">
        <label>Sort By:</label>
        <button type="submit" name="action" value="sort_by_rate">Overall Score</button>
    </form>
    <br><br>
    <table style="width:100%; text-align:center; border: 1px;">
        <tr>
            <th>Id</th>
            <th>Name</th> 
            <th>City</th>
            <th>Type</th>
            <th>Time</th>
            <th>Service Score</th>
            <th>Food Score</th>
            <th>Ambiance Score</th>
            <th>Overall Score</th>
        </tr>
        <%
            for (Restaurant restaurant : restaurants) {
        %>

        <tr>
            <td><%=restaurant.id()%></td>
            <td><a href="${pageContext.request.contextPath}/restaurants/<%=restaurant.id()%>"><%=restaurant.name()%></a></td>
            <td><%=restaurant.address().city()%></td>
            <td><%=restaurant.type()%></td>
            <td><%=UtilsService.convertTime(restaurant.startTime())%> - <%=UtilsService.convertTime(restaurant.endTime())%></td>
            <td><%=ReviewService.getAverageServiceRate(restaurant)%></td>
            <td><%=ReviewService.getAverageFoodRate(restaurant)%></td>
            <td><%=ReviewService.getAverageAmbianceRate(restaurant)%></td>
            <td><%=ReviewService.getAverageOverallRate(restaurant)%></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>