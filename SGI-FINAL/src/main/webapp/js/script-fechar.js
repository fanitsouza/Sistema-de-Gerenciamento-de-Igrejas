document.addEventListener('DOMContentLoaded', () => {
    const modalSucesso = document.getElementById('janela-modal-sucesso');
    const modalErro = document.getElementById('janela-modal-erro');

    if (modalSucesso) {
        modalSucesso.classList.add('abrirSucesso');
        modalSucesso.addEventListener('click', (e) => {
            if (e.target.id == 'fechar-sucesso' || e.target.id == 'janela-modal-sucesso') {
                modalSucesso.classList.remove('abrirSucesso');
            }
        });
    }

    if (modalErro) {
        modalErro.classList.add('abrirErro');
        modalErro.addEventListener('click', (e) => {
            if (e.target.id == 'fechar-erro' || e.target.id == 'janela-modal-erro') {
                modalErro.classList.remove('abrirErro');
            }
        });
    }
});