var myModal = new bootstrap.Modal(document.getElementById('modalAltera'));

//PEGANDO TODOS OS DESAFETOS  = READALL E EXIBINDO
fetch("http://localhost:8092/desafeto",{
    method: "GET",
    headers: {"Content-type": "application/json;charset=UTF-8"}
}).then(data => data.json())
.then(json => {
    console.log(json);
    const divMain = document.getElementById("main");
    
    let s = "<table class='table'>"
        +"<tr><th>Código</th><th>Nome</th><th>Faixa etaria</th><th>Altura</th><th>Peso</th><th>Grau de Convivio</th><th>Descrição</th><th>Ações</th></tr>";

    json.forEach(element => {
        
        s += `<tr><td>${element.id}</td><td>${element.nome}</td><td>${element.faixaEtaria}</td><td>${element.altura}</td><td>${element.peso}</td><td>${element.grauConvivio}</td><td>${element.descricao}</td>`
        +`<td><button class="btn btn-primary" onclick="abrirModalAltera(${element.id})" >alterar</button>`
        +`<button class="btn btn-secondary" onclick="deletar(${element.id})">deletar</button>`
        +`<button class="btn btn-primary" onclick="detalharRegistros(${element.id})">detalhar</button></td></tr>`
    })

        /*for(let i = 0; i < json.length; i++){
            s += '<tr><td>'+json[i].codigo+'</td><td>'+json[i].nome+'</td><td>'+json[i].contato+'</td></tr>'
        }*/

    s += "</table>"
    
    divMain.innerHTML = s

}).catch( err => {
    document.getElementById("main").innerHTML = "Erro ao recuperar a lista"
});

/*Função para exibir o modal de alteração de um registro(que é chamada lá no botão dentro de fetch de readll) com campos preechidos com os dados anteriores
*/
async function abrirModalAltera(id){
    myModal.show()

    const retorno = await fetch("http://localhost:8092/desafeto/"+id,
        {
            method: "GET",
            headers: {"Content-type": "application/json;charset=UTF-8"}
        }
    )

    retorno.json().then(json => {
        
        document.getElementById("txtCodigo").value = json.id
        document.getElementById("txtNome").value = json.nome
        document.getElementById("txtFaixaEtaria").value = json.faixaEtaria
        document.getElementById("txtAltura").value = json.altura
        document.getElementById("txtPeso").value = json.peso
        document.getElementById("txtGrauConvivio").value = json.grauConvivio
        document.getElementById("txtDescricao").value = json.descricao

    })

};
//Função para deletar um registro
async function deletar(id){

    if(confirm("Deseja realmente deletar o registro?")){

        const retorno = await fetch("http://localhost:8092/desafeto/"+id,
        {
            method:"DELETE",
            headers: {"Content-type": "application/json;charset=UTF-8"}
        })

        location.reload();

    }
}
//enviando o link de uma pagina e o parametro id, que será necessário, junto
async function detalharRegistros(id){
    
    window.location=`desavencasDesafeto.html?${id}`;
};

