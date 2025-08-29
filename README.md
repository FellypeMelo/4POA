# Agenda de Contatos

Uma aplicação Java simples para gerenciar contatos pessoais.

## Funcionalidades

- **Adicionar contato**: Incluir novo contato com nome, telefone e email
- **Remover contato**: Excluir contato existente por nome
- **Editar contato**: Modificar informações de contatos existentes
- **Buscar contato**: Localizar contato específico por nome
- **Listar contatos**: Visualizar todos os contatos cadastrados

## Estrutura do Projeto

```
Agenda/
├── Class/
│   └── Agenda.java          # Classe principal para representar contatos
├── Controller/
│   └── AgendaController.java # Controlador para gerenciar operações
├── Main/
│   └── Main.java            # Interface principal do usuário
└── agenda.txt               # Arquivo de armazenamento dos dados
```

## Como Executar

1. Compile todos os arquivos Java:
   ```bash
   javac Agenda/Class/Agenda.java
   javac Agenda/Controller/AgendaController.java
   javac Agenda/Main/Main.java
   ```

2. Execute a aplicação:
   ```bash
   java Agenda.Main.Main
   ```

## Formato dos Dados

Os contatos são armazenados no arquivo `agenda.txt` no formato:
```
nome|telefone|email
```

## Exemplo de Uso

1. Escolha a opção "1" para adicionar contato
2. Digite o nome do contato
3. Digite o telefone
4. Digite o email
5. O contato será salvo automaticamente

## Tecnologias Utilizadas

- Java 8+
- Sistema de arquivos para persistência de dados
- Arquitetura MVC (Model-View-Controller)
