function verificarRespostas(assunto) {
    const assuntoInt = parseInt(assunto);
    const info = document.getElementById('informacao');
    const subText = document.querySelector('.sub-text'); // Seleciona o elemento onde a pontuação será exibida
    info.classList.add('abrir');

    info.addEventListener('click', (e) =>{
        if (e.target.id == 'fechar') {
            info.classList.remove('abrir');
        }
    });

    const respostasCorretas = {
        1: { questao1: 'a', questao2: 'c', questao3: 'b' },
        2: { questao1: 'c', questao2: 'b', questao3: 'c' },
        3: { questao1: 'b', questao2: 'c', questao3: 'a' },
        4: { questao1: 'a', questao2: 'c', questao3: 'a' },
        5: { questao1: 'c', questao2: 'b', questao3: 'a' },
    };

    const respostas = respostasCorretas[assuntoInt];
    if (!respostas) {
        console.error('Assunto inválido.');
        return;
    }

    let pontuacao = 0;
    for (const pergunta in respostas) {
        const respostaSelecionada = document.querySelector(`input[name="${pergunta}"]:checked`);
        if (respostaSelecionada && respostaSelecionada.value === respostas[pergunta]) {
            pontuacao++;
        }
    }



    // Insere a pontuação na janela modal
    subText.textContent = `Você acertou ${pontuacao} pergunta(s).`;
}
