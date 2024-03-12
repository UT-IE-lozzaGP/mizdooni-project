<%@ page import="org.lozza.business.entry.user.Client" %>
<%@ page import="org.lozza.business.services.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lozza.business.entry.Reservation" %>
<%@ page import="org.lozza.business.services.ReservationService" %>
<%@ page import="org.lozza.business.entry.Restaurant" %>
<%@ page import="org.lozza.business.entry.Table" %>
<%@ page import="org.lozza.business.services.UtilsService" %>
<%
    Client client = (Client) request.getSession().getAttribute("user");
    List<Reservation> reservations = ReservationService.getAllReservationsByClient(client);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reservations</title>
</head>
<body>
    <p id="username">username: <%=client.username()%> <a href="${pageContext.request.contextPath}/">Home</a> <a href="${pageContext.request.contextPath}/logout" style="color: red">Log Out</a></p>
    <h1>Your Reservations:</h1>
    <br><br>
    <br><br>
    <table style="width:100%; text-align:center; border: 1px" >
        <tr>
            <th>Reservation Id</th> 
            <th>Restaurant Name</th>
            <th>Table Number</th> 
            <th>Date & Time</th>
            <th>Canceling</th>
        </tr>
        <%
            for (Reservation reservation : reservations) {
                Table table = reservation.table();
                Restaurant restaurant = table.restaurant();
        %>
        <tr>
            <td><%=reservation.reservationNumber()%></td>
            <td><a href="${pageContext.request.contextPath}/restaurants/<%=restaurant.id()%>"><%=restaurant.name()%></a></td>
            <td><%=table.tableNumber()%></td>
            <td><%=UtilsService.convertDateTime(reservation.dateTime())%></td>
            <td>
                <form action="${pageContext.request.contextPath}/reservations" method="post">
                    <input type="hidden" name="client_username" value="<%=client.username()%>" >
                    <input type="hidden" name="reservation_number" value="<%=reservation.reservationNumber()%>" >
                    <button type="submit" name="action" value="cancel_reservation">Cancel This</button>
                </form>
            </td> 
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>