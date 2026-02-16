# GP-42 Game Clone - Implementation Details

## Reference Image Analysis

The original game image shows:
- **Game Title**: GP-42
- **Two Players**: BLACK (score: 0) and WHITE (score: 122)
- **Timer**: Shows TIME: 28
- **Game Field**: Dotted border creating a playing arena
- **Player Entities**: Two characters marked with symbols (# for BLACK, similar for WHITE)
- **Visual Style**: Classic arcade monochrome with white dots on gray background
- **Bottom Message**: "EXTENDED PLAY FOR 100 POINTS"

## Implementation Features

### Core Components

1. **Game.java** (Main game engine)
   - Window size: 720x400 pixels matching arcade proportions
   - Game loop running at 100ms intervals
   - Real-time rendering with Java Swing
   - Collision detection system
   - Food spawning and collection
   - Score and timer tracking

2. **Player.java** (Player entity)
   - Trail-based movement system (like classic arcade games)
   - Individual color identification (BLACK/WHITE)
   - Keyboard controls:
     - BLACK: WASD keys
     - WHITE: Arrow keys
   - Collision detection for walls, self, and opponent
   - Score tracking

### Visual Implementation

The game recreates the classic arcade look:
```
┌─────────────────────────────────────────┐
│ BLACK: 0    TIME: 28         WHITE: 122 │
├╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
┊                                         ┊
┊    #  ←BLACK                            ┊
┊                          WHITE→  #      ┊
┊                                         ┊
┊           • ← food                      ┊
┊                                         ┊
├╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│     EXTENDED PLAY FOR 100 POINTS        │
└─────────────────────────────────────────┘
```

### Game Mechanics

1. **Scoring System**
   - Yellow food items worth 10 points each
   - "Extended Play" bonus triggered at 100 points
   - Score displayed at top of screen

2. **Collision Detection**
   - Wall collision (dotted border)
   - Self-trail collision
   - Opponent trail collision
   - Food collection radius

3. **Movement**
   - Continuous directional movement
   - Trail follows player position
   - Cannot reverse direction instantly
   - 4-pixel speed per update

4. **Game States**
   - Running: Active gameplay
   - Paused: Frozen state (SPACE key)
   - Game Over: Collision detected
   - Restart: R key when game over

### Controls Summary

| Action | BLACK Player | WHITE Player |
|--------|-------------|--------------|
| Up     | W           | Arrow Up     |
| Down   | S           | Arrow Down   |
| Left   | A           | Arrow Left   |
| Right  | D           | Arrow Right  |

| Global Controls |
|----------------|
| SPACE - Pause/Resume |
| R - Restart (game over) |

## Technical Specifications

- **Language**: Java 17+ (ready for Java 25)
- **Build Tool**: Maven 3.x
- **GUI Framework**: Java Swing
- **Graphics**: Java2D with antialiasing
- **Architecture**: Event-driven with game loop pattern

## Comparison to Reference

| Feature | Reference Image | Implementation |
|---------|----------------|----------------|
| Two players | ✓ BLACK/WHITE | ✓ BLACK/WHITE |
| Score display | ✓ | ✓ |
| Timer | ✓ | ✓ |
| Dotted border | ✓ | ✓ Dashed stroke |
| Player trails | ✓ | ✓ Linked trail |
| Food items | ✓ (implied) | ✓ Yellow dots |
| Extended play | ✓ | ✓ At 100 points |
| Collision detection | ✓ (implied) | ✓ Comprehensive |
| Game over state | ✓ (implied) | ✓ With restart |

## Running the Game

### Build and Run
```bash
# Compile
mvn clean compile

# Run
mvn exec:java

# Run tests
mvn test-compile
java -cp target/test-classes:target/classes dev.pekelund.gp42.GameTest
```

### System Requirements
- Java 17 or higher
- Maven 3.x
- Display environment for GUI (X11, Windows, macOS)

## Future Enhancements (Beyond MVP)
- Sound effects
- Multiple levels/mazes
- AI opponent option
- High score persistence
- Network multiplayer
- Customizable controls
- Power-ups
- Speed variations
