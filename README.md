# gp-42
GP-42 clone - A two-player racing game

## Description
This is a Java implementation of the GP-42 racing game. Two players (BLACK and WHITE) race around an oval track, competing for the best time and highest score.

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
### Black Player (Car)
- W: Accelerate
- S: Brake/Reverse
- A: Turn Left
- D: Turn Right

### White Player (Car)
- Arrow Up: Accelerate
- Arrow Down: Brake/Reverse
- Arrow Left: Turn Left
- Arrow Right: Turn Right

### Other Controls
- SPACE: Pause/Resume
- R: Restart (when game over)

## Gameplay
- Race around the oval track
- Stay on the track to earn points
- Points are awarded continuously while racing on the track
- Hitting the track boundaries will slow you down
- Colliding with the other car will slow both cars down
- First player to reach 100 points achieves "Extended Play"
- Game tracks time and scores for both players

## Features
- Two-player simultaneous racing
- Realistic car physics (acceleration, turning, friction)
- Oval race track with boundaries
- Score tracking for both players
- Collision detection (track boundaries, car-to-car)
- Extended play bonus at 100 points
- Pause functionality
- Top-down racing view

## Racing Physics
- Cars accelerate gradually when pressing forward
- Friction slows down cars when not accelerating
- Turning works best at higher speeds
- Hitting walls causes bounce-back effect
- Car-to-car collisions affect both vehicles

