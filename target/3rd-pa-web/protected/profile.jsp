<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="../snippets/head.jsp">
    <jsp:param name="title" value="Profile"/>
</jsp:include>

<body>
<h1>Supplier Profile</h1>


<p>Supplier ID: <c:out value="${user.supplierId}"/></p>
<ul>
    <li><a href="<c:url value="#"><c:param name="id" value="${user.supplierId}"/></c:url>">Products</a></li>
    <li><a href="<c:url value="#"><c:param name="id" value="${user.supplierId}"/></c:url>">Supplier products</a></li>
    <li><a href="<c:url value="#"><c:param name="id" value="${user.supplierId}"/></c:url>">Product details</a></li>
    <li><a href="<c:url value="#"><c:param name="id" value="${user.supplierId}"/></c:url>">Add product</a></li>
</ul>


<jsp:include page="../snippets/logout.jsp"/>
</body>
</html>
