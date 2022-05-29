function validarCPF(inputCPF) {
    var soma = 0;
    var resto;
    var inputCPF = document.getElementById('cpf').value;

    if (inputCPF == '00000000000')
        return false;
    for (i = 1; i <= 9; i++)
        soma = soma + parseInt(inputCPF.substring(i - 1, i)) * (11 - i);
    resto = (soma * 10) % 11;

    if ((resto == 10) || (resto == 11))
        resto = 0;
    if (resto != parseInt(inputCPF.substring(9, 10)))
        return false;

    soma = 0;
    for (i = 1; i <= 10; i++)
        soma = soma + parseInt(inputCPF.substring(i - 1, i)) * (12 - i);
    resto = (soma * 10) % 11;

    if ((resto == 10) || (resto == 11))
        resto = 0;
    if (resto != parseInt(inputCPF.substring(10, 11)))
        return false;
    return true;
}

function mascaraCPF() {
        // *OBS - ARRUMAR
        var cpf = document.getElementById("cpf").value
        console.log(cpf)
        if (cpf.value.length == 3 || cpf.value.length == 7){
            cpf.value += "."
        } else if (cpf.value.length == 11){
            cpf.value += "-"
        }
    }

function confirmarUsuario(formulario) {
    

}

function cadastro(){
    alert('Caso exista uma conta com o e-mail informado vamos enviar um e-mail com as instruções e o link para você trocar a senha. Se você não receber o e-mail em alguns minutos, verifique a sua caixa de spam ou tente novamente.');
    var senha = Math.random().toString(36).slice(-10);
    var email = document.getElementById('email').value;
    alert('Senha nova: ' + senha + '.');
    /*
    Email.send({
        Host : "smtp.gmail.com",
        Username : "mercadofipp@gmail.com",
        Password : "Senha123",
        To : email,
        From : "mercadofipp@gmail.com",
        Subject : "MercadoFIPP: senha nova",
        Body : "Senha nova: " + senha + "."
    }).then(
      alert('LIXOOOOOOOOOOOOOOOOOOOO')
    );*/

}