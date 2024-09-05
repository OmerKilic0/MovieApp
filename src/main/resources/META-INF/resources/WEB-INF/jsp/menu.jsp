<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - Menu</title>
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

.scroll-container {
	position: relative;
	width: 80%;
	max-width: 800px;
	overflow: hidden;
	margin: 10px auto;
	padding: 0 40px;
	display: flex;
	align-items: center;
}

.scroll-content {
	display: flex;
	overflow-x: auto;
	overflow-y: hidden;
	scroll-behavior: smooth;
	gap: 5px;
	padding: 5px 0;
	scrollbar-width: thin;
	scrollbar-color: #fb8500 #333;
}

.scroll-content::-webkit-scrollbar {
	height: 8px;
}

.scroll-content::-webkit-scrollbar-thumb {
	background-color: #fb8500;
	border-radius: 4px;
}

.scroll-content::-webkit-scrollbar-thumb:hover {
	background-color: #e07a5f;
}

.scroll-content::-webkit-scrollbar-track {
	background: #333;
	border-radius: 4px;
}

.film-card, .actor-card {
	flex: 0 0 auto;
	width: 150px;
	text-align: center;
	border-radius: 8px;
	background: #111;
	margin: 0 5px;
	cursor: pointer;
	transition: transform 0.3s ease;
}

.film-card img, .actor-card img {
	width: 100%;
	height: auto;
	border-radius: 8px;
}

.film-card h3, .actor-card h3 {
	margin: 5px 0;
	font-size: 14px;
	color: #b8c0ff;
}

.film-card:hover, .actor-card:hover {
	transform: scale(1.05);
}

.arrow {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	font-size: 20px;
	color: #fb8500;
	cursor: pointer;
	user-select: none;
	z-index: 1;
}

.arrow.left {
	left: 10px;
}

.arrow.right {
	right: 10px;
}

.section-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 10px auto 5px;
	max-width: 800px;
	padding: 0;
}

.section-header h2 {
	margin: 0;
	color: #588157;
}

.view-all {
	font-size: 16px;
	color: #ffb703;
	cursor: pointer;
}

.view-all:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="section-header">
		<h2>Films</h2>
		<span class="view-all"
			onclick="window.location.href='${pageContext.request.contextPath}/films'">View
			All</span>
	</div>
	<div class="scroll-container">
		<div class="arrow left" onclick="scrollContainer('films', -150)">&larr;</div>
		<div class="scroll-content" id="films">
			<c:forEach var="film" items="${films}" end="9">
				<div class="film-card"
					onclick="window.location.href='${pageContext.request.contextPath}/films/${film.title}'">
					<img
						src="${pageContext.request.contextPath}/images/films/poster${film.id}.jpg"
						alt="${film.title} Poster">
					<h3>${film.title}</h3>
				</div>
			</c:forEach>
		</div>
		<div class="arrow right" onclick="scrollContainer('films', 150)">&rarr;</div>
	</div>

	<div class="section-header">
		<h2>Actors</h2>
		<span class="view-all"
			onclick="window.location.href='${pageContext.request.contextPath}/actors'">View
			All</span>
	</div>
	<div class="scroll-container">
		<div class="arrow left" onclick="scrollContainer('actors', -150)">&larr;</div>
		<div class="scroll-content" id="actors">
			<c:forEach var="actor" items="${actors}" end="9">
				<div class="actor-card"
					onclick="window.location.href='${pageContext.request.contextPath}/actors/${actor.id}'">
					<img
						src="${pageContext.request.contextPath}/images/actors/actor${actor.id}.jpg"
						alt="${actor.firstName} ${actor.lastName}">
					<h3>${actor.firstName} ${actor.lastName}</h3>
				</div>
			</c:forEach>
		</div>
		<div class="arrow right" onclick="scrollContainer('actors', 150)">&rarr;</div>
	</div>

	<script>
		function scrollContainer(id, offset) {
			const container = document.getElementById(id);
			container.scrollBy({
				left : offset,
				behavior : 'smooth'
			});
		}
	</script>
</body>
</html>
