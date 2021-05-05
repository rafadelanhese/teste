var URL =  "http://localhost:8080/projeto-teste/rs/funcionario";
var botaoEnviarFormulario = document.getElementById("enviar");
const HTTP_STATUS_OK = 200;
const HTTP_STATUS_CREATED = 201;


window.addEventListener("load", function(event) {
    getTodosSetores();
    temDadosLocalStorage();
});

botaoEnviarFormulario.addEventListener("click", function (event) {
  event.preventDefault();
  var id = document.getElementById('id').value;

  if(validarCamposFormulario()){
    var json = serialize();
    var xhttp = new XMLHttpRequest();
    if(id == null || id == ''){
          xhttp.open("POST", URL, true);
      } else {
          xhttp.open("PUT", URL.concat("/", id), true);
      }
      xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
      xhttp.send(json);
      xhttp.onreadystatechange = function(){
        if(xhttp.readyState === XMLHttpRequest.DONE){
              if(xhttp.status == HTTP_STATUS_OK || xhttp.status == HTTP_STATUS_CREATED){
                  console.log(xhttp.responseText);
                  initDadosForm();
              } else {
                  console.log(xhttp.responseText);
              }
         }
      }
  }
});

var serialize = function () {
  var json = {};
  var form = document.getElementById('formulario');
  var data = new FormData(form);
  var keys = data.keys();
  for (var key = keys.next(); !key.done; key = keys.next()) {
      var values = data.getAll(key.value);
	  if(key.value == "setor"){
	      var setor = {};
          var setorOption = document.getElementById(values[0]);
          setor["id"] = values[0];
          setor["nome"] = setorOption.dataset.nome;
          json[key.value] = setor;
	  } else {
		json[key.value] = values.length == 1 ? values[0] : values;
	  }
    }
  return JSON.stringify(json);
}

function temDadosLocalStorage(){
	if(localStorage.getItem('idFuncionario')){
      var id = localStorage.getItem('idFuncionario');
      localStorage.removeItem('idFuncionario');

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", URL.concat("/", id), true);
      xhttp.send();
      xhttp.onreadystatechange = function() {
        if (xhttp.status == HTTP_STATUS_OK) {
			var funcionario = JSON.parse(this.responseText);
			preencheDadosForm(funcionario);
        } else {
          console.log("Algo deu errado");
        }
      };
    }
}

function preencheDadosForm(funcionario){
    formulario.id.value = funcionario.id;
    formulario.nome.value = funcionario.nome;
	formulario.idade.value = funcionario.idade;
    formulario.email.value = funcionario.email;
    formulario.salario.value = funcionario.salario;
	document.getElementById("setor").value = funcionario.setor.id;
}

function initDadosForm(){
    formulario.id.value = '';
    formulario.nome.value = '';
    formulario.idade.value = '';
    formulario.email.value = '';
    formulario.salario.value = '';
    formulario.setor.value = 0;
}

function getTodosSetores(){
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", "http://localhost:8080/projeto-teste/rs/setor", true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (xhttp.status == HTTP_STATUS_OK) {
      var listaSetores = JSON.parse(xhttp.responseText);

      populaSelectSetores(listaSetores);
    } else {
      console.log("Algo deu errado");
    }
  };
}

function populaSelectSetores(listaSetores){
    const qtdeMinimaElementos = 0;
    var select = document.getElementById('setor');
    if(select.childElementCount > qtdeMinimaElementos){
      select.clear();
    }
    for(var setor of listaSetores){
        var option = document.createElement('option');
        option.setAttribute("id", setor.id);
        option.setAttribute("data-nome", setor.nome);
        option.value = setor.id;
        option.text = setor.nome;
        select.appendChild(option);
    }
}

function validarCamposFormulario(){
    if(validaVazioOuNulo(formulario.nome) && validaVazioOuNulo(formulario.idade)
    && validaIdade(formulario.idade) && validaMenorQueZero(formulario.idade) && validaVazioOuNulo(formulario.email)
    && validaVazioOuNulo(formulario.salario) && validaMenorQueZero(formulario.salario) && validaMenorQueZero(formulario.email)){
        return true;
    } else {
        return false;
    }
}

function validaVazioOuNulo(valor){
    return valor.value == '' || valor.value == null ? valor.focus() : true;
}

function validaMenorQueZero(valor){
   const valorZerado = 0;
   return valor.value < valorZerado ? valor.focus() : true;
}

function validaIdade(valor){
    const idadeMaxima = 100;
    return valor.value > idadeMaxima ? valor.focus() : true;
}

