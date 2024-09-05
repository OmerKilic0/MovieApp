<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - ${actor.firstName} ${actor.lastName}</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
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
	margin-right: 8px;
}

.nav-bar a:hover {
	text-decoration: underline;
}

.container {
	display: flex;
	flex-direction: row;
}

.sidebar {
	background-color: #000;
	padding: 20px;
	width: 300px;
	color: #fff;
}

.sidebar h2, h3 {
	color: #588157;
    font-size: 20px;
    margin-bottom: 15px;
    border-bottom: 2px solid #003566;
    padding-bottom: 10px;
}

.left-sidebar {
	border-right: 2px solid #003566;
}

.right-sidebar {
	border-left: 2px solid #003566;
}

.right-sidebar ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

.right-sidebar ul li {
    background-color: #111;
    border: 1px solid #003566;
    border-radius: 5px;
    margin-bottom: 10px;
    padding: 10px;
    color: #fdf0d5;
    transition: background-color 0.3s, transform 0.3s;
}

.right-sidebar ul li:hover {
	cursor: pointer;
    background-color: #003566;
    transform: scale(1.05);
}

.right-sidebar ul li span {
    font-weight: bold;
    color: #ffb703;
}

.main-content {
	flex: 1;
	padding: 20px;
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
	<div class="container">
		<div class="sidebar left-sidebar">
			<h2>About Us</h2>
			<p>ADD CONTEXT</p>
			<h3>Contact Us</h3>
			<p>Email: info@movimo.com</p>
			<p>Phone: +123-456-7890</p>
		</div>
		<div class="main-content">
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
		</div>
		<div class="sidebar right-sidebar">
			<h2>Latest News</h2>
			<p>ADD CONTEXT</p>
			<h3>Popular Films</h3>
			<ul>
				<c:forEach var="film" items="${topSoldFilms}">
					<li><span onclick="window.location.href='/films/${film.title}'">${film.title}</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
