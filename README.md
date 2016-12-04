
## Overview da solução implementada

O sistema foi implementado de acordo com a especificação do projeto e consiste em uma aplicação REST de backend escrita em Java 8 e uma aplicação de frontend escrita em AngularJS. O sistema não possui autenticação e nem multitenancy. A solução permite executar as funções básicas requeridas:

* Cadastro de clientes (empresas)
* Registro de impostos fiscais emitidas
* Cálculo dos impostos devidos por mês e ano de referência
* Consulta de impostos fiscais e impostos por mês e ano de referência
* Marcar o imposto como PAGO e persistir o estado

As seções a seguir descrevem as dependências e dão um overview de como configurar e executar a solução.

#### Frontend

Aplicação single page feita em AngularJS, HTML5 e CSS3, e Bootstrap. O projeto foi gerado a partir do clone do [**Angular Seed**](https://github.com/angular/angular-seed). 

**Bibliotecas e frameworks**

* [**Bootstrap**](http://getbootstrap.com/)
* [**Angular JS**](http://angularjs.org/)

#### Backend

Aplicação escrita em Java 8 e utiliza Jersey e utilizando a implementação JPA do Hibernate. O banco de dados escolhido é PostgreSQL 9.4. O código segue o padrão MVC e as APIs se comunicam via mensagens JSON.

**Bibliotecas e frameworks**

* [**Jersey**](https://jersey.java.net)
* [**Jetty**](http://www.eclipse.org/jetty/)
* [**Hibernate**](http://hibernate.org)

## Setup do sistema

#### Requisitos

Antes de tudo, a máquina esteja preparada com os seguintes softwares:

* [**JDK Java 8**](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [**NPM**](https://www.npmjs.com/package/npm)
* [**Bower**](https://bower.io/)
* [**PostgreSQL 9.4**](https://www.postgresql.org/download/)
* [**Maven 3.x**](https://maven.apache.org/download.cgi)

#### Repositório

you@machine:~$ git clone https://github.com/pericles-gumerato/fullstack-java-teste.git

you@machine:~$ cd fullstack-java-teste

#### Database

##### Configuração de usuário

Por padrão, a aplicação utiliza o usuário/password contabilizei/contabilizei para criação do schema. Caso deseje criar este mesmo usuário, existe um arquivo .sql no repositório em backend/init_user.sql. Ao executá-lo como administrador, um usuário 'contabilizei' com senha 'contabilizei' é criado no banco de dados.

O schema é criado automaticamente utilizando-se o [**Flyway DB**](https://flywaydb.org/), basta executar os comandos abaixo:

Caso deseje alterar o usuário de criação do schema, basta editar o arquivo db-schema/src/main/resources/flywayConfig.properties com o usuário e o endereço do servidor.

##### Criação do schema

Basta executar os comandos a seguir:

you@machine:~$ cd fullstack-java-teste

you@machine:~$ cd backend/db-schema

you@machine:~$ mvn clean install flyway:migrate


#### Backend

##### Configuração
Após configurar o banco de dados, a aplicação de backend deve ser configurada com a porta do servidor (padrão 8080) e os dados de conexão com banco:

* A porta do servidor deve ser configurada no arquivo backend/app/src/main/resources/config.properties
* Os parâmetros de configuração com o banco devem ser configurados no arquivo backend/app/src/main/resources/META-INF/persistence.xml


##### Execução

you@machine:~$ cd fullstack-java-teste

you@machine:~$ cd backend

you@machine:~$ mvn clean install

you@machine:~$ cd app

you@machine:~$ mvn exec:java


#### Frontend

##### Configuração

A aplicação possui duas configurações básicas: o host/porta do backend e a porta em que o servidor local sobe:

* A configuração do host do backend está em frontend/app/app.js, nas linhas 13 e 14. Caso deseje alterá-los, basta editar este arquivo.
* A configuração da porta de execução do servidor está em frontend/package.json, na linha 26.

##### Execução

you@machine:~$ cd fullstack-java-teste

you@machine:~$ cd frontend

you@machine:~$ npm start

Após inicializar o ambiente, o npm deve executar a aplicação (por padrão, a aplicação sobe na porta 8000).



## Usando a aplicação

Por padrão, a aplicação pode ser acessada em http://localhost:8080 e é uma single-page app simples. Existe uma barra onde a funcionalidade desejada deve ser selecionada e a tela correspondente é mostrada.

Um fluxo típico de uso poderia ser:

1. Cadastra cliente
2. Cadastra notas fiscais emitidas
3. Lista (verifica) notas fiscais cadastradas
4. Calcula impostos
5. Lista os impostos
6. Na tela de listagem de impostos, marca os impostos como pago


## Reposta Bonus

**Pergunta** : Se voce fosse utilizar esse sistema comercialmente, que alterações vc faria para escalar e/ou facilitar a vida do usuario? OBS: Voce pode descrever isso aqui ou mostrar na implementação.

Divido a resposta em duas partes, a do sistema e a do usuário.

##### Sistema

O sistema deveria possuir mecanismos úteis para escalar, como cache local e distribuído onde fosse possível/necessário para diminuir os acessos ao banco. Uma ideia seria montar uma (ou mais) aplicação separada da aplicação principal para o cálculo de impostos (função que talvez fosse assíncrona), de forma que não houvesse um impacto direto/imediato na aplicação quando o usuário pedisse para o sistema calcular seus impostos. Para escalabilidade, dividir o sistema em micro serviços com comunicação assíncrona entre eles é o melhor approach. Além disso, certamente parte dos dados pode ser armazenado em um banco de dados não relacional. Se a modelagem for bem feita, o sistema se tornaria muito mais performático.

Para ser utilizado comercialmente, seria necessário implementar testes unitários e de integração em todo o sistema para evitar features quebradas e ter mais segurança nas atualizações e na construção de novas funcionalidades.

##### Usuário

A interface está muito crua e falta um bom tratamento de erros de fluxo. O layout da interface não ficou bom e pode ser muito melhorado. Acredito que uma integração com um sistema de e-mail lembrando que o usuário possui impostos prestes a vencer é uma ótima feature. Além disso deve ser possível cadastrar informações de contato dos clientes.
