var URL = "http://localhost:8080/projeto-teste/rs/setor";
var enviar = document.getElementById("enviar");

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
              if(xhttp.status === 0 || (xhttp.status == 200 || xhttp.status == 201)){
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
        if (this.status == 200) {
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
