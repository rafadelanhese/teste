var URL =  "http://localhost:8080/projeto-teste/rs/funcionario";
window.onload = getTodosFuncionarios;

function getTodosFuncionarios() {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", URL, true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.status == 200) {
      var listaFuncionarios = JSON.parse(this.responseText);

	  var linhasTabela = "<tr><th>ID</th><th>Nome</th><th>E-mail</th><th>Idade</th><th>Salário</th><th>Setor</th><th>Editar</th><th>Excluir</th></tr>";

	  for(var posicao = 0; posicao < listaFuncionarios.length; posicao++){
		  linhasTabela += linhaTabela(listaFuncionarios[posicao]);
	  }
	  document.getElementById("tabela").innerHTML = linhasTabela;
    } else {
      console.log("Algo deu errado");
    }
  };
}

function linhaTabela(funcionario){
	return "<tr>"
	+ "<td>"+ funcionario.id +"</td>"
	+"<td>"+ funcionario.nome +"</td>"
	+"<td>"+ funcionario.email +"</td>"
	+"<td>"+ funcionario.idade +"</td>"
	+"<td>"+ funcionario.salario +"</td>"
	+"<td>"+ funcionario.setor.nome +"</td>"
	+"<td><button onclick='editar("+ funcionario.id +")'>Editar</button></td>"
	+"<td><button onclick='excluir("+ funcionario.id +")'>Excluir</button></td>"
}

function editar(idFuncionario){
	localStorage.setItem('idFuncionario', idFuncionario);
	window.location.assign("http://localhost:8080/projeto-teste/pages/funcionario-criar.html");
}

function excluir(idFuncionario){
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE", URL.concat("/", idFuncionario), true);
	xhttp.onreadystatechange = function(){
	    if(xhttp.readyState === XMLHttpRequest.DONE){
	        if(xhttp.status === 0 || xhttp.status == 200){
                console.log("Funcionário deletado com sucesso");
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


