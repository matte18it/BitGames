let signup = document.querySelector(".programmatore");
let login = document.querySelector(".giocatore");
let slider = document.querySelector(".slider");
let giocatoreForm = document.querySelector(".giocatore-box");
let programmatoreForm = document.querySelector(".programmatore-box");

signup.addEventListener("click", () => {
    slider.classList.add("moveslider");
    giocatoreForm.classList.add("hidden");
    programmatoreForm.classList.remove("hidden");
});

login.addEventListener("click", () => {
    slider.classList.remove("moveslider");
    giocatoreForm.classList.remove("hidden");
    programmatoreForm.classList.add("hidden");
});

$(document).ready(function () {
    var usernameInput = $('#usernameP');

    usernameInput.blur(function () {
        var username = usernameInput.val();

        // Verifica se lo username non è vuoto prima di effettuare la chiamata AJAX
        if (username.trim() !== '') {
            // Chiamata AJAX a checkUsername
            $.ajax({
                url: '/checkUsername?username=' + encodeURIComponent(username),
                method: 'GET',
                success: function (data) {
                    if (data === "false") {
                        usernameInput.val('');
                        alert("Username già in uso!");
                    }
                },
                error: function () {
                    alert('Si è verificato un errore nella richiesta!');
                }
            });
        }
    });
});

var formProgrammatore = $('.programmatore-box form');

formProgrammatore.submit(function (event) {
    event.preventDefault();

    var username = $('#usernameP').val();
    var password = $('#passwordP').val();
    var email = $('#emailP').val();
    var nome = $('#nomeP').val();
    var cognome = $('#cognomeP').val();
    var dataNascita = $('#dataNascitaP').val();
    var citta = $('#cittaP').val();

    // Passo i parametri utilizzando AJAX
    $.ajax({
        url: '/saveUser?username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password) + '&email=' + encodeURIComponent(email) + '&nome=' + encodeURIComponent(nome) + '&cognome=' + encodeURIComponent(cognome) + '&dataNascita=' + encodeURIComponent(dataNascita) + '&citta=' + encodeURIComponent(citta),
        method: 'GET',
        success: function (data) {
            window.location.href = data;
        },
        error: function () {
            alert('Si è verificato un errore nella richiesta!');
        }
    });
});



