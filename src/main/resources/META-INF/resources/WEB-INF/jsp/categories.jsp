<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - Categories</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #000;
	color: #fff;
}

.app-name {
	padding: 20px;
	font-size: 24px;
	font-weight: bold;
	text-align: left;
	padding-left: 20px;
}

.nav-bar {
	background-color: #222;
	padding: 10px 0;
	text-align: center;
}

.nav-bar a {
	color: #fff;
	text-decoration: none;
	margin: 0 15px;
	font-size: 18px;
}

.nav-bar a:hover {
	text-decoration: underline;
}

.search-container {
	text-align: center;
	margin-top: 20px;
}

.search-container input[type="text"] {
	padding: 10px;
	width: 60%;
	border-radius: 5px;
	border: none;
	font-size: 16px;
}

.category-list {
	list-style-type: none;
	padding: 0;
	margin: 20px auto;
	max-width: 600px;
}

.category-list li {
	background-color: #333;
	margin: 10px 0;
	padding: 15px;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s;
	font-size: 18px;
}

.category-list li:hover {
	background-color: #444;
}

</style>
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
