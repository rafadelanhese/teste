const HTTP_STATUS_OK = 200;
var URL = "http://localhost:8080/projeto-teste/rs/setor";
window.onload = getTodosSetores;

function getTodosSetores() {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", URL, true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.status == HTTP_STATUS_OK) {
      var listaSetores = JSON.parse(this.responseText);

	  var linhasTabela = "<tr><th>ID</th><th>Nome</th><th>Editar</th><th>Excluir</th></tr>";

      for(var setor of listaSetores){
        linhasTabela += linhaTabela(setor);
      }

	  document.getElementById("tabela").innerHTML = linhasTabela;
    } else {
      console.log("Algo deu errado");
    }
  };
}

function linhaTabela(setor){
	return "<tr>"
	+ "<td>"+ setor.id +"</td>"
	+"<td>"+ setor.nome +"</td>"
	+"<td><button onclick='editar("+ setor.id +")'>Editar</button></td>"
	+"<td><button onclick='excluir("+ setor.id +")'>Excluir</button></td></tr>"
}

function editar(idSetor){
	localStorage.setItem('idSetor', idSetor);
	window.location.assign("http://localhost:8080/projeto-teste/views/setor/criar.html");
}

function excluir(idSetor){
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE", URL.concat('/', idSetor), true);
	xhttp.onreadystatechange = function(){
	    if(xhttp.readyState === XMLHttpRequest.DONE){
            if(xhttp.status === 0 || xhttp.status == HTTP_STATUS_OK){
                console.log(xhttp.responseText);
                getTodosSetores();
            } else {
                console.log("Funcionário não foi deletado");
            }
        }
	}
	xhttp.send();
}


