$(document).ready(function () {
    var formLogin = $('.login-box form');

    formLogin.submit(function (event) {
        event.preventDefault();

        var username = $('#usernameLogin').val();
        var password = $('#passwordLogin').val();

        $.ajax({
            url: '/checkUser?username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password),
            method: 'GET',
            success: function (data) {
                window.location.href = data;
            },
            error: function () {
                alert('Si è verificato un errore nella richiesta!');
            }
        });
    });
});
