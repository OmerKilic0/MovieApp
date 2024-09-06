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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/film-details.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<script>
        var watchedFilmIdsFromServer = ${watched};
        var inWatchlistFilmIdsFromServer = ${inWatchlist};
        var filmIdFromServer = ${film.id};
</script>

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
			<div class="film-details">
				<a href="javascript:history.back()" class="back-button"> <i
					class="fa-solid fa-circle-chevron-left fa-lg"></i>
				</a>
				<div class="film-media">
					<img
						src="${pageContext.request.contextPath}/images/films/poster${film.id}.jpg"
						alt="${film.title} Poster">
					<div class="icon-buttons">
						<form id="add-to-watched" action="/films/addToWatched"
							method="post">
							<input type="hidden" name="filmId" value="${film.id}">
							<button type="submit" title="Mark as Watched"
								onclick="handleAction('add-to-watched', ${film.id})">
								<i class="fa-solid fa-eye"></i>
							</button>
						</form>
						<form id="remove-from-watched" action="/films/removeFromWatched"
							method="post" style="display: none;">
							<input type="hidden" name="filmId" value="${film.id}">
							<button type="submit" title="Remove from watched"
								onclick="handleAction('remove-from-watched', ${film.id})">
								<i class="fa-solid fa-eye-slash"></i>
							</button>
						</form>
						<form id="add-to-watchlist" action="/films/addToWatchlist"
							method="post">
							<input type="hidden" name="filmId" value="${film.id}">
							<button type="submit" title="Add to Watchlist"
								onclick="handleAction('add-to-watchlist', ${film.id})">
								<i class="fa-regular fa-bookmark"></i>
							</button>
						</form>
						<form id="remove-from-watchlist"
							action="/films/removeFromWatchlist" method="post"
							style="display: none;">
							<input type="hidden" name="filmId" value="${film.id}">
							<button type="submit" title="Remove from Watchlist"
								onclick="handleAction('remove-from-watchlist', ${film.id})">
								<i class="fa-solid fa-bookmark"></i>
							</button>
						</form>
					</div>
				</div>
				<h1>${film.title}</h1>
				<ul class="categories">
					<c:forEach var="category" items="${film.categories}">
						<li><a
							onclick="window.location.href='/categories/${category.name}'">${category.name}</a></li>
					</c:forEach>
				</ul>
				<p>${film.description}</p>
				<p>
					<i class="bi bi-translate" style="color: #fdf0d5;"></i> <span
						class="film-attribute"> Language: </span>${film.language.name}
				</p>
				<p>
					<i class="fa-solid fa-clock" style="color: #fdf0d5;"></i> <span
						class="film-attribute"> Duration: </span>${film.length} minutes
				</p>
				<p>
					<i class="fa-solid fa-calendar-days" style="color: #fdf0d5;"></i> <span
						class="film-attribute"> Release Year: </span>${film.releaseYear}
				</p>
				<p>
					<i class="fa-solid fa-dollar-sign" style="color: #fdf0d5"></i> <span
						class="film-attribute"> Price: </span> \$${film.price}
				</p>
				<h2>Actors:</h2>
				<ul>
					<c:forEach var="actor" items="${film.actors}">
						<li>${actor.firstName} ${actor.lastName}</li>
					</c:forEach>
				</ul>

				<c:choose>
					<c:when test="${hasPurchased}">
						<span class="purchased-button">PURCHASED</span>
					</c:when>
					<c:otherwise>
						<button class="purchase-button"
							onclick="purchaseFilm(${customer.id}, ${film.id}, ${film.price})">PURCHASE</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="sidebar right-sidebar">
			<h2>Latest News</h2>
			<p>ADD CONTEXT</p>
			<h3>Popular Films</h3>
			<ul>
				<c:forEach var="film" items="${topSoldFilms}">
					<li><span
						onclick="window.location.href='/films/${film.title}'">${film.title}</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
