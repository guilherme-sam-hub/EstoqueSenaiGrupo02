const email = document.getElementById("inputEmail").value;
const senha = document.getElementById("inputSenha").value;
const apiURL = "";


async function fazerLogin(){
    
    try{
        const resposta = await fetch(apiURL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email:email,
                senha:senha
            })
        });
        
        const dados = await resposta.json();

        if(resposta.ok){
            window.location.href = "/templates/estoque/estoque.html";
        }else{
            alert(dados.mensagem);
        }
    }catch(erro){
        console.error("Erro ao realizar login: " + erro);
        alert("Não foi possível conectar com o servidor!");
    }
    
}