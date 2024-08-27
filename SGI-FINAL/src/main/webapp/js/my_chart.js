var ctx = document.getElementById('myChart').getContext('2d');
var earning = document.getElementById('earning').getContext('2d');
let previousValoresContas = null;
let previousDizimoData = null;
let previousOfertaData = null;

async function loadChartData() {
    try {
        var timestamp = new Date().getTime(); 

        const response = await fetch('/SGI/JSON/valoresContas.json?ts=' + timestamp);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        const valores = data.valores;
        const pago = parseFloat(valores.Pago);
        const pendente = parseFloat(valores.Pendente);
        const atrasado = parseFloat(valores.Atrasado);

        // Verifica se os dados mudaram
        if (JSON.stringify(data) !== JSON.stringify(previousValoresContas)) {
            previousValoresContas = data; // Atualiza o valor anterior
            updateChart(pago, pendente, atrasado);
        }

    } catch (error) {
        console.error('Erro ao carregar o JSON:', error);
    }
}


let myChart; // Declaração fora da função

function updateChart(pago, pendente, atrasado) {
    if (myChart) {
        myChart.destroy(); // Destrói o gráfico existente, se houver
    }

    const ctx = document.getElementById('myChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Pago', 'Pendente', 'Atrasada'],
            datasets: [{
                label: 'Contas',
                data: [pago, pendente, atrasado],
                backgroundColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
            }]
        },
        options: {
            responsive: true,
        }
    });
}


// Carregar os dados e criar o gráfico quando a página estiver pronta
document.addEventListener('DOMContentLoaded', function() {
    loadChartData();

    function loadDizimoEOfertaData() {
        var timestamp = new Date().getTime(); // Cria um timestamp único para evitar cache

        $.ajax({
            url: '/SGI/JSON/chartDizimo.json?ts=' + timestamp,
            dataType: 'json',
            cache: false,
            success: function(dizimoData) {
                $.ajax({
                    url: '/SGI/JSON/chartOferta.json?ts=' + timestamp,
                    dataType: 'json',
                    cache: false,
                    success: function(ofertaData) {
                        // Verifica se os dados mudaram
                        if (JSON.stringify(dizimoData) !== JSON.stringify(previousDizimoData) ||
                            JSON.stringify(ofertaData) !== JSON.stringify(previousOfertaData)) {
                            
                            previousDizimoData = dizimoData; // Atualiza os valores anteriores
                            previousOfertaData = ofertaData;

                            var meses = [];
                            var dizimoValores = [];
                            var ofertaValores = [];

                            dizimoData.dizimosOfertas.forEach(function(item) {
                                meses.push(item.mes);
                                dizimoValores.push(parseFloat(item.valor));
                            });

                            ofertaData.dizimosOfertas.forEach(function(item) {
                                ofertaValores.push(parseFloat(item.valor));
                            });

                            var ctx = document.getElementById('earning').getContext('2d');
                            var myChart = new Chart(ctx, {
                                type: 'bar',
                                data: {
                                    labels: meses,
                                    datasets: [
                                        {
                                            label: 'Dízimos',
                                            data: dizimoValores,
                                            backgroundColor: 'rgba(54, 162, 235, 1)',
                                        },
                                        {
                                            label: 'Ofertas',
                                            data: ofertaValores,
                                            backgroundColor: 'rgba(255, 206, 86, 1)',
                                        }
                                    ]
                                },
                                options: {
                                    responsive: true,
                                    scales: {
                                        x: {
                                            beginAtZero: true,
                                        },
                                        y: {
                                            beginAtZero: true
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function() {
                        console.error('Erro ao carregar o JSON de Oferta.');
                    }
                });
            },
            error: function() {
                console.error('Erro ao carregar o JSON de Dízimo.');
            }
        });
    }

    // Carrega os dados inicialmente
    loadDizimoEOfertaData();

    // Verifica por atualizações a cada 5 segundos
    setInterval(function() {
        loadChartData();
        loadDizimoEOfertaData();
    }, 5000);
});
