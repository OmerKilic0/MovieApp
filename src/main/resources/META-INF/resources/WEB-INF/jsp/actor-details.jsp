<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - ${actor.firstName} ${actor.lastName}</title>
<link rel="stylesheet" 
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/actor-details.css">
	
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
