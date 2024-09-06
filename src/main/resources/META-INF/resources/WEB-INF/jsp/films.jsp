<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimo - Films</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/category-details.css">

</head>
<body>
	<div class="search-container">
		<form action="${pageContext.request.contextPath}/films/search"
			method="get">
			<input type="text" name="name" placeholder="Search for films..."
				value="${param.name}" />
		</form>
	</div>
	<div class="film-container">
		<c:forEach var="film" items="${films}">
			<div class="film-card"
				onclick="window.location.href='${pageContext.request.contextPath}/films/${film.title}'">
				<img
					src="${pageContext.request.contextPath}/images/films/poster${film.id}.jpg"
					alt="${film.title} Poster">
				<h3>${film.title}</h3>
			</div>
		</c:forEach>
	</div>
</body>
</html>
