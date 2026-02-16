# GP-42 Racing Game - Implementation Details

## Game Type: Top-Down Racing

This is a **racing game** where two cars compete on an oval track, NOT a snake/trail game.

## Reference Image Analysis

The image clearly shows:
- Oval race track with dotted boundary lines
- Two racing cars (BLACK and WHITE) at different positions
- Cars are small sprites/icons, not trails
- Track layout resembles classic top-down racing games
- Score: BLACK: 0, WHITE: 122, TIME: 28
- "EXTENDED PLAY FOR 100 POINTS" message

## Implementation

### Files

**Game.java** - Main game engine with rendering, collision detection, and game loop
**Car.java** - Racing vehicle with realistic physics (acceleration, turning, friction)
**Track.java** - Oval race track with boundary detection
**RacingGameTest.java** - Component tests

### Racing Physics

- Gradual acceleration (not instant movement)
- Friction causes natural slowdown
- Turning requires forward momentum
- Collision bounce-back effect
- Maximum speed limits

### Controls

**BLACK Car**: W (accelerate), S (brake), A/D (turn)
**WHITE Car**: Arrow keys
**Global**: SPACE (pause), R (restart)

### Features

✓ Two-player racing
✓ Oval track with dotted boundaries
✓ Realistic car physics
✓ Collision detection (track, car-to-car)
✓ Score system (points for racing on track)
✓ Timer
✓ Extended play bonus at 100 points

## Why This Is A Racing Game

1. **Track layout**: Oval/circular racing circuit
2. **Dotted boundaries**: Race track edges
3. **Car positioning**: Cars at different track locations
4. **Physics**: Cars need acceleration and turning
5. **Competitive racing**: Race for best score/time

## Running

```bash
mvn clean compile
mvn exec:java
```
