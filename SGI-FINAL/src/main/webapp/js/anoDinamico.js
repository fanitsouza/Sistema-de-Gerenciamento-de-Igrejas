// Obtenha o elemento de seleção de ano
const anoSelect = document.getElementById('ano');

// Defina o intervalo de anos
const anoInicio = 1990; // Ano inicial
const anoFim = new Date().getFullYear(); // Ano atual

// Popule as opções de ano dinamicamente
for (let ano = anoInicio; ano <= anoFim; ano++) {
    const option = document.createElement('option');
    option.value = ano;
    option.textContent = ano;
    anoSelect.appendChild(option);
}