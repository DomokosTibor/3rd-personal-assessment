<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login Page</title>
	<link rel="shortcut icon" type="image/png" href="images/site_logo.png">
	<link rel="shortcut icon" type="image/png" href="images/site_logo.png">
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
	<header>
		<form method="post" action="login">
			<input type="text" name="id" placeholder=" ID number" required>
			<select name="role">
				<option value="Supplier">Supplier</option>
				<option value="Shipper">Shipper</option>
			</select>
			<input type="submit" value="Login">
		</form>
	</header>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
</body>

</html>
