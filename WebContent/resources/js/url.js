var winLocaltionHref = window.location.href;
var urlSplit = winLocaltionHref.split('/');
var urlCorreta = urlSplit[0] + '//' + urlSplit[2] + '/' + urlSplit[3];

var url = {
	setor: function(){		
		return urlCorreta + "/rs/setor";
	},
	setorPorID: function(id){
		return urlCorreta + "/rs/setor/" + id;
	},
	funcionario: function(){
		return urlCorreta + "/rs/funcionario";
	},
	funcionarioPorID: function(id){
		return urlCorreta + "/rs/funcionario/" + id;
	}
}

export { url };