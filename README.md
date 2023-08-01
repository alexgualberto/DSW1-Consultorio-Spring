# DSW1 - Agenda Médica Spring

## Diretórios com arquivos Java

- config: mudar as URLs para poder as páginas, além de permitir certas URLs para alguns usuários.
- controller: lógica de acesso ao banco de dados, i.e., regras de negócios (comparado aos outros, esse é o que vai dar mais trabalho, mas não deve ser tanto assim).
- conversor: converte o ID de String para Long.
- dao: herdado da superclasse "CrudRepository".
- domain: classes com as especificações do banco de dados sobre os atributos, além dos métodos get e set.
- security: ainda tenho que ver o que faz.
- service
    - impl: implementação da classe de serviço usando os métodos da classe dao.
    - spec: interface do serviço.
- validation: verifica se a chave (CPF ou CMR) é único.
- aplicação (para preencher o banco de dados): salva dados no banco de dados por meio de uma classe em vez de um arquivo .sql.

## Diretórios com arquivos HTML

- admin: menus de CRUD.
- consulta: listagem e cadastro.
- medico: listagem e cadastro.
- paciente: listagem e cadastro.
- fragments: adaptar os arquivos da livraria.
- Outros arquivos dentro de "templates": também adaptar.

### Outros arquivos

- Arquivos .properties.
- Diretório "static"
    - Arquivo CSS.
    - Imagens.

## Envio de email

- Entender e implementar o envio de email automático que ficou faltando no último trabalho.
