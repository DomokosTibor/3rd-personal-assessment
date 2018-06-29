<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>All Products</title>
	<link rel="shortcut icon" type="image/png" href="../images/site_logo.png">
	<link rel="shortcut icon" type="image/png" href="../images/site_logo.png">
	<link rel="stylesheet" type="text/css" href="../css/protected.css">
</head>

<body>
	<header>
        <form method="post" action="logout">
            <input type="submit" value="Logout">
        </form>
	</header>
	<div class="content">
	    <h1>All Products</h1>
        <form action="AllProductsServlet" method="post">
            <input type="text" value="" name="filter" placeholder=" Product Name">
            <input type="submit" value="Search">
        </form>
        <table class="stripe">
        <tr>
            <th><b>Product ID</b></th>
            <th><b>Product Name</b></th>
            <th><b>Unit Price</b></th>
            <th><b>Unit In Stock</b></th>
            <th><b>Category Name</b></th>
            <th><b>Supplier Name</b></th>
        </tr>
        <c:forEach items="${product}" var='product'>
        <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.unitPrice}</td>
            <td>${product.unitsInStock}</td>
            <td>${product.categoryName}</td>
            <td>${product.supplierName}</td>
        </tr>
        </c:forEach>
        </table>
        <button onclick="window.location.href='profile'">Back</button>
	</div>
</body>

</html>
