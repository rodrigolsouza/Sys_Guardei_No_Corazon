var myModal = new bootstrap.Modal(document.getElementById('modalAltera'));

//PEGANDO TODOS OS DESAFETOS  = READALL E EXIBINDO
fetch("http://localhost:8092/desavenca",{
    method: "GET",
    headers: {"Content-type": "application/json;charset=UTF-8"}
}).then(data => data.json())
.then(json => {

    const divMain = document.getElementById("main")
    
    let s = "<table class='table'>"
        +"<tr><th>Código</th><th>Tipo</th><th>Violencia Fisica</th><th>Local</th><th>Descricao</th><th>Data</th><th>Hora</th><th>Ações</th></tr>"

    json.forEach(element => {
        
        s += `<tr><td>${element.id}</td><td>${element.tipo}</td><td>${element.violenciaFisica}</td><td>${element.local}</td><td>${element.descricao}</td><td>${element.data}</td><td>${element.hora}</td>`
        +`<td><button class="btn btn-primary" onclick="abrirModalAltera(${element.id})" >alterar</button>`
        +`<button class="btn btn-secondary" onclick="deletar(${element.id})">deletar</button></td></tr>`

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

    const retorno = await fetch("http://localhost:8092/desavenca/"+id,
        {
            method: "GET",
            headers: {"Content-type": "application/json;charset=UTF-8"}
        }
    )

    retorno.json().then(json => {

        document.getElementById("txtCodigo").value = json.id
        document.getElementById("txtTipo").value = json.tipo
        document.getElementById("txtViolenciaFisica").value = json.violenciaFisica
        document.getElementById("txtLocal").value = json.local
        document.getElementById("txtDescricao").value = json.descricao
        document.getElementById("txtData").value = json.data
        document.getElementById("txtHora").value = json.hora

    })

};

//Função para deletar um registro
async function deletar(id){

    if(confirm("Deseja realmente deletar o registro?")){

        const retorno = await fetch("http://localhost:8092/desavenca/"+id,
        {
            method:"DELETE",
            headers: {"Content-type": "application/json;charset=UTF-8"}
        })

        location.reload();

    }

};