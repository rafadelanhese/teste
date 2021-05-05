var URL = "http://localhost:8080/projeto-teste/rs/setor";
var enviar = document.getElementById("enviar");
const HTTP_STATUS_OK = 200;
const HTTP_STATUS_CREATED = 201;

window.addEventListener("load", function(event) {
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
      xhttp.open("PUT",URL.concat('/', id), true);
  }
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(JSON.stringify(json));
  xhttp.onreadystatechange = function(){
  	    if(xhttp.readyState === XMLHttpRequest.DONE){
              if(xhttp.status == HTTP_STATUS_OK || xhttp.status == HTTP_STATUS_CREATED){
                  console.log(xhttp.responseText);
                  initDadosForm();
              } else {
                  console.log("Error ao adicionar setor");
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
        if (this.status == HTTP_STATUS_OK) {
			var setor = JSON.parse(this.responseText);
			preencheDadosForm(setor);
        } else {
          console.log("Algo deu errado");
        }
      };
    }
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
  return json;
}
