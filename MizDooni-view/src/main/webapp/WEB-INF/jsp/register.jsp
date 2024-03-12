<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <style>
    form {
      border-style: double;
      padding: 20px 0;
    }
    form input {
      margin: 10px;
    }
    form button {
      background-color: green;
      color: white;
      width: 25%;
      padding: 2px;
      font-size: 20px;
    }
    form button:hover {
      transform: scale(105%);
      cursor: pointer;
    }

  </style>
</head>

<body style="text-align:center">

  <h1>Register Your Account</h1>
  <form method="post" action="${pageContext.request.contextPath}/register">
    <fieldset>
        <p>
            <span>Role:</span>
            <input id="role.client" name="role" type="radio" value="client"/> <label for="role.client">Client</label>
            <input id="role.manager" name="role" type="radio" value="manager"/> <label for="role.manager">Manager</label>
        </p>
        <p>
            <label for="username">Username:</label>
            <input id="username" name="username" type="text" />
        </p>

        <p>
            <label for="email">Email:</label>
            <input id="email" name="email" type="email" />
        </p>
        <p>
            <label for="password">Password:</label>
            <input id="password" name="password" type="password" />
        </p>
        <div>
            <span>Address</span>
            <p>
                <label for="address.country">Country:</label>
                <input id="address.country" name="address.country" type="text" />
            </p>
            <p>
                <label for="address.city">City:</label>
                <input id="address.city" name="address.city" type="text" />
            </p>
            <p>
                <label for="address.street">Street:</label>
                <input id="address.street" name="address.street" type="text" />
            </p>
        </div>
        <button type="submit">Login!</button>
    </fieldset>
  </form>
</body>
</html>