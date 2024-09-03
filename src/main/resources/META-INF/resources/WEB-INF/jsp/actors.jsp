<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - Actors</title>
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

.actor-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	gap: 20px;
	margin-top: 20px;
}

.actor-card {
	background-color: #111;
	border-radius: 5px;
	overflow: hidden;
	text-align: center;
	width: 200px;
	cursor: pointer;
	transition: transform 0.3s;
}

.actor-card img {
	width: 100%;
	height: 300px;
	object-fit: cover;
}

.actor-card:hover {
	transform: scale(1.05);
}

.actor-card h3 {
	margin: 10px;
}
</style>
</head>
<body>
	<div class="search-container">
		<form action="${pageContext.request.contextPath}/actors/search"
			method="get">
			<input type="text" name="name" placeholder="Search for an actor..."
				value="${param.name}" />
		</form>
	</div>

	<div class="actor-container">
		<c:forEach var="actor" items="${actors}">
			<div class="actor-card"
				onclick="window.location.href='${pageContext.request.contextPath}/actors/${actor.id}'">
				<img
					src="${pageContext.request.contextPath}/images/actors/actor${actor.id}.jpg"
					alt="${actor.firstName} ${actor.lastName}">
				<h3>${actor.firstName} ${actor.lastName}</h3>
			</div>
		</c:forEach>
	</div>
</body>
</html>
