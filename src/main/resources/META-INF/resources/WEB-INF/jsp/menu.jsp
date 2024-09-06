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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menu.css">

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
