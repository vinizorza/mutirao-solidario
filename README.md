# Mutirão Solidário - Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen)

Bem-vindo ao repositório do backend do **Mutirão Solidário**, uma plataforma dedicada a conectar voluntários a oportunidades de ação comunitária. O objetivo deste projeto é facilitar a organização e a participação em eventos de mutirões para prevenir e responder a desastres naturais, promovendo a solidariedade e a cidadania ativa.

## Visão Geral

O **Mutirão Solidário** é um aplicativo backend que permite a criação, coordenação e participação em mutirões comunitários. A plataforma foi projetada para resolver desafios logísticos, conectar voluntários e facilitar a comunicação centralizada em tempos de necessidade.

## Funcionalidades

- **Criação de Eventos:**
    - Criação e gerenciamento de atividades, especificando tipo de trabalho, local, data e número de voluntários necessários.
    - Ferramentas de coordenação para gerenciar tarefas, materiais necessários e cronogramas.

- **Participação de Voluntários:**
    - Registro e inscrição de voluntários em eventos disponíveis.
    - Notificações em tempo real sobre atualizações e lembretes de eventos.

- **Visualização de Eventos:**
    - Mapa interativo mostrando eventos de mutirão em diferentes locais.
    - Filtragem de eventos por tipo de atividade e proximidade.

- **Engajamento Comunitário:**
    - Perfis de voluntários com histórico de participação, habilidades e interesses.
    - Sistema de recompensas e certificações para incentivar a participação contínua.

- **Parcerias com Organizações:**
    - Integração com ONGs e governos para aumentar o alcance e a eficácia dos mutirões.

## Tecnologias Utilizadas

- **Java 17:** Linguagem de programação principal.
- **Spring Boot 3.3:** Framework para construção de aplicações web.
- **PostgreSQL:** Banco de dados relacional para armazenamento dos dados.
- **Swagger:** Documentação interativa para APIs RESTful.

## Pré-requisitos

Para executar este projeto, você precisará ter as seguintes ferramentas instaladas em sua máquina:

- **Java 17** ou superior
- **Maven** (para gerenciamento de dependências)
- **Docker** (opcional, para executar o PostgreSQL)
- **Git** (para clonar o repositório)

## Instalação

Siga as etapas abaixo para configurar o projeto em sua máquina local:

1. **Clone o Repositório:**

```bash
git clone https://github.com/vinizorza/mutirao-solidario.git
cd mutirao-solidario
```

2. **Instale as Dependências:**

Utilize o Maven para instalar todas as dependências do projeto:

```bash
mvn clean install
```

3. **Configure o Banco de Dados:**

Se você estiver usando o Docker, execute o seguinte comando para subir o banco de dados PostgreSQL:

```bash
docker run --name mutirao-db -e POSTGRES_DB=mutirao -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=senha123 -p 5432:5432 -d postgres
```
Configure o arquivo src/main/resources/application.properties com as informações de conexão do banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mutirao
spring.datasource.username=admin
spring.datasource.password=0000
spring.jpa.hibernate.ddl-auto=update
```

## Execução
Após configurar o banco de dados, você pode iniciar o servidor da aplicação com o seguinte comando:

```bash
mvn spring-boot:run
```
A aplicação estará rodando em http://localhost:8080.

## Documentação da API
A documentação das APIs pode ser acessada através do Swagger, disponível em:

```bash
https://mutirao-solidario-production.up.railway.app/swagger-ui/index.html
```

## Contribuição
Contribuições são bem-vindas! Siga as etapas abaixo para contribuir com o projeto:

```
Faça um fork do repositório.
Crie uma nova branch para sua feature (git checkout -b feature/nova-feature).
Commit suas alterações (git commit -m 'Adiciona nova feature').
Faça o push para a branch (git push origin feature/nova-feature).
Abra um Pull Request.
```

## Repositório Frontend:
```
https://github.com/felipelube/multirao-solidario
```

## Aplicação em funcionamento:
```
https://multirao-solidario.vercel.app/
```