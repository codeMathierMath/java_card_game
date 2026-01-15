# Java Card Game Simulation

This repository contains a small object-oriented Java program that
simulates a simple card game using custom `Card`, `Deck`, and `Hand`
classes.

The project was originally written as early coursework and later
reorganized to emphasize clarity of design and interaction between
objects.

---

## Overview

A standard 52-card deck is created, shuffled, and used to deal
five-card hands to a user-specified number of players. A deterministic
rule is applied to each hand to compute a score, and the player with
the highest score is declared the winner. In the case of a tie, the
winner is chosen at random among the tied players.

The game is text-based and runs entirely in the console.

---

## Game Rules

For each player's hand:

1. Find the highest-ranked card.
2. Find the lowest-ranked card.
3. Compute the difference between those ranks.
4. Add the integer representation of the highest suit in the hand.

- If a unique maximum score exists, that player wins.
- If multiple players share the maximum score, the winner is chosen
  randomly among them.

---

## Structure

```
src/
├── Game.java
├── Card.java
├── Deck.java
└── Hand.java
```

- `Card` represents an individual playing card.
- `Deck` models a standard 52-card deck and supports shuffling and dealing.
- `Hand` stores and manages a fixed-size collection of cards.
- `Game` coordinates gameplay, scoring, and winner selection.

---

## Concepts Demonstrated

- Object-oriented design and composition
- Encapsulation of state and behavior
- Interaction between multiple classes
- Array-based data structures
- Rule-based simulation and control flow
- Defensive handling of user input

The implementation prioritizes explicit logic and readability over
abstraction or performance optimization.

---

## Running the Program

Compile all source files and run the `Game` class with a single command
line argument specifying the number of players (between 2 and 10):

```bash
javac src/*.java
java Game <numPlayers>

## Possible Extensions

- Replace integer suit/rank representations with enums
- Refactor scoring logic into a separate evaluator class
- Add support for alternative scoring rules
- Improve shuffling and tie-breaking structure
- Refactor array-based storage to use Java Collections (`List`, `Map`)
  for improved flexibility and clarity

