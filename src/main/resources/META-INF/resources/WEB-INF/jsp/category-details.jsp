<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - ${category.name}</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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

.category-header {
	text-align: center;
	margin-top: 20px;
	font-size: 24px;
	color: #588157;
}

.film-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	gap: 20px;
	margin-top: 20px;
}

.film-card {
	background-color: #333;
	border-radius: 5px;
	overflow: hidden;
	text-align: center;
	width: 200px;
	cursor: pointer;
	transition: transform 0.3s;
}

.film-card img {
	width: 100%;
	height: 300px;
	object-fit: cover;
}

.film-card:hover {
	transform: scale(1.05);
}

.film-card h3 {
	margin: 10px;
}
</style>
</head>
<body>

	<div class="category-header">
		<h2>${category.name}</h2>
	</div>

	<div class="film-container">
		<c:forEach var="film" items="${category.films}">
			<div class="film-card"
				onclick="window.location.href='${pageContext.request.contextPath}/films/${film.title}'">
				<img src="${pageContext.request.contextPath}/images/films/poster${film.id}.jpg" alt="${film.title} Poster">
				<h3>${film.title}</h3>
			</div>
		</c:forEach>
	</div>

</body>
</html>
