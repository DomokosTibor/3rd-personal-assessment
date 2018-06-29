<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Profile</title>
	<link rel="shortcut icon" type="image/png" href="../images/site_logo.png">
	<link rel="shortcut icon" type="image/png" href="../images/site_logo.png">
	<link rel="stylesheet" type="text/css" href="../css/protected.css">
	<link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
</head>

<body>
	<header>
        <form method="post" action="logout">
            <input type="submit" value="Logout">
        </form>
	</header>
	<c:if test="${user.userRole == 'Supplier'}"><div class="content supplier"></c:if>
	<c:if test="${user.userRole == 'Shipper'}"><div class="content shipper"></c:if>
	<c:if test="${user.userRole != 'Supplier' && user.userRole != 'Shipper'}"><div class="content"></c:if>
        <h1>User Profile</h1>
        <table>
            <tr>
                <td width="20px"><i class="fa fa-id-badge fa-1"></i></td>
                <td width="140px"><b>${user.userRole} ID:</b></td>
                <td><c:out value="${user.userId}"/></td>
            </tr>
            <tr>
                <td><i class="fa fa-building-o fa-1"></i></td>
                <td><b>Company name:</b></td>
                <td>${user.companyName}</td>
            </tr>
            <c:if test="${user.userRole == 'Supplier'}">
            <tr>
                <td><i class="fa fa-map-marker fa-1"></i></td>
                <td><b>Address:</b></td>
                <td>${user.address},
                    ${user.city},
                    ${user.region},
                    ${user.postalCode},
                    ${user.country}
                </td>
            </tr>
            </c:if>
            <tr>
                <td><i class="fa fa-phone fa-1"></i></td>
                <td><b>Phone:</b></td>
                <td>${user.phone}</td>
            </tr>
            <c:if test="${user.userRole == 'Supplier'}">
            <tr>
                <td><i class="fa fa-globe fa-1"></i></td>
                <td><b>Homepage:</b></td>
                <td>${user.homepage}</td>
            </tr>
            <tr>
                <td><i class="fa fa-user fa-1"></i></td>
                <td><b>Contact:</b></td>
                <td>${user.contactName} (${user.contactTitle})</td>
            </tr>
            </c:if>
        </table>

        <h2>Available options:</h2>
        <ul>
            <li><a href="AllProductsServlet">All Products</a></li>
            <c:if test="${user.userRole == 'Supplier'}">
                <li><a href="<c:url value="SupplierProductsServlet"><c:param name="user_id" value="${user.userId}"/></c:url>">Supplier products</a></li>
                <li>
                    <form action="ProductDetailsServlet" method="post">
                        <b>Product details by:</b>
                        <input type="hidden" name="user_id" value="${user.userId}" readonly>
                        <input type="text" name="product_id" value="" placeholder=" Product ID" size="8">
                        or
                        <input type="text" name="product_name" value="" placeholder=" Product Name">
                        <input type="submit" value="Check">
                        <c:if test="${not empty error}">
                            <p class="error">${error}</p>
                        </c:if>
                    </form>
                </li>
                <li><a href="<c:url value="AddProductsServlet"><c:param name="user_id" value="${user.userId}"/></c:url>">Add product</a></li>
            </c:if>
        </ul>
	</div>
</body>

</html>
