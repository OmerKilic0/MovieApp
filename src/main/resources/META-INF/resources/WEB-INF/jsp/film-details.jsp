<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - ${film.title}</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #000;
	color: #fff;
	font-size: 17px;
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
	display: inline-flex;
	align-items: center;
}

.nav-bar i {
	margin-right: 8px; /* Space between icon and text */
}

.nav-bar a:hover {
	text-decoration: underline;
}

.film-attribute {
    color: #ffb703;
}

.film-details {
    margin: 20px auto;
    max-width: 600px;
    padding: 15px;
    background-color: #000;
    border-radius: 8px;
    border: 2px solid #003566;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.film-details h1 {
	margin-top: 0;
	color: #588157;
}

.film-details h2 {
	margin-top: 0;
	color: #b8c0ff;
}

.film-details img {
	max-width: 50%;
	height: auto;
	border-radius: 5px;
	margin-bottom: 20px;
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.back-button {
	position: relative;
	top: 20px;
	color: #e36414;
	border: none;
	border-radius: 5px;
	padding: 5px 10px;
	font-size: 16px;
	cursor: pointer;
	text-decoration: none;
	display: inline-flex;
	align-items: center;
	transition: transform 0.3s ease;
}

.back-button i {
	margin-right: 8px;
}

.back-button:hover {
	transform: scale(1.2);
}
</style>
</head>
<body>
	<div class="film-details">
		<a href="javascript:history.back()" class="back-button"> <i
			class="fa-solid fa-circle-chevron-left fa-lg"></i>
		</a> 
		<img
			src="${pageContext.request.contextPath}/images/films/poster${film.id}.jpg"
			alt="${film.title} Poster">
		<h1>${film.title}</h1>
		<p>${film.description}</p>
		<p><span class="film-attribute">Language: </span>${film.language.name}</p>
		<p><span class="film-attribute">Length: </span>${film.length}</p>
		<h2>Actors:</h2>
		<ul>
			<c:forEach var="actor" items="${film.actors}">
				<li>${actor.firstName} ${actor.lastName}</li>
			</c:forEach>
		</ul>
		<h2>Categories:</h2>
		<ul>
			<c:forEach var="category" items="${film.categories}">
				<li>${category.name}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
