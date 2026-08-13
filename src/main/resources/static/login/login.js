const email = document.getElementById("inputEmail");
const senha = document.getElementById("inputSenha");
const apiURL = "";


async function fazerLogin(){
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
    
}