<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - Categories</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/categories.css">

<script>
        function showDetails(name) {
        	window.location.href = '/categories/' + name;
        }
</script>
</head>
<body>
	<div class="search-container">
		<form action="${pageContext.request.contextPath}/categories/search"
			method="get">
			<input type="text" name="name" placeholder="Search for a category..."
				value="${param.name}" />
		</form>
	</div>

	<ul class="category-list">
		<c:forEach var="category" items="${categories}">
			<li
				onclick="showDetails('${category.name}')">
				${category.name}</li>
		</c:forEach>
	</ul>
</body>
</html>
