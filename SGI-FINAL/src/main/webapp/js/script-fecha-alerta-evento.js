function abrirModalAlertaEvento(id) {
    const modal = document.getElementById('janela-modal-alerta');
    modal.classList.add('abrir');
    const confirmaExlcusao = document.getElementById('confirma-exclusao');
    
    // Verificação para depuração
    console.log("ID: " + id);
    
    confirmaExlcusao.href = 'deleteEvento?idevento=' + id;

    modal.addEventListener('click', (e) => {
        if (e.target.id == 'fechar' || e.target.id == 'janela-modal-alerta' || e.target.id == 'fechar-alerta') {
            modal.classList.remove('abrir');
        }
    });
}
