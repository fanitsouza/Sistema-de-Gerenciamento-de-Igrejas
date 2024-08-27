// Obtém os parâmetros da URL
const urlParams = new URLSearchParams(window.location.search);
const videoId = urlParams.get('nomevideo');
const quest_1 = document.getElementById('questao1');
const quest_2 = document.getElementById('questao2');
const quest_3 = document.getElementById('questao3');
const quest_4 = document.getElementById('questao4');
const quest_5 = document.getElementById('questao5');

console.log(videoId)

console.log(quest_1);

// Define a fonte de vídeo com base no parâmetro
const videoSrc = {
    1: './assets/videos/membros.mp4',
    2: './assets/videos/tarefas.mp4',
    3: './assets/videos/contas_a_pagar.mp4',
    4: './assets/videos/patrimonio.mp4',
    5: './assets/videos/dizimo.mp4'
};

// Cria o elemento de vídeo e define a fonte correspondente
if (videoId && videoSrc[videoId]) {
    const videoElement = document.createElement('video');
    videoElement.setAttribute('controls', 'controls');
    videoElement.setAttribute('width', '1000');
    videoElement.src = videoSrc[videoId];

    document.getElementById('video-container').appendChild(videoElement);
} else {
    document.getElementById('video-container').innerHTML = '<p>Vídeo não encontrado.</p>';
}

switch(parseInt(videoId)){
    case 1:
        quest_1.classList.add('abrir');
        
        console.log(quest_1);
        
        break;
    case 2:
        quest_2.classList.add('abrir');
        break;
    case 3:
        quest_3.classList.add('abrir')
        break;
    case 4:
        quest_4.classList.add('abrir');
        break;
    case 5:
        quest_5.classList.add('abrir');
        break;
}