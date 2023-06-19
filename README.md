<div align="center">
 <h1>Desafio Klok - Projeto Prático</h1>
 <h2>Estágio em Desenvolvimento de Software<h2>
</div>

### :warning: Cuidado, branch main (De produção)

## :books: Sobre o Desafio:

- Implementar uma API RESTFul capaz de:
  - Gerenciar vendas (cadastrar, atualizar e cancelar); e
  - Gerar cobranças para as vendas através de um job/scheduler configurável.
- Implementar uma segunda API RESTFul que deve:
  - Receber pagamentos via REST;
  - Se comunicar com o serviço de vendas via mensageria após o recebimento do pagamento para que a respectiva cobrança seja finalizada.
- As APIs deverão ser executadas através de containers Docker;
- Cada API deve ter seu próprio banco de dados e ambos deves ser executados via containers Docker;
- Os containers Docker devem ser orquestrados via docker-compose;
- Testes de unidade devem ser implementados;
- README com as especificações do projeto e os passos necessários para execução e testes.

:exclamation:Veja um vídeo que produzi para explicar melhor sobre o projeto [aqui](https://www.youtube.com/watch?v=s49vWQEGd9o)<br>
:exclamation:Consulte o diagrama de classes do projeto [aqui](https://drive.google.com/file/d/1uycFOMyOjZU0vY1rxKQqLuwk2WsG35oe/view)<br>

## :hammer_and_wrench: Das Tecnologias:

- Java 17;
- Maven;
- Spring Boot;
- Spring Data;
- Spring MVC;
- Hibernate Validation;
- Lombok;
- RabbitMQ;
- PostgreSQL;
- Docker;
- Git.
- Spring Test (JUnit);
- Sringdoc OpnAi (Swagger);

## :play_or_pause_button: Executando o projeto:

Se quiser executar o projeto localmente é bem simples, porém, é necessário que tenha algumas ferramentas instaladas.

:exclamation:Baixe e instale o git clicando [aqui](https://git-scm.com/downloads).<br>
:exclamation:Baixe e instale o docker clicando [aqui](https://www.docker.com/products/docker-desktop/).<br>

Após instalar os programas solicitados (ou se já tiver os mesmos) siga os seguintes passos:

1. Clone o referido repositório, executando o seguinte comando no terminal bash do git:
```sh
   git clone https://github.com/IriedsonSouto/desafio-klok-estagio.git
```

2. Em seguida, para fazer o build e subir as imagens docker, abra um terminal na raiz da pasta clonada e execute o seguinte comando:
```sh
   docker-compose up
```

3. Em seguida abra o RabbitMQ no navegador e realize a importação dos dados encontrados no diretório "rabbitmq/data".
*Para importar basta logar com o usuário e senha 'guest' e no rodapé da home page encontrará a opção*

4. Também será necessário importar os dados do KeyCloak presentes no diretório "keycloak/data".
*Para importar basta logar com o usuário e senha 'admin' na opção de criar novo realm escolher para importar arquivo*

5. Após realizar essas pré configurações já será possível consumir a api na sua aplicação de requisição rest preferida, para ajudar deixei exportado uma base de consultas do Insomnia.

:exclamation:Consulte um vídeo explicativo sobre a etapa 3, 4 e 5 [aqui](https://www.youtube.com/watch?v=cstGRPQeYDE).<br>

## :pushpin: ATENÇÃO:

O KeyCloak é o responsável por gerenciar a segurança da aplicação, cada vez que o token vencer deverá consultar um novo token no endpoint especificado.

O projeto está funcionando, porém, está havendo um erro na comunicação das imagens descritas no docker-compose, então se necessário execute localmente com sua IDE JAVA e modifique as variáveis necessárias no arquivo application.yml.

Cada api tem a documentação do swagger disponível, porém, só é possível consultar diretamente pelo endereço do micro serviço.


