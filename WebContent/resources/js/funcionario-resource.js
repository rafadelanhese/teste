var URL =  "http://localhost:8080/projeto-teste/rs/funcionario";
const HTTP_STATUS_OK = 200;

window.onload = getTodosFuncionarios;

function getTodosFuncionarios() {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", URL, true);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.status == HTTP_STATUS_OK) {
      var listaFuncionarios = JSON.parse(this.responseText);

	  var linhasTabela = "<tr><th>ID</th><th>Nome</th><th>E-mail</th><th>Idade</th><th>Salário</th><th>Setor</th><th>Editar</th><th>Excluir</th></tr>";

	  for(var funcionario of listaFuncionarios){
      		linhasTabela += linhaTabela(funcionario);
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
	+"<td><button class=\"btn btn-primary\" onclick='editar("+ funcionario.id +")'>Editar</button></td>"
	+"<td><button class=\"btn btn-danger\" onclick='excluir("+ funcionario.id +")'>Excluir</button></td></tr>"
}

function editar(idFuncionario){
	localStorage.setItem('idFuncionario', idFuncionario);
	window.location.assign("http://localhost:8080/projeto-teste/views/funcionario/criar.html");
}

function excluir(idFuncionario){
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE", URL.concat("/", idFuncionario), true);
	xhttp.onreadystatechange = function(){
	    if(xhttp.readyState === XMLHttpRequest.DONE){
	        if(xhttp.status == HTTP_STATUS_OK){
                console.log(xhttp.responseText);
                getTodosFuncionarios();
            } else {
                console.log("Funcionário não foi deletado");
            }
	    }
	}
	xhttp.send();
}

