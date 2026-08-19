# EstoqueSenaiGrupo02
# API de Gerenciamento de Estoque - SENAI (Grupo 02)

Este projeto consiste em uma aplicação backend desenvolvida em Java com Spring Boot para o gerenciamento e controle de estoque.
A aplicação disponibiliza uma API RESTful para operações de CRUD (Criar, Ler, Atualizar e Excluir) de **Usuários** e **Produtos**,
com persistência de dados em MySQL.

Além da API, o projeto inclui um frontend funcional (HTML/CSS/JS) com tela de login e gerenciamento de estoque,
que consome os endpoints da API para validação e manipulação de dados.

## Boas Práticas Implementadas

Além dos requisitos obrigatórios, o grupo optou por aplicar padrões de projeto e boas práticas de mercado
para garantir um código limpo e escalável:

- ** Uso Data Transfer Objects (DTO's):**
  Em vez de expor diretamente as entidades do banco de dados (`@Entity`) nas requisições e respostas da API,
  utilizamos `ProdutoRequestDto`, `ProdutoResponseDto`, `UsuarioRequestDto` e `UsuarioResponseDto`.
  Isso garante o desacoplamento entre a estrutura do banco de dados e a API, além de aumentar a segurança
  ao evitar a exposição acidental de dados sensíveis.

- ** Separação de Responsabilidades (SRP):**
  A aplicação segue o padrão em camadas:
    - **Controllers:** Responsáveis apenas por receber as requisições HTTP, validar os dados de entrada (`@Valid`)
      e retornar os status codes adequados.
    - **Services:** Camada onde residem as regras de negócio, mantendo os Controllers limpos e focados apenas no tráfego de dados.
    - Autenticação via **LoginService** para evitar sobrecarga de responsabilidades em **UsuarioService**
    - **Repositories:** Focados exclusivamente na comunicação com o banco de dados via Spring Data JPA.



## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x** (Spring Web, Spring Data JPA, Spring Validation)
- **MySQL** (Banco de dados relacional)
- **Maven** (Gerenciamento de dependências)
- **Postman** (Testes de requisições HTTP)
- **Git e GitHub** (Versionamento de código)
- **HTML5, CSS3 e JavaScript (Fetch API)** (Frontend da aplicação)

---

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
- [MySQL Server](https://dev.mysql.com/downloads/mysql/) (Workbench, DBeaver ou similar)
- [Maven](https://maven.apache.org/download.cgi) (ou utilize o wrapper do projeto)
- [Postman](https://www.postman.com/) ou extensão Thunder Client (VS Code)

---

## ⚙️ Configuração do Banco de Dados

1. Abra o seu cliente MySQL e crie o banco de dados:

   ```sql
   CREATE DATABASE estoque_senai;
   ```

2. Configure as credenciais de acesso no arquivo application.yml do projeto:

   ```properties
   spring:
datasource:
url: jdbc:mysql://localhost:3306/estoque_senai?createDatabaseIfNotExist=true&serverTimezone=UTC
username: root
password: sua_senha_aqui # <-- TROQUE PELA SUA SENHA REAL DO MYSQL
jpa:
hibernate:
ddl-auto: update
show-sql: true
properties:
hibernate:
dialect: org.hibernate.dialect.MySQLDialect

server:
port: 8080
   ```

---

## ▶️ Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/guilherme-sam-hub/EstoqueSenaiGrupo02.git
   ```

2. Acesse a pasta do projeto:

   ```bash
   cd EstoqueSenaiGrupo02
   ```

3. Execute o projeto com Maven:

   ```bash
   mvn spring-boot:run
   ```

4. A aplicação estará disponível em:

   ```
   http://localhost:8080
   ```

---

## Endpoints da API

### Usuários

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/usuarios` | Lista todos os usuários |
| `GET` | `/usuarios/{id}` | Busca um usuário por ID |
| `POST` | `/usuarios` | Cadastra um novo usuário |
| `PUT` | `/usuarios/{id}` | Atualiza um usuário existente |
| `DELETE` | `/usuarios/{id}` | Remove um usuário |



### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca um produto por ID |
| `POST` | `/produtos` | Cadastra um novo produto |
| `PUT` | `/produtos/{id}` | Atualiza um produto existente |
| `DELETE` | `/produtos/{id}` | Remove um produto |


## Frontend

### Funcionalidades:

-  **Tela de Login** – Validação de credenciais via API
-  **Dashboard de Estoque** – Visualização dos produtos cadastrados
-  **Cadastro de Produtos** – Formulário para adicionar novos itens
-  **Edição de Produtos** – Atualização de dados dos produtos
-  **Exclusão de Produtos** – Remoção de itens do estoque

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais no curso do **SENAI**.

---

## 🤝 Contato

Em caso de dúvidas ou sugestões, entre em contato com os integrantes do grupo através dos perfis do GitHub listados acima.


# 1. Garante que você está na branch principal
git switch main

# 2. Restaura os 3 arquivos específicos pegando a versão da branch 'desenvolvimento'
# (Nota: Ajuste o caminho 'src/main/resources/static/' se os seus arquivos estiverem em outra pasta, como na raiz do projeto)
git restore --source=desenvolvimento -- src/main/resources/static/Estoque.html src/main/resources/static/estoque.js src/main/resources/static/index.html

# 3. Adiciona, commita e envia para a main no GitHub
git add .
git commit -m "feat: sincroniza arquivos de frontend (Estoque.html, estoque.js, index.html) na main"
git push origin main

