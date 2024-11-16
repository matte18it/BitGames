// Dati per il grafico a linee
const getLast30Days = () => {
    const today = new Date();
    const last30Days = Array.from({ length: 30 }, (_, index) => {
        const date = new Date(today);
        date.setDate(today.getDate() - index);
        return date;
    });
    return last30Days;
};

const formatDate = (date) => {
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    return `${day}/${month}`;
};

let myLineChart;  //dichiaro il grafico su cui vado a fare le varie operazioni di aggiornamento

function initializeChart(data) {
    const last30Days = getLast30Days().reverse();
    const labels = last30Days.map(formatDate);

    const config = {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Partite Giocate',
                data: data,
                fill: true,
                borderColor: '#85996e',
                backgroundColor: 'rgba(133, 153, 110, 0.2)',
                tension: 0.1,
                legend: false
            }]
        },
        options: {
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    };

    const ctx = document.getElementById('myLineChart').getContext('2d');
    myLineChart = new Chart(ctx, config);
}

function updateChart() {
    // Effettuo una chiamata AJAX per ottenere i nuovi dati dal server
    $.ajax({
        url: "/updateData",
        type: "GET",
        success: function (data) {
            // Inizializza il grafico la prima volta che ottieni i dati
            if (!myLineChart) {
                initializeChart(data);
            } else {
                // Aggiorna i dati del grafico
                myLineChart.data.datasets[0].data = data;

                // Aggiorna l'etichetta del tempo (se necessario)
                const newLabels = getLast30Days().reverse().map(formatDate);
                myLineChart.data.labels = newLabels;

                // Aggiorna il grafico
                myLineChart.update();
            }
        },
        error: function () {
            alert("Si è verificato un errore durante l'aggiornamento dei dati!");
        }
    });
}

// Chiama la funzione updateChart immediatamente
updateChart();

// Ogni minuto chiama la funzione updateChart per aggiornare il grafico quasi in real-time
setInterval(updateChart, 60000);
