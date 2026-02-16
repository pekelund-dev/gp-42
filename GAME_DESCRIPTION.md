╔═══════════════════════════════════════════════════════════════════════════╗
║                         GP-42 GAME CLONE                                  ║
║                    Two-Player Arcade Game                                 ║
╚═══════════════════════════════════════════════════════════════════════════╝

VISUAL LAYOUT:
┌───────────────────────────────────────────────────────────────────────────┐
│                                                                           │
│  BLACK: 0                TIME: 28                    WHITE: 122          │
│                                                                           │
│  ┌ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┐  │
│  ┆                                                                 ┆  │
│  ┆    ■────────┐  BLACK Player Trail                              ┆  │
│  ┆             │                                                   ┆  │
│  ┆             │                       ○  Food Item                ┆  │
│  ┆             └──────┐                                            ┆  │
│  ┆                    │                                            ┆  │
│  ┆                    │           ○  Food Item                     ┆  │
│  ┆                    │                                            ┆  │
│  ┆                    └───────────────┐                            ┆  │
│  ┆                                    │                            ┆  │
│  ┆                                    │     ┌───┐ WHITE Trail      ┆  │
│  ┆                                    │     │   ■                  ┆  │
│  ┆                                    │     └───┘                  ┆  │
│  ┆                                    │                            ┆  │
│  ┆         ○  Food Item               │                            ┆  │
│  ┆                                    │                            ┆  │
│  ┆                                    └────────────────────┐       ┆  │
│  ┆                                                         │       ┆  │
│  └ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┄ ┘  │
│                                                                           │
│               EXTENDED PLAY FOR 100 POINTS                                │
│                                                                           │
└───────────────────────────────────────────────────────────────────────────┘

GAME ELEMENTS:
- ■ : Player head (BLACK is black, WHITE is white)
- ──: Player trail (path the player has traveled)
- ○ : Food items (yellow dots worth 10 points each)
- ┄ : Dotted border (collision boundary)

GAMEPLAY:
1. Two players compete simultaneously on the same keyboard
2. Collect food items (○) to increase your score
3. Avoid hitting:
   - The dotted border walls
   - Your own trail
   - The opponent's trail
4. First to 100 points gets "Extended Play" bonus
5. Game tracks time and scores continuously

CONTROLS:
┌─────────────────────────┬─────────────────────────┐
│     BLACK PLAYER        │     WHITE PLAYER        │
├─────────────────────────┼─────────────────────────┤
│  W - Move Up            │  ↑ - Move Up            │
│  S - Move Down          │  ↓ - Move Down          │
│  A - Move Left          │  ← - Move Left          │
│  D - Move Right         │  → - Move Right         │
└─────────────────────────┴─────────────────────────┘

GLOBAL CONTROLS:
- SPACE: Pause/Resume game
- R: Restart game (when game over)

COLLISION RULES:
- Hitting the dotted border = GAME OVER
- Hitting your own trail = GAME OVER  
- Hitting opponent's trail = GAME OVER
- Collecting food = +10 points (no penalty)

SCORING:
- Each food item: 10 points
- Extended play message appears at 100 points
- No maximum score limit

GAME FEATURES:
✓ Real-time two-player action
✓ Continuous movement and collision detection
✓ Dynamic food spawning
✓ Score and time tracking
✓ Pause functionality
✓ Game over detection and restart
✓ Classic arcade visual style
✓ Smooth 100ms update cycle

TECHNICAL DETAILS:
- Language: Java 17+ (ready for Java 25)
- Framework: Java Swing
- Graphics: Java2D with antialiasing
- Update Rate: 100 milliseconds per frame
- Window Size: 720 x 400 pixels
- Trail Limit: 100 segments per player
- Max Food: 10 items on screen
- Food Spawn: Every 30 game ticks

BUILD COMMANDS:
$ mvn clean compile    # Compile the project
$ mvn exec:java        # Run the game
$ mvn test-compile     # Compile tests
$ java -cp target/test-classes:target/classes dev.pekelund.gp42.GameTest

PROJECT FILES:
- Game.java: Main game engine and rendering
- Player.java: Player entity with movement and collision
- GameTest.java: Component validation tests
- pom.xml: Maven build configuration
- README.md: User documentation
- IMPLEMENTATION.md: Technical details
- SUMMARY.md: Project overview

REFERENCE:
This implementation is a faithful clone of the GP-42 arcade game shown in
the reference image, preserving the classic two-player competitive gameplay,
visual style, and arcade mechanics.
