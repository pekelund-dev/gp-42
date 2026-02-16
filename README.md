# gp-42
GP-42 clone - A two-player arcade-style game

## Description
This is a Java implementation of the GP-42 arcade game. Two players (BLACK and WHITE) compete by navigating the game field, collecting food, and avoiding collisions with walls, themselves, and each other.

## Requirements
- Java 17 or higher
- Maven 3.x

## Building
```bash
mvn clean compile
```

## Running
```bash
mvn exec:java
```

## Controls
### Black Player
- W: Move Up
- S: Move Down
- A: Move Left
- D: Move Right

### White Player
- Arrow Up: Move Up
- Arrow Down: Move Down
- Arrow Left: Move Left
- Arrow Right: Move Right

### Other Controls
- SPACE: Pause/Resume
- R: Restart (when game over)

## Gameplay
- Collect yellow food items to increase your score
- Each food item is worth 10 points
- Avoid hitting the walls (dotted border)
- Avoid hitting your own trail
- Avoid hitting the other player's trail
- First player to reach 100 points achieves "Extended Play"
- Game tracks time and scores for both players

## Features
- Two-player simultaneous gameplay
- Score tracking for both players
- Game timer
- Collision detection (walls, self, other player)
- Food collection system
- Extended play bonus at 100 points
- Pause functionality
- Game over and restart capability

