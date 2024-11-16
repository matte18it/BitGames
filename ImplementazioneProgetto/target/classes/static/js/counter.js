function getCounter(){
    //prendo il componente dal mio html
    var counterElement = document.getElementById('counter');
    var currentCount = parseInt(counterElement.innerHTML);

    // Imposta il numero desiderato
    var targetNumber = currentCount;

    //inizializzo
    counterElement.innerHTML = 0;
    currentCount = 0;

    //passo alla funzione che si occupa di fare il counter
    incrementCounter(targetNumber, currentCount, counterElement);
}

function incrementCounter(targetNumber, currentCount, counterElement) {
    //qua sistemo i valori
    currentCount += 5;
    counterElement.innerHTML = currentCount;

    // Incrementa il contatore
    if (currentCount < targetNumber)
        setTimeout(function (){
            incrementCounter(targetNumber, currentCount, counterElement);
        }, 5);
    else
        counterElement.innerHTML = targetNumber;
}

function getGameCounter(){
    //prendo il componente dal mio html
    var counterElement = document.getElementById('gameCounter');
    var currentCount = parseInt(counterElement.innerHTML);

    // Imposta il numero desiderato
    var targetNumber = currentCount;

    //inizializzo
    counterElement.innerHTML = 0;
    currentCount = 0;

    //passo alla funzione che si occupa di fare il counter
    incrementGameCounter(targetNumber, currentCount, counterElement);
}

function incrementGameCounter(targetNumber, currentCount, counterElement) {
    //qua sistemo i valori
    currentCount += 1;
    counterElement.innerHTML = currentCount;

    // Incrementa il contatore
    if (currentCount < targetNumber)
        setTimeout(function () {
            incrementGameCounter(targetNumber, currentCount, counterElement);
        }, 50);
    else
        counterElement.innerHTML = targetNumber;
}

// Chiama la funzione per iniziare l'incremento del contatore
getCounter();
// Chiama la funzione per iniziare l'incremento del contatore
getGameCounter();
