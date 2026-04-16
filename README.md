# RPG Game — MC322 Tarefa 3

## Descrição

Jogo de batalha em turnos baseado em *Slay the Spire*. O jogador controla um Herói que enfrenta um inimigo utilizando cartas compradas de um baralho. Esta versão introduz um sistema de **efeitos** implementado com o padrão de design **Observer**.

## Efeitos implementados

**Veneno:** ao final do turno do jogador, a entidade afligida sofre X de dano e perde 1 acúmulo. Quando os acúmulos chegam a zero, o efeito se dissipa.

**Força:** quando a entidade afligida realiza um ataque, causa X de dano adicional. É aplicado pelo inimigo em si mesmo.

**Fraqueza:** quando a entidade afligida realiza um ataque, causa X de dano a menos. É aplicada pelo jogador via carta.

## Cartas que aplicam efeitos

**Frasco de Veneno** (Custo 1): aplica 3 acúmulos de Veneno ao inimigo.

**Golpe Enfraquecedor** (Custo 1): aplica 2 acúmulos de Fraqueza ao inimigo, reduzindo seu ataque.

## Padrão Observer
**Combate** atua como **Publisher**: notifica os efeitos ativos sobre eventos do combate (**FIM_TURNO_JOGADOR**, **ATAQUE**). Cada Efeito é um **Subscriber** que reage apenas ao evento relevante para ele.

## Como compilar

```bash
javac -d bin $(find src -name "*.java")
```

## Como executar

```bash
java -cp bin App
```