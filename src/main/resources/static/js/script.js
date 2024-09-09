function purchaseFilm(customerId, filmId, amount) {
	$.ajax({
		type: 'POST',
		url: '/api/purchase/buy',
		data: {
			customerId: customerId,
			filmId: filmId,
			amount: amount
		},
		success: function(response) {
			alert(response);
			location.reload();
		},
		error: function(xhr) {
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
	var watchedFilmIds = watchedFilmIdsFromServer;
	var inWatchlistFilmIds = inWatchlistFilmIdsFromServer;
	var filmId = filmIdFromServer;

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

function toggleLike(commentId) {
    fetch('/toggleLike?commentId=' + commentId, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(() => {
        var likeButton = document.getElementById('like-button-' + commentId);
        var dislikeButton = document.getElementById('dislike-button-' + commentId);
        var likeCountElement = document.getElementById('like-count-' + commentId);
        var dislikeCountElement = document.getElementById('dislike-count-' + commentId);
        
        var currentLikeCount = parseInt(likeCountElement.textContent);
        var currentDislikeCount = parseInt(dislikeCountElement.textContent);
        
        if (likeButton.classList.contains('active')) {
            likeCountElement.textContent = currentLikeCount - 1;
            likeButton.classList.remove('active');
        } else {
            if (dislikeButton.classList.contains('active')) {
                dislikeCountElement.textContent = currentDislikeCount - 1;
                dislikeButton.classList.remove('active');
            }
            likeCountElement.textContent = currentLikeCount + 1;
            likeButton.classList.add('active');
        }
    });
}

function toggleDislike(commentId) {
    fetch('/toggleDislike?commentId=' + commentId, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(() => {
        var likeButton = document.getElementById('like-button-' + commentId);
        var dislikeButton = document.getElementById('dislike-button-' + commentId);
        var likeCountElement = document.getElementById('like-count-' + commentId);
        var dislikeCountElement = document.getElementById('dislike-count-' + commentId);
        
        var currentLikeCount = parseInt(likeCountElement.textContent);
        var currentDislikeCount = parseInt(dislikeCountElement.textContent);
        
        if (dislikeButton.classList.contains('active')) {
            dislikeCountElement.textContent = currentDislikeCount - 1;
            dislikeButton.classList.remove('active');
        } else {
            if (likeButton.classList.contains('active')) {
                likeCountElement.textContent = currentLikeCount - 1;
                likeButton.classList.remove('active');
            }
            dislikeCountElement.textContent = currentDislikeCount + 1;
            dislikeButton.classList.add('active');
        }
    });
}