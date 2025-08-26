# 🏁 Jogo de Corrida de Gatos - Teste de Threads 🐱

Este é um jogo educativo desenvolvido para demonstrar o uso de threads em Java, baseado no conceito de corrida de carros, mas adaptado para gatos com animações simples.

## 🎯 Objetivo

Demonstrar o funcionamento de threads através de um jogo interativo onde três gatos competem em uma corrida, cada um executando em sua própria thread.

## 🚀 Como Executar

1. **Compilar o projeto:**
   ```bash
   javac -cp . Threads/Class/GatoThread.java
   javac -cp . Threads/src/GeradorImagens.java
   javac -cp . Threads/src/GeradorPista.java
   javac -cp . Threads/Main/JanelaCorrida.java
   javac -cp . Threads/Main/Main.java
   ```

2. **Executar o jogo:**
   ```bash
   java -cp . Threads.Main.Main
   ```

## 🎮 Como Jogar

### Controles:
- **🚀 Iniciar Corrida**: Inicia a corrida com os três gatos
- **⏹️ Parar Corrida**: Para a corrida em andamento
- **🔄 Reiniciar**: Limpa a pista para uma nova corrida

### Funcionalidades:
- Cada gato corre em sua própria thread
- Movimento aleatório com pequenas animações
- Sistema de posicionamento automático
- Interface gráfica colorida e intuitiva

## 🏗️ Estrutura do Projeto

```
Threads/
├── Class/
│   └── GatoThread.java      # Classe principal com implementação de threads
├── Main/
│   ├── JanelaCorrida.java   # Interface gráfica do jogo
│   └── Main.java            # Classe principal para execução
├── src/
│   ├── imagens/             # Pasta com imagens reais dos gatos
│   │   ├── gato1.jpg
│   │   ├── gato2.jpg
│   │   └── gato3.jpg
│   ├── GeradorImagens.java  # Carregador de imagens dos gatos
│   └── GeradorPista.java    # Gerador da pista de corrida
└── README.md                # Este arquivo
```

## 🔧 Características Técnicas

### GatoThread.java
- Implementa `Runnable` para execução em threads
- Controle de movimento aleatório
- Sistema de posicionamento automático
- Gerenciamento de estado da corrida

### JanelaCorrida.java
- Interface gráfica Swing
- Controle de botões e eventos
- Gerenciamento da pista de corrida
- Sistema de animação simples

## 🎨 Animações e Imagens

- **Imagens reais dos gatos** carregadas da pasta `src/imagens/`
- Movimento horizontal progressivo com threads
- Pequenas variações verticais para simular movimento natural
- Velocidades variáveis para cada gato
- Sistema de colisão com bordas da pista
- Pista visual com faixas, decorações e linha de chegada

## 🏆 Sistema de Pontuação

- Posicionamento automático baseado na ordem de chegada
- Mensagens informativas para cada colocação
- Contador global de posições

## 🐛 Solução de Problemas

Se encontrar problemas de compilação:
1. Verifique se está no diretório correto
2. Certifique-se de que todas as dependências estão disponíveis
3. Use Java 8 ou superior

## 📚 Conceitos Demonstrados

- **Threads**: Execução concorrente de tarefas
- **Swing**: Interface gráfica em Java
- **Eventos**: Tratamento de ações do usuário
- **Sincronização**: Controle de estado entre threads
- **Randomização**: Movimento imprevisível dos gatos

## 🎯 Aplicações Educativas

Este projeto é ideal para:
- Aprender sobre threads em Java
- Entender programação concorrente
- Estudar interfaces gráficas Swing
- Praticar programação orientada a objetos

---

**Desenvolvido para fins educativos** 🎓
