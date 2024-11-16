// prendo il pulsante di download
var downloadButton = document.getElementById('downloadButton');

// Percorso dell'immagine nella cartella img
var imagePath = 'img/lineChart.png';

// Aggiungo l'evento di click al pulsante di download
downloadButton.addEventListener('click', function () {
    // Controllo se l'immagine esiste
    var http = new XMLHttpRequest();
    http.open('HEAD', imagePath, false);
    http.send();

    if (http.status !== 404)
        downloadButton.href = imagePath;
    else
        alert("L'immagine non è stata ancora generata! Si prega di attendere qualche secondo e riprovare!");
});
