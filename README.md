# RPG Game — MC322 Tarefa 4

## Descrição

Jogo de batalha em turnos baseado em *Slay the Spire*. O jogador controla um Herói que enfrenta um inimigo utilizando cartas compradas de um baralho. Esta versão introduz um sistema de **efeitos** implementado com o padrão de design **Observer**.

## Documentação implementada

Foi feita uma documentação em todo código de forma bem detalhada sobre o funcionamento do programa com o auxilio de uma LLM.

## Novas cartas implementadas

**Bola de fogo** (Custo 2): aplica 8 de dano ao inimigo.

**Espinho** (Custo 2): aplica 8 de Dano ao inimigo.

**Parede de terra** (Custo 1): aplica 10 de Defesa ao herói.

**Parede de ferro** (Custo 2): aplica 15 de Defesa ao herói.

**Golpe atordoante** (Custo 1): aplica 2 acumulo de Fraqueza ao inimigo, reduzindo seu ataque.

## Como compilar

```bash
./gradlew build
```

## Como executar

```bash
./gradlew run
```