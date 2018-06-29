<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Add Product</title>
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
        <h1>Add Product</h1>
        <form action="AddProductsServlet" method="post">
            <input type="text" name="supplier_id" value="${user_id}" readonly>
            <table class="stripe">
                <tr>
                    <td><b>Product ID:</b></td>
                    <td><input type="text" name="product_id" value="" required></td>
                </tr>
                <tr>
                    <td><b>Product Name:</b></td>
                    <td><input type="text" name="product_name" value=""></td>
                </tr>
                <tr>
                    <td><b>Category:</b></td>
                    <td>
                    <select name="category_id">
                        <option value="1">Beverages</option>
                        <option value="2">Condiments</option>
                        <option value="3">Confections</option>
                        <option value="4">Dairy Products</option>
                        <option value="5">Grains/Cereals</option>
                        <option value="6">Meat/Poultry</option>
                        <option value="7">Produce</option>
                        <option value="8">Seafood</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td><b>Quantity per Unit:</b></td>
                    <td><input type="text" name="quantity_per_unit" value=""></td>
                </tr>
                <tr>
                    <td><b>Unit price:</b></td>
                    <td><input type="text" name="unit_price" value=""></td>
                </tr>
                <tr>
                    <td><b>Units in stock:</b></td>
                    <td><input type="text" name="units_in_stock" value=""></td>
                </tr>
                <tr>
                    <td><b>Units on order:</b></td>
                    <td><input type="text" name="units_on_order" value=""></td>
                </tr>
                <tr>
                    <td><b>Reorder Level:</b></td>
                    <td><input type="text" name="reorder_level" value=""></td>
                </tr>
                <tr>
                    <td><b>Discontinued:</b></td>
                    <td><input type="text" name="discontinued" value="" required></td>
                </tr>
            </table>
            <input type="submit" value="Add product">
        </form>
        <button onclick="window.location.href='profile'">Back</button>
	</div>
</body>

</html>
