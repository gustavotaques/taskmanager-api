# 📋 Task Manager API

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)

## 📖 Sobre o Projeto

O **Task Manager API** é uma solução de backend desenvolvida para gerenciamento de tarefas (To-Do List), construída com foco em **Escalabilidade**, **Containerização** e **Boas Práticas de DevOps**.

A principal proposta deste projeto foi criar um ambiente de desenvolvimento agnóstico: graças ao uso de **Docker Multi-Stage Build**, não é necessário ter o Java (JDK) ou Maven instalados na máquina host para rodar o projeto. O Docker se encarrega de baixar as dependências, compilar o código e gerar a imagem final otimizada.

### 🎯 Principais Funcionalidades (CRUD)
* **Create:** Cadastro de novas tarefas.
* **Read:** Listagem de todas as tarefas.
* **Update:** Atualização de descrição ou status (concluído/pendente).
* **Delete:** Remoção de tarefas.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA & Hibernate
* **Banco de Dados:** PostgreSQL 15
* **Infraestrutura:** Docker & Docker Compose
* **Segurança:** Gerenciamento de credenciais via Variáveis de Ambiente (`.env`)

---

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para subir a aplicação do zero.

### Pré-requisitos

- Ter o **Docker** e o **Docker Compose** instalados e rodando
- (Opcional) **Git** para clonar o repositório

### Passo 1: Clone o repositório

Execute os comandos abaixo no seu terminal:

```bash
git clone https://github.com/SEU_USUARIO/taskmanager-api.git
cd taskmanager-api
```

### Passo 2: Configure as Variáveis de Ambiente

Por segurança, as senhas do banco não são enviadas para o Git. Crie um arquivo chamado `.env` na raiz do projeto (no mesmo local onde está o arquivo `docker-compose.yml`) e insira o seguinte conteúdo:

```env
DB_NAME=taskdb
DB_USER=usuario
DB_PASSWORD=senha_secreta
DB_PORT=5432
```

### Passo 3: Suba o Ambiente (Build & Run)

Execute o comando abaixo. O Docker irá baixar as dependências, compilar o arquivo `.jar` (Build Stage), criar as imagens e subir os containers da API e do Banco de Dados.

```bash
docker-compose up --build
```

Aguarde até ver a mensagem **"Started TaskmanagerApplication"** no terminal.

## Guia de Validação (Como Testar)

A API estará rodando no endereço: `http://localhost:8080`

Você pode usar o **Postman**, **Insomnia** ou **cURL** para validar os endpoints.

### Criar Tarefa (Método POST)

Cria uma nova tarefa no banco de dados.

- **URL**: `http://localhost:8080/tasks`
- **Corpo da requisição (JSON)**:

```json
{
  "description": "Estudar arquitetura de Microsserviços"
}
```

- **Status Esperado**: `200 OK` (Retorna o objeto com ID)

### Listar Tarefas (Método GET)

Verifica se a tarefa foi salva.

- **URL**: `http://localhost:8080/tasks`
- **Status Esperado**: `200 OK` (Retorna uma lista de tarefas)

### Atualizar Tarefa (Método PUT)

Marca uma tarefa como concluída ou altera a descrição. Substitua `{id}` pelo ID da tarefa (exemplo: `1`).

- **URL**: `http://localhost:8080/tasks/1`
- **Corpo da requisição (JSON)**:

```json
{
  "description": "Estudar arquitetura de Microsserviços",
  "completed": true
}
```

- **Status Esperado**: `200 OK` (Retorna o objeto atualizado)

### Deletar Tarefa (Método DELETE)

Remove a tarefa do banco.

- **URL**: `http://localhost:8080/tasks/1`
- **Status Esperado**: `204 No Content`

## Estrutura de Arquivos

Abaixo, a explicação dos arquivos cruciais de infraestrutura:

```
/
├── Dockerfile              # Configuração Multi-Stage: Build com Maven e Run com JRE
├── docker-compose.yml      # Orquestração dos serviços App e Postgres e Redes
├── .env                    # Você deve criar este arquivo para guardar segredos e senhas do banco
├── .gitignore              # Impede o vazamento de arquivos sensíveis como .env e a pasta target
└── src/                    # Código fonte Java/Spring Boot
```