var URL =  "http://localhost:8080/projeto-teste/rs/funcionario";
var enviar = document.getElementById("enviar");

window.addEventListener("load", function(event) {
    getTodosSetores();
    temDadosLocalStorage();
});

enviar.addEventListener("click", function (event) {
  event.preventDefault();
  var id = document.getElementById('id').value;
  var json = serialize();
  var xhttp = new XMLHttpRequest();

  if(id == null || id == ''){
      xhttp.open("POST", URL, true);
  } else {
      xhttp.open("PUT", URL.concat("/", id), true);
  }
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(JSON.stringify(json));
  xhttp.onreadystatechange = function(){
    if(xhttp.readyState === XMLHttpRequest.DONE){
          if(xhttp.status === 0 || (xhttp.status == 200 || xhttp.status == 201)){
              console.log(xhttp.responseText);
              initDadosForm();
          } else {
              console.log(xhttp.responseText);
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
      json[key.value] = values.length == 1 ? values[0] : values;
    }
  return json;
}

function temDadosLocalStorage(){
	if(localStorage.getItem('idFuncionario')){
      var id = localStorage.getItem('idFuncionario');
      localStorage.removeItem('idFuncionario');

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", URL.concat("/", id), true);
      xhttp.send();
      xhttp.onreadystatechange = function() {
        if (xhttp.status == 200) {
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
	formulario.setor.value = funcionario.setor.id;
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
    if (xhttp.status == 200) {
      var listaSetores = JSON.parse(xhttp.responseText);

	  var optionSelect = "";
      var setor;
	  for(var posicao = 0; posicao < listaSetores.length; posicao++){
	      setor = listaSetores[posicao];
		  optionSelect += "<option value="+ setor.id +">"+ setor.nome +"</option>";
	  }
	  document.getElementById("setor").innerHTML = optionSelect;
    } else {
      console.log("Algo deu errado");
    }
  };
}

function validarCamposFormulario(){
    if(validaVazioOuNulo(formulario.nome) && validaVazioOuNulo(formulario.idade)
    && validaMenosQueZero(formulario.idade) && validaVazioOuNulo(formulario.email)
    && validaVazioOuNulo(formulario.salario) && validaMenosQueZero(formulario.salario) && validaMenosQueZero(formulario.email)){
        return true;
    } else {
        return false;
    }
}

function validaVazioOuNulo(valor){
    if(valor.value == '' || valor.value == null){
        valor.focus();
        return false;
    } else {
        return true;
    }
}

function validaMenosQueZero(valor){
   if(valor.value < 0){
        valor.focus();
        return false;
    } else {
        return true;
    }
}

