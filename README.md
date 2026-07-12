# RPG Game — MC322 Tarefa 5

## Descrição

Jogo de batalha em turnos baseado em *Slay the Spire*. O jogador controla um Herói que navega por um mapa de batalhas representado como uma árvore, escolhendo seu caminho após cada vitória. A vida e o baralho são mantidos entre batalhas, mas efeitos e energia reiniciam a cada combate.

## Sistema de mapa

O mapa é organizado como uma árvore de nós, onde cada nó representa uma batalha:

```
[Rato Gigante] --> [Goblin]     --> [Dragão (final)]
               --> [Lobisomem] --> [Lich (final)]
```

Após cada vitória, o jogador escolhe qual caminho seguir. O jogo termina em vitória ao derrotar um inimigo em um nó final, ou em derrota se o herói morrer.

## Efeitos implementados

**Veneno:** ao final do turno do jogador, a entidade afligida sofre X de dano e perde 1 acúmulo. Quando os acúmulos chegam a zero, o efeito se dissipa.

**Força:** quando a entidade afligida realiza um ataque, causa X de dano adicional. É aplicado pelo inimigo em si mesmo.

**Fraqueza:** quando a entidade afligida realiza um ataque, causa X de dano a menos. É aplicada pelo jogador via carta.

## Cartas que aplicam efeitos

**Frasco de Veneno** (Custo 1): aplica 3 acúmulos de Veneno ao inimigo.

**Golpe Enfraquecedor** (Custo 1): aplica 2 acúmulos de Fraqueza ao inimigo, reduzindo seu ataque.

**Golpe Atordoante** (Custo 1): aplica 2 acúmulos de Fraqueza ao inimigo, reduzindo seu ataque.

## Testes automatizados

Os testes estão em `src/test/java/` e cobrem as classes `Heroi`, `Baralho` e `NoMapa`. Para executar e gerar o relatório de cobertura:

```bash
./gradlew test
```

O relatório de cobertura é gerado em `build/reports/jacoco/test/html/index.html`.

## Como compilar

```bash
./gradlew build
```

## Como executar

```bash
./gradlew run
```