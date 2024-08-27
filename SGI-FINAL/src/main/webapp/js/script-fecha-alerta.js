function abrirModalAlerta(id, mes, ano, tipo) {
    const modal = document.getElementById('janela-modal-alerta');
    modal.classList.add('abrir');
    const confirmaExlcusao = document.getElementById('confirma-exclusao');
    
    // Verificação para depuração
    console.log("ID: " + id + ", Mês: " + mes + ", Ano: " + ano + ", Tipo: " + tipo);
    
    confirmaExlcusao.href = 'desativaDizOferta?dzoid=' + id + '&p_excluir_mes=' + mes + '&p_excluir_ano=' + ano + '&p_excluir_tipo=' + tipo;

    modal.addEventListener('click', (e) => {
        if (e.target.id == 'fechar' || e.target.id == 'janela-modal-alerta' || e.target.id == 'fechar-alerta') {
            modal.classList.remove('abrir');
        }
    });
}
