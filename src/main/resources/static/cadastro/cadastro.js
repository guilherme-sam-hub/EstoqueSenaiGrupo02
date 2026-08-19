const nome = document.getElementById("nomeInput");
const email = document.getElementById("emailInput");
const senha = document.getElementById("senhaInput");
const apiURL = "http://localhost:8080/usuario";
const formCadastro = document.getElementById("formCadastro");
const listaUsuarios = document.getElementById("listaUsuarios");
let idEditando = null;

//------Listar Usuários---------
async function listarUsuarios() {
    try {
        const resposta = await fetch(apiURL);
        if (!resposta.ok) throw new Error("Falha ao buscar usuários");

        const usuarios = await resposta.json();
        listaUsuarios.innerHTML = "";

        if (usuarios.length === 0) {
            listaUsuarios.innerHTML = `<tr><td colspan="4" style="text-align:center; padding: 20px;">Nenhum usuário cadastrado.</td></tr>`;
            return;
        }

        usuarios.forEach(usuario => {
            const linha = document.createElement("tr");
            linha.classList.add("linhaBod");
            // ATENÇÃO: Caminhos absolutos para as imagens de editar e excluir
            linha.innerHTML = `
                <td class="colunaId">${usuario.id}</td>
                <td>${usuario.nome}</td>
                <td>${usuario.email}</td>
                <td class="linhaBotao">
                    <button class="botoes btnEditar" data-id="${usuario.id}">
                        <img class="imgEdtr" src="/cadastro/imagens/btnEdtr.png" alt="Botão de Editar">
                    </button>
                    <button class="botoes btnExcluir" data-id="${usuario.id}"> 
                        <img class="imgExclr" src="/cadastro/imagens/btnExclr.png" alt="Botão de Excluir">
                    </button>
                </td>
            `;
            listaUsuarios.appendChild(linha);
        });

    } catch (erro) {
        console.error("Erro ao listar usuários:", erro);
        alert("Erro ao carregar a lista de usuários.");
    }
}

//Cadastrar e Editar Usuário
formCadastro.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (!nome.value || !email.value || (!idEditando && !senha.value)) {
        alert("Por favor, preencha todos os campos obrigatórios.");
        return;
    }

    const usuario = {
        nome: nome.value,
        email: email.value,
        senha: senha.value
    };

    try {
        let resposta;
        if (idEditando === null) {
            resposta = await fetch(apiURL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(usuario)
            });
        } else {
            resposta = await fetch(`${apiURL}/${idEditando}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" }, // Corrigido
                body: JSON.stringify(usuario)
            });
        }

        if (!resposta.ok) {
            const erroTexto = await resposta.text();
            throw new Error(erroTexto || "Erro ao salvar usuário. Verifique se o e-mail já existe.");
        }

        alert(idEditando === null ? "Usuário cadastrado com sucesso!" : "Usuário atualizado com sucesso!");
        formCadastro.reset();
        idEditando = null;
        await listarUsuarios();

    } catch (erro) {
        console.error("Erro ao salvar usuário:", erro);
        alert("Erro: " + erro.message);
    }
});

// -------------Botão Excluir e Editar------------------
listaUsuarios.addEventListener("click", async function(event) {
    const botaoEditar = event.target.closest(".btnEditar");
    if (botaoEditar) {
        const id = botaoEditar.dataset.id;
        try {
            const resposta = await fetch(`${apiURL}/${id}`);
            if (!resposta.ok) throw new Error("Erro ao buscar usuário.");

            const usuario = await resposta.json();
            nome.value = usuario.nome;
            email.value = usuario.email;
            senha.value = ""; // Limpa a senha por segurança
            senha.placeholder = "Digite uma nova senha";
            idEditando = id;
            window.scrollTo({ top: 0, behavior: 'smooth' });
        } catch (erro) {
            console.error("Erro ao buscar usuário:", erro);
            alert("Não foi possível carregar os dados para edição.");
        }
        return;
    }

    const botaoExcluir = event.target.closest(".btnExcluir");
    if (botaoExcluir) {
        const id = botaoExcluir.dataset.id;
        if (!confirm("Tem certeza que deseja excluir este usuário?")) return;

        try {
            const resposta = await fetch(`${apiURL}/${id}`, { method: "DELETE" });
            if (!resposta.ok) throw new Error("Erro ao excluir usuário.");

            alert("Usuário excluído com sucesso!");
            await listarUsuarios();
            if (idEditando == id) {
                formCadastro.reset();
                idEditando = null;
            }
        } catch (erro) {
            console.error("Erro ao excluir usuário:", erro);
            alert("Erro ao excluir o usuário.");
        }
    }
});

// INICIA A LISTAGEM
document.addEventListener("DOMContentLoaded", listarUsuarios);