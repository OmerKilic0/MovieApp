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

.film-attribute {
	color: #ffb703;
}

.film-details {
	position: relative;
	margin: 20px auto;
	max-width: 600px;
	padding: 15px;
	background-color: #000;
	border-radius: 8px;
	border: 2px solid #003566;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
	align-items: flex-start;
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
	margin-right: 70px;
}

.film-media {
	display: flex;
	justify-content: center;
	margin-right: 10px;
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

.icon-buttons {
	display: flex;
	justify-content: center;
	margin-top: 10px;
}

.icon-buttons form {
	display: inline;
	margin: 0 5px;
}

.icon-buttons button {
	background: none;
	border: none;
	color: #ffb703;
	font-size: 24px;
	cursor: pointer;
	transition: color 0.3s;
}

.icon-buttons button:hover {
	color: #e36414;
}

.purchase-button {
	position: absolute;
	background-color: #ffb703;
	color: #000;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	right: 20px;
	bottom: 20px;
}

.purchase-button:hover {
	background-color: #e36414;
	color: #fff;
}

.purchased-button {
	position: absolute;
	background-color: #808080;
	color: #ffffff;
	cursor: not-allowed;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 18px;
	transition: none;
	right: 20px;
	bottom: 20px;
}

.price-info {
	font-size: 20px;
	color: #fdf0d5;
	margin: 10px 0;
	display: flex;
	align-items: center;
}

.price-info i {
	margin-right: 10px;
	color: #e36414;
}

.categories {
	display: flex;
	flex-wrap: wrap;
	margin-top: 10px;
	list-style: none;
	padding: 0;
}

.categories li {
	margin: 0 10px;
	position: relative;
	font-size: 18px;
}

.categories li:not(:last-child)::after {
	content: '\2022';
	color: #bbb;
	font-size: 20px;
	margin-left: 16px;
}

.categories a {
	text-decoration: underline;
	color: #fb8500;
	cursor: pointer;
	font-size: 20px;
}

.categories a:hover {
	text-decoration: none;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function purchaseFilm(customerId, filmId, amount) {
		$.ajax({
			type : 'POST',
			url : '/api/purchase/buy',
			data : {
				customerId : customerId,
				filmId : filmId,
				amount : amount
			},
			success : function(response) {
				alert(response);
				location.reload();
			},
			error : function(xhr) {
				alert("Purchase failed: " + xhr.responseText);
			}
		});
	}
	
	function handleAction(action, filmId) {
		var form = document.getElementById(action + '-' + filmId);
		if (form) {
			form.submit();
		}
	}

	$(document).ready(function() {
	    var watchedFilmIds = ${watched};
	    var inWatchlistFilmIds = ${inWatchlist};
	    var filmId = ${film.id};

	    if (watchedFilmIds.includes(filmId)) {
	        $('#add-to-watched').hide();
	        $('#remove-from-watched').show();
	    } else {
	        $('#add-to-watched').show();
	        $('#remove-from-watched').hide();
	    }

	    if (inWatchlistFilmIds.includes(filmId)) {
	        $('#add-to-watchlist').hide();
	        $('#remove-from-watchlist').show();
	    } else {
	        $('#add-to-watchlist').show();
	        $('#remove-from-watchlist').hide();
	    }
	});
	
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
