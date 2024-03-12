<%
    String message = request.getParameter("message");
    if (message == null) message = "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>400 Error</title>
</head>
<body style="text-align:center">
    <h1>400<br><%= message %></h1>
</body>
</html>