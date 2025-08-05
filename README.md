# Controle de Produtos

Uma API RESTful completa para o gerenciamento de produtos. Esta aplicação permite a realização de operações CRUD (Criar, Consultar Dados) de produtos, 
utilizando um banco de dados H2 em memória para agilizar o desenvolvimento e os testes.

---

## Tecnologias e Versões

* **Java**: 17
* **Spring Boot**: 3.5.4
* **Gradle**: 8.14.3
* **Spring Data JPA**: Para abstração e acesso a dados.
* **H2 Database**: Banco de dados relacional em memória.
* **Flyway**: Ferramenta de migração de banco de dados.
* **Spring Web**: Para a criação dos endpoints da API RESTful.
* **Springdoc-OpenAPI UI**: Para documentação automática da API (Swagger UI).
* **IntelliJ IDEA**: Ambiente de desenvolvimento integrado (IDE).

---

## Como Rodar a Aplicação

Siga os passos abaixo para clonar e executar o projeto localmente.

### Pré-requisitos

Certifique-se de ter o **Java 17** e o **Gradle 8.14.3** instalados na sua máquina.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/tatianenm/ControleDeProdutos.git
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd [nome-do-seu-projeto]
    ```

3.  **Execute a aplicação usando Gradle:**
    ```bash
    ./gradlew bootRun
    ```
    *(No Windows, use `gradlew.bat bootRun`)*

A aplicação será iniciada na porta `8080`.  O Flyway executará as migrações automaticamente no banco de dados H2 na inicialização.
src/main/resources/
└── db/
└── migration/
├── V1__CREATE_TABLE_PRODUTO.sql

---

## Endpoints e Ferramentas Úteis

A aplicação oferece algumas ferramentas para auxiliar no desenvolvimento e na interação com a API.

* **API RESTful (Endpoints):**
    * `GET /api/produtos`: Retorna a lista de todos os produtos, nomeProduto ou pelo preço(min ou max).
    * `POST /api/produtos`: Cria um novo produto. O corpo da requisição deve ser um JSON.
    

* **Console do H2 Database:**
    * URL: **http://localhost:8080/h2-console**
    * Você pode usar esta interface para visualizar e gerenciar o banco de dados em tempo real. A URL de conexão JDBC
      e as credenciais padrão estarão disponíveis no log da aplicação.
    * username: sa,  password: 123
    * **Importante:** Os dados são perdidos quando a aplicação é reiniciada.

* **Documentação da API (Swagger UI):**
    * URL: **http://localhost:8080/swagger-ui.html**
    
  ---

## Exemplo de Requisição

Para criar um novo produto, envie uma requisição `POST` para `http://localhost:8080/v1/produto` com o seguinte corpo em formato JSON:

```json
{
  "nomeEmpresa": "string",
  "origem": "string",
  "tipo": "string",
  "preco": 0,
  "quantidade": 0,
  "nomeProduto": "string"
}
