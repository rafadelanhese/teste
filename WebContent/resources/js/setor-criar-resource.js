var URL = "http://localhost:8080/projeto-teste/rs/setor";
var botaoEnviar = document.getElementById("enviar");
var botaoAtualizar = document.getElementById("atualizar");
const HTTP_STATUS_OK = 200;
const HTTP_STATUS_CREATED = 201;

window.addEventListener("load", function(event) {
    temDadosLocalStorage();
});


botaoEnviar.addEventListener("click", function (event) {
  event.preventDefault();

  if(validaVazioOuNulo(formulario.nome)){
      var id = document.getElementById('id').value;
      var json = serialize();
      var xhttp = new XMLHttpRequest();

      xhttp.open("POST", URL, true);
      xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
      xhttp.send(json);
      xhttp.onreadystatechange = function(){
            if(xhttp.readyState === XMLHttpRequest.DONE){
                  if(xhttp.status == HTTP_STATUS_CREATED){
                      exibeToast(xhttp.responseText);
                  } else {
                      exibeToast("Error ao adicionar setor");
                  }
            }
       }
  }

});

botaoAtualizar.addEventListener("click", function (event){
    event.preventDefault();
    if(validaVazioOuNulo(formulario.nome)){
        var id = document.getElementById('id').value;
        var json = serialize();
        var xhttp = new XMLHttpRequest();
        xhttp.open("PUT",URL.concat('/', id), true);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(json);
        xhttp.onreadystatechange = function(){
            if(xhttp.readyState === XMLHttpRequest.DONE){
                  if(xhttp.status == HTTP_STATUS_OK){
                      exibeToast(xhttp.responseText);
                      initDadosForm();
                  } else {
                      exibeToast("Error ao atualizar setor");
                  }
              }
        }
    }
});

function temDadosLocalStorage(){
	if(localStorage.getItem('idSetor')){
      var id = localStorage.getItem('idSetor');
      localStorage.removeItem('idSetor');

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", URL.concat('/', id), true);
      xhttp.send();
      xhttp.onreadystatechange = function() {
        if (xhttp.status == HTTP_STATUS_OK) {
			var setor = JSON.parse(xhttp.responseText);
			preencheDadosForm(setor);
			exibeBotaoAtualizarEOcultaBotaoEnviar();
        } else {
          console.log("Algo deu errado");
        }
      };
    }
}

function exibeBotaoAtualizarEOcultaBotaoEnviar(){
    document.getElementById('atualizar').style.display = 'inline';
    document.getElementById('enviar').style.display = 'none';
}

function preencheDadosForm(setor){
    formulario.id.value = setor.id;
    formulario.nome.value = setor.nome;
}

function initDadosForm(){
    formulario.id = '';
    formulario.nome = '';
}

var serialize = function () {
  var json = {};
  var form = document.getElementById('formulario');
  var data = new FormData(form);
  var keys = data.keys();
  for (var key = keys.next(); !key.done; key = keys.next()) {
      var values = data.getAll(key.value);
      json[key.value] = values.length == 1 ? values[0] : values;
    }
  return JSON.stringify(json);
}

function validaVazioOuNulo(valor){
    if(valor.value == '' || valor.value == null){
        valor.focus();
        exibeToast("Campo nome está vazio ou é nulo");
        return false;
    }
    return true;
}

function exibeToast(mensagem){
    document.getElementById("paragrafoToast").innerHTML = mensagem;
    $('#toastMensagem').toast('show');
}
