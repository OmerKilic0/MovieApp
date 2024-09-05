<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - ${actor.firstName} ${actor.lastName}</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
}

.nav-bar a:hover {
	text-decoration: underline;
}

.actor-details {
    margin: 20px auto;
    max-width: 600px;
    padding: 15px;
    background-color: #000;
    border-radius: 8px;
    border: 2px solid #003566;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.actor-details h1 {
	margin-top: 0;
	color: #588157;
}

.actor-details h2 {
	margin-top: 0;
	color: #b8c0ff;
}

.actor-details img {
	width: 50%;
	height: auto;
	border-radius: 5px;
	margin-bottom: 20px;
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.actor-details ul {
    list-style-type: none;
    padding: 0;
    text-align: left;
}

.actor-details ul li {
    font-size: 18px;
    margin-bottom: 8px;
    cursor: pointer;
}

.actor-details ul li a {
    color: #ffb703;
    text-decoration: underline;
}

.actor-details ul li a:hover {
    text-decoration: none;
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
	<div class="actor-details">
		<a href="javascript:history.back()" class="back-button">
			<i class="fa-solid fa-circle-chevron-left fa-lg"></i>
		</a>
		<img
			src="${pageContext.request.contextPath}/images/actors/actor${actor.id}.jpg"
			alt="${actor.firstName} ${actor.lastName}">
		<h1>${actor.firstName} ${actor.lastName}</h1>
		<h2>Films:</h2>
		<ul>
			<c:forEach var="film" items="${actor.films}">
				<li><a href="${pageContext.request.contextPath}/films/${film.title}">${film.title}</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
