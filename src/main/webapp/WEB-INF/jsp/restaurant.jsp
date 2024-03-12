<%@ page import="org.lozza.business.entry.user.Client" %>
<%@ page import="org.lozza.business.entry.Restaurant" %>
<%@ page import="org.lozza.business.services.UtilsService" %>
<%@ page import="org.lozza.business.services.ReviewService" %>
<%@ page import="org.lozza.business.services.TableService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.lozza.business.entry.Table" %>
<%@ page import="org.lozza.business.entry.Review" %>
<%
  Client client = (Client) request.getSession().getAttribute("user");
  Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
  List<Table> tables = TableService.getAllTablesByRestaurant(restaurant);
  List<Review> reviews = ReviewService.getAllReviewsByRestaurant(restaurant);
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Restaurant</title>
  </head>
  <body>
    <p id="username">username: <%=client.username()%> <a href="${pageContext.request.contextPath}/">Home</a> <a href="${pageContext.request.contextPath}/logout" style="color: red">Log Out</a></p>
    <br>
    <h2>Restaurant Info:</h2>
    <ul>
      <li id="id">Id: <%=restaurant.id()%></li>
      <li id="name">Name: <%=restaurant.name()%></li>
      <li id="type">Type: <%=restaurant.type()%></li>
      <li id="time">Time: <%=UtilsService.convertTime(restaurant.startTime())%> - <%=UtilsService.convertTime(restaurant.endTime())%></li>
      <li id="rate">Scores:</li>
      <li>
        <ul>
          <li>Food: <%=ReviewService.getAverageFoodRate(restaurant)%></li>
          <li>Service: <%=ReviewService.getAverageServiceRate(restaurant)%></li>
          <li>Ambiance: <%=ReviewService.getAverageAmbianceRate(restaurant)%></li>
          <li>Overall: <%=ReviewService.getAverageOverallRate(restaurant)%></li>
        </ul>
      </li>
      <li id="address">Address: <%=restaurant.address().street()%>, <%=restaurant.address().city()%>, <%=restaurant.address().country()%></li>
      <li id="description">Description: <%=restaurant.description()%></li>
    </ul>


    <table style="border: 1px">
      <tr>
          <td style="padding: 10px">
              <label>Reserve Table:</label>
              <form action="${pageContext.request.contextPath}/reservations" method="post">
                <input type="hidden" name="client_username" value="<%=client.username()%>" >
                <input type="hidden" name="restaurant_id" value="<%=restaurant.id()%>" >
                <label for="table_number">Table:</label>
                <select id="table_number" name="table_number">
                  <%
                    for (Table table: tables) {
                  %>
                  <option value="<%=table.tableNumber()%>"><%=table.tableNumber()%></option>
                  <%
                    }
                  %>
                </select>
                <label for="date_time">Date & Time:</label>
                <input type="datetime-local" id="date_time" name="date_time">
                <br>
                <button type="submit" name="action" value="reserve_table">Reserve</button>
              </form>
          </td>
      </tr>
  </table>

    <table style="border: 1px">
      <tr>
          <td style="padding: 10px">
              <label>Feedback:</label>
              <form action="${pageContext.request.contextPath}/reviews" method="post">
                <input type="hidden" name="client_username" value="<%=client.username()%>">
                <input type="hidden" name="restaurant_name" value="<%=restaurant.name()%>">
                <label for="food_rate">Food Rate:</label>
                <input type="number" id="food_rate" name="food_rate" step="0.1" min="0" max="5">
                <label for="service_rate">Service Rate:</label>
                <input type="number" id="service_rate" name="service_rate" step="0.1" min="0" max="5">
                <label for="ambiance_rate">Ambiance Rate:</label>
                <input type="number" id="ambiance_rate" name="ambiance_rate" step="0.1" min="0" max="5">
                <label for="overall_rate">Overall Rate:</label>
                <input type="number" id="overall_rate" name="overall_rate" step="0.1" min="0" max="5">
                <br>
                <label for="comment">Comment:</label>
                <textarea name="comment"  id="comment" cols="30" rows="5" placeholder="Enter your comment"></textarea>
                <br>
                <button type="submit" name="action" value="feedback">Submit</button>
              </form>
          </td>
      </tr>
  </table>

    


    <br>
    
    <br/>
    <table style="width: 100%; text-align: center; border: 1px">
      <caption><h2>Feedbacks</h2></caption>
      <tr>
        <th>Username</th>
        <th>Comment</th>
        <th>Date</th>
        <th>Food Rate</th>
        <th>Service Rate</th>
        <th>Ambiance Rate</th>
        <th>Overall Rate</th>
      </tr>
      <%
        for (Review review : reviews) {
      %>
      <tr>
        <td><%=review.client().username()%></td>
        <td><%=review.comment()%></td>
        <td><%=UtilsService.convertDateTime(review.dateTime())%></td>
        <td><%=review.foodRate()%></td>
        <td><%=review.serviceRate()%></td>
        <td><%=review.ambianceRate()%></td>
        <td><%=review.overallRate()%></td>
      </tr>
      <%
        }
      %>
    </table>
    <br><br>
    
  </body>
</html>
