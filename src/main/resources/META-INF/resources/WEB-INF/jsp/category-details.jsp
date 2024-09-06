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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/category-details.css">

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
