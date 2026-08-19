document.addEventListener("DOMContentLoaded", () => {
    const inputNome = document.getElementById("input-nome");
    const inputQuantidade = document.getElementById("input-quantidade");
    const inputPreco = document.getElementById("input-preco");
    const btnCadastrar = document.getElementById("btn-cadastrar");
    const tabelaProdutos = document.getElementById("tabela-produtos");

    let produtos = [];
    let editandoId = null;

    // Função para renderizar a tabela
    function renderizarTabela() {
        tabelaProdutos.innerHTML = "";

        if (produtos.length === 0) {
            tabelaProdutos.innerHTML = `
                <tr>
                    <td colspan="5" class="lista-vazia">Nenhum produto cadastrado</td>
                </tr>
            `;
        } else {
            // Agora o ID exibido será o índice + 1, garantindo que comece do 1
            produtos.forEach((produto, index) => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td style="color: #1056DD; font-weight: 600;">${index + 1}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.quantidade}</td>
                    <td>R$ ${produto.preco}</td>
                    <td>
                        <button class="btn-editar" data-index="${index}" style="background-color: #FFC107; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-weight: 600; margin-right: 6px; color: #131C2A;">Editar</button>
                        <button class="btn-excluir" data-index="${index}" style="background-color: #DC3545; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-weight: 600; color: #FFFFFF;">Excluir</button>
                    </td>
                `;
                tabelaProdutos.appendChild(tr);
            });
        }
        verificarLimite();
    }

    function verificarLimite() {
        if (produtos.length >= 10) {
            btnCadastrar.disabled = true;
            btnCadastrar.style.backgroundColor = "#9CA3AF";
            btnCadastrar.style.cursor = "not-allowed";
        } else {
            btnCadastrar.disabled = false;
            btnCadastrar.style.backgroundColor = "#1056DD";
            btnCadastrar.style.cursor = "pointer";
        }
    }

    btnCadastrar.addEventListener("click", () => {
        const nome = inputNome.value.trim();
        const quantidade = inputQuantidade.value.trim();
        let preco = inputPreco.value.trim();

        if (!nome || !quantidade || !preco) {
            alert("Preencha todos os campos!");
            return;
        }

        preco = Number(preco.replace(",", ".")).toFixed(2).replace(".", ",");

        if (editandoId !== null) {
            const produto = produtos.find(p => p.id === editandoId);
            if (produto) {
                produto.nome = nome;
                produto.quantidade = quantidade;
                produto.preco = preco;
            }
            editandoId = null;
            btnCadastrar.innerText = "Cadastrar Produto";
        } else {
            if (produtos.length >= 10) return;
            // Adiciona o produto com um ID único baseado no tempo para não ter erro
            produtos.push({ id: Date.now(), nome, quantidade, preco });
        }

        inputNome.value = "";
        inputQuantidade.value = "";
        inputPreco.value = "";
        renderizarTabela();
    });

    // ... (restante do código de eventos de clique permanece igual)
    tabelaProdutos.addEventListener("click", (e) => {
        const index = e.target.getAttribute("data-index");
        if (e.target.classList.contains("btn-excluir")) {
            produtos.splice(index, 1);
            renderizarTabela();
        }
        if (e.target.classList.contains("btn-editar")) {
            const p = produtos[index];
            inputNome.value = p.nome;
            inputQuantidade.value = p.quantidade;
            inputPreco.value = p.preco;
            editandoId = p.id;
            btnCadastrar.innerText = "Salvar Alteração";
        }
    });

    renderizarTabela();
});