var URL = "http://localhost:8080/projeto-teste/rs/setor";
window.onload = getTodosSetores;

function getTodosSetores() {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", URL, true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.status == 200) {
      var listaSetores = JSON.parse(this.responseText);

	  var linhasTabela = "<tr><th>ID</th><th>Nome</th><th>Editar</th><th>Excluir</th></tr>";

	  for(var posicao = 0; posicao < listaSetores.length; posicao++){
		  linhasTabela += linhaTabela(listaSetores[posicao]);
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
	+"<td><button onclick='excluir("+ setor.id +")'>Excluir</button></td>"
}

function editar(idSetor){
	localStorage.setItem('idSetor', idSetor);
	window.location.assign("http://localhost:8080/projeto-teste/pages/setor-criar.html");
}

function excluir(idSetor){
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE", URL.concat('/', idSetor), true);
	xhttp.onreadystatechange = function(){
	    if(xhttp.readyState === XMLHttpRequest.DONE){
            if(xhttp.status === 0 || xhttp.status == 200){
                console.log(xhttp.responseText);
                getTodosSetores();
            } else {
                console.log("Funcionário não foi deletado");
            }
        }
	}
	xhttp.send();
}

function modalExcluir(setor){
	document.getElementById("modalExcluir").showModalDialog;
}


