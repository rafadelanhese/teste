const HTTP_STATUS_OK = 200;
var URL = "http://localhost:8080/projeto-teste/rs/setor";

window.addEventListener("load", function(event) {
    getTodosSetores();
});

function getTodosSetores() {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", URL, true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (xhttp.status == HTTP_STATUS_OK) {
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
	+"<td><button class=\"btn btn-primary\" onclick='editar("+ setor.id +")'>Editar</button></td>"
	+"<td><button class=\"btn btn-danger\" onclick='excluir("+ setor.id +")'>Excluir</button></td></tr>"
}

function editar(idSetor){
	localStorage.setItem('idSetor', idSetor);
	window.location.assign("http://localhost:8080/projeto-teste/views/setor/criar.html");
}

function excluir(idSetor){
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE", URL.concat('/', idSetor), true);
	xhttp.send();
	xhttp.onreadystatechange = function(){
	    if(xhttp.readyState === XMLHttpRequest.DONE){
            if(xhttp.status == HTTP_STATUS_OK){
                exibeToast("Setor excluído com sucesso")
                getTodosSetores();
            } else {
                exibeToast("Setor não foi excluído")
            }
        }
	}
}

function exibeToast(mensagem){
    document.getElementById("paragrafoToast").innerHTML = mensagem;
    $('#toastMensagem').toast('show');
}



