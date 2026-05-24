# VetLink - Sprint 1 DevOps & Cloud Computing

## Descrição do Projeto

O VetLink é uma aplicação desenvolvida em Java Spring Boot com foco no gerenciamento de pets através de operações CRUD (Create, Read, Update e Delete).

A solução foi conteinerizada utilizando Docker e implantada em uma Máquina Virtual Linux na Microsoft Azure, seguindo os conceitos de DevOps, Cloud Computing e Infraestrutura como Código.

A aplicação utiliza banco de dados H2 executando em container Docker separado, com persistência de dados através de volume nomeado.

---

## Benefícios para o Negócio

- Centralização das informações dos pets
- Facilidade no gerenciamento de cadastros
- Arquitetura escalável em nuvem
- Implantação automatizada utilizando Azure CLI
- Facilidade de manutenção com containers Docker
- Persistência de dados utilizando volumes Docker
- Separação entre aplicação e banco de dados
- Facilidade de deploy em diferentes ambientes

---

## Arquitetura da Solução

A solução foi implantada utilizando os seguintes componentes:

- Microsoft Azure
- Máquina Virtual Linux Ubuntu 24.04
- Docker Engine
- Container da aplicação Spring Boot
- Container do Banco H2
- Docker Network
- Docker Volume
- Azure NSG (Network Security Group)

Fluxo da aplicação:

Usuário → Azure VM → Container API → Container Banco H2

---

## Rotas da API

### GET
```http
GET /pets
```

### POST
```http
POST /pets
```

Exemplo:
```json
{
  "nome":"Rex",
  "raca":"Golden",
  "idade":5
}
```

### PUT
```http
PUT /pets/{id}
```

### DELETE
```http
DELETE /pets/{id}
```

---

## Instalação da Solução (How To)

### Clonar projeto

```bash
git clone https://github.com/brunoferr10/Devops.git
```

### Entrar na pasta

```bash
cd Devops
```

### Build da imagem Docker

```bash
docker build -t vetlink-app .
```

### Criar network Docker

```bash
docker network create vetlink-network
```

### Criar volume Docker

```bash
docker volume create h2-vetlink-data
```

### Executar container H2

```bash
docker run --name h2-vetlink -d \
--network vetlink-network \
-p 8082:8082 \
-p 9092:9092 \
-v h2-vetlink-data:/opt/h2-data \
oscarfonts/h2
```

### Executar aplicação

```bash
docker run --name vetlink-container -d \
--network vetlink-network \
-p 8080:8080 \
vetlink-app
```

### Testar aplicação

```bash
http://localhost:8080/pets
```

---

## Dockerfile

```dockerfile
FROM maven:3.9.9-eclipse-temurin-17-alpine

RUN apk update

RUN adduser -h /home/usrapp -s /bin/sh -D userapp

WORKDIR /app

COPY . .

RUN mvn package -DskipTests

RUN chown -R userapp:userapp /app

USER userapp

EXPOSE 8080

CMD ["./mvnw","spring-boot:run"]
```

---

## Script Azure CLI

```bash
#!/bin/bash

RESOURCE_GROUP="rg-vetlink"
LOCATION="canadacentral"
VM_NAME="vm-vetlink"

az group create \
--name $RESOURCE_GROUP \
--location $LOCATION

az vm create \
--resource-group $RESOURCE_GROUP \
--name $VM_NAME \
--image Ubuntu2404 \
--size Standard_B2ats_v2 \
--authentication-type password \
--admin-username azureuser \
--admin-password Vetlink@2026

az vm open-port \
--resource-group $RESOURCE_GROUP \
--name $VM_NAME \
--port 22

az vm open-port \
--resource-group $RESOURCE_GROUP \
--name $VM_NAME \
--port 8080

az vm open-port \
--resource-group $RESOURCE_GROUP \
--name $VM_NAME \
--port 9092
```

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Docker
- Microsoft Azure
- H2 Database
- GitHub

---

## Integrantes

- Bruno Ferreira
