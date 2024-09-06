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
