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

function toggleLikeComment(commentId) {
    fetch('/toggleCommentLike?commentId=' + commentId, {
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

function toggleDislikeComment(commentId) {
    fetch('/toggleCommentDislike?commentId=' + commentId, {
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

function toggleLikeReply(replyId) {
    fetch('/toggleReplyLike?replyId=' + replyId, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(() => {
        var likeButtonReply = document.getElementById('like-button-reply-' + replyId);
        var dislikeButtonReply = document.getElementById('dislike-button-reply-' + replyId);
        var likeCountElementReply = document.getElementById('like-count-reply-' + replyId);
        var dislikeCountElementReply = document.getElementById('dislike-count-reply-' + replyId);
        
        var currentLikeCountReply = parseInt(likeCountElementReply.textContent);
        var currentDislikeCountReply = parseInt(dislikeCountElementReply.textContent);
        
        if (likeButtonReply.classList.contains('active')) {
            likeCountElementReply.textContent = currentLikeCountReply - 1;
            likeButtonReply.classList.remove('active');
        } else {
            if (dislikeButtonReply.classList.contains('active')) {
                dislikeCountElementReply.textContent = currentDislikeCountReply - 1;
                dislikeButtonReply.classList.remove('active');
            }
            likeCountElementReply.textContent = currentLikeCountReply + 1;
            likeButtonReply.classList.add('active');
        }
    });
}

function toggleDislikeReply(replyId) {
    fetch('/toggleReplyDislike?replyId=' + replyId, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(() => {
        var likeButtonReply = document.getElementById('like-button-reply-' + replyId);
        var dislikeButtonReply = document.getElementById('dislike-button-reply-' + replyId);
        var likeCountElementReply = document.getElementById('like-count-reply-' + replyId);
        var dislikeCountElementReply = document.getElementById('dislike-count-reply-' + replyId);
        
        var currentLikeCountReply = parseInt(likeCountElementReply.textContent);
        var currentDislikeCountReply = parseInt(dislikeCountElementReply.textContent);
        
        if (dislikeButtonReply.classList.contains('active')) {
            dislikeCountElementReply.textContent = currentDislikeCountReply - 1;
            dislikeButtonReply.classList.remove('active');
        } else {
            if (likeButtonReply.classList.contains('active')) {
                likeCountElementReply.textContent = currentLikeCountReply - 1;
                likeButtonReply.classList.remove('active');
            }
            dislikeCountElementReply.textContent = currentDislikeCountReply + 1;
            dislikeButtonReply.classList.add('active');
        }
    });
}

function showReplyForm(commentId) {
    var replyForm = document.getElementById('reply-form-' + commentId);
    if (replyForm.style.display === 'none' || replyForm.style.display === '') {
        replyForm.style.display = 'block';
    } else {
        replyForm.style.display = 'none';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('.star-rating .fa-star');
    const ratingValue = document.getElementById('rating-value');
    const userRating = document.querySelector('.star-rating').getAttribute('data-user-rating');
    
    if (userRating) {
        for (let i = 0; i < userRating; i++) {
            stars[i].classList.add('selected');
        }
    }

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const rating = this.getAttribute('data-rating');
            
            ratingValue.value = rating;

            stars.forEach(s => {
                s.classList.remove('selected');
            });

            for (let i = 0; i < rating; i++) {
                stars[i].classList.add('selected');
            }
        });
    });
});