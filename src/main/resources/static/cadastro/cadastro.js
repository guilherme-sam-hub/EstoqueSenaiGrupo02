const nome = document.getElementById("nomeInput");
const email = document.getElementById("emailInput");
const senha = document.getElementById("senhaInput");
const apiURL = "http://localhost:8080/usuario";
const formCadastro = document.getElementById("formCadastro");
const listaUsuarios = document.getElementById("listaUsuarios")
let idEditando = null;

//------Listar Usuários---------
async function listarUsuarios(){ //função que lista usuários do banco de dados 
    try{
        const resposta = await fetch(apiURL);//quando não nomeamos o method, por padrão o JS usa o GET
        const usuarios = await resposta.json();
        listaUsuarios.innerHTML = ""; //tira todo elemento que estiver na linha(os botoes editar e excluir)

        usuarios.forEach(usuario => { //
            const linha = document.createElement("tr");

            linha.innerHTML = `
            <td class="colunaId">${usuario.id}</td>
            <td>${usuario.nome}</td>
            <td>${usuario.email}</td>
            <td class="linhaBotao">
                <button class="botoes btnEditar" data-id="${usuario.id}">
                    <img class="imgEdtr" src="../cadastro/imagens/btnEdtr.png" alt= "Botão de Editar">
                </button>

                <button class="botoes btnExcluir" data-id="${usuario.id}"> 
                    <img class="imgExclr" src="../cadastro/imagens/btnExclr.png" alt= "Botão de Excluir">
                </button>
            </td>
            `;
            listaUsuarios.appendChild(linha);
        });

    }catch(erro){
        console.error("Erro ao listar usuários" + erro);
    }
}


//Cadastrar e Editar Usuário
formCadastro.addEventListener("submit", async (e)=>{
    e.preventDefault();

    const usuario = {
        nome: nome.value,
        email: email.value,
        senha: senha.value
    };
    
    try{

        let resposta;
        if(idEditando === null){//se o id estiver vazio é pq está cadastrando usuario
            resposta = await fetch(apiURL, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(usuario) //enviando daodos em formato JSON
            });
        }else{//se não estiver vazio é porque está editando usuário
            resposta = await fetch(`${apiURL}/${idEditando}`,{
                method: "PUT",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(usuario)
            });
        }

        if(!resposta.ok){
            throw new Error("Erro ao salvar usuário.");
        }

        formCadastro.reset();

        idEditing = null;

        await listarUsuarios();

        
    }catch(erro){
        console.error("Erro ao salvar usuário: " + erro);
    }
});
// -------------Botão Excluir e Editar------------------

listaUsuarios.addEventListener("click", async function(event) {

    // BOTÃO EDITAR

    const botaoEditar = event.target.closest(".btnEditar");

    if (botaoEditar) {

        const id = botaoEditar.dataset.id;

        try {

            const resposta = await fetch(`${apiURL}/${id}`);

            if (!resposta.ok) {

                throw new Error("Erro ao buscar usuário.");

            }

            const usuario = await resposta.json();

            nome.value = usuario.nome;

            email.value = usuario.email;

            senha.value = usuario.senha;

            idEditando = id;

        } catch (erro) {

            console.error("Erro ao buscar usuário:", erro);

        }

        return;

    }

    // BOTÃO EXCLUIR

    const botaoExcluir = event.target.closest(".btnExcluir");

    if (botaoExcluir) {

        const id = botaoExcluir.dataset.id;

        try {

            const resposta = await fetch(`${apiURL}/${id}`, {

                method: "DELETE"

            });

            if (!resposta.ok) {

                throw new Error("Erro ao excluir usuário.");

            }

            await listarUsuarios();

        } catch (erro) {

            console.error("Erro ao excluir usuário:", erro);

        }

    }

});

// ==========================

// INICIA A LISTAGEM

// ==========================

listarUsuarios();