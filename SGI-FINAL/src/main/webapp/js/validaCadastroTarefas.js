/**
 * 
 */
function validarCadastroTarefa() {
   var titulo = document.forms["frmMembro"]["trftitulo"].value;
    var filial = document.forms["frmMembro"]["trffilial"].value;
    var descricao = document.forms["frmMembro"]["trfdesc"].value;
    var membroId = document.forms["frmMembro"]["trfmbrid"].value;
    var data = document.forms["frmMembro"]["trfdata"].value;
    var hora = document.forms["frmMembro"]["trfhora"].value;
    var status = document.forms["frmMembro"]["trfstatus"].value;

    // Verificando se todos os campos estão preenchidos
    if (titulo == "" || filial == "" || descricao == "" || membroId == "" || data == "" || hora == "" || status == "") {
        alert(decodeURIComponent("Por favor, preencha todos os campos."));
        return false;
    }

    // Verificando se a data e hora não são anteriores à data e hora atual
    var dataAtual = new Date().toISOString().slice(0, 10);
    var horaAtual = new Date().toTimeString().slice(0, 5);

    if (data < dataAtual || (data == dataAtual && hora < horaAtual)) {
        alert(decodeURIComponent("A data e hora nao podem ser anteriores a data e hora atual."));
        return false;
    }

    return true;
}
