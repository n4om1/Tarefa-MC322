# RPG GAME

## Introdução

O jogo implementado tem como inspiração jogos de RPG online que utilizam sistemas de cartas para batalhar contra inimigos, usando um herói escolhido. Criamos algo parecido com o jogo *Slay the Spire*.

## Como baixar o repositório, compilar e executar o jogo

Primeiramente, é necessário criar um clone do repositório para depois compilá-lo. Para isso, escreva em seu terminal:

* `git clone git@github.com:n4om1/Tarefa-MC322.git`

Você também pode obter o URL clicando no botão verde escrito "Code" e copiando-o da aba SSH.

Com esse comando, será criada uma pasta com uma cópia de todos os arquivos do repositório. Depois disso, é possível entrar nessa pasta com o comando `cd`:

* `cd repo.git`

Para compilar e executar o programa, use os seguintes comandos:

* `javac -d bin $(find src -name "*.java")`
* `java -cp bin App`

E o jogo já estará pronto para ser jogado.

