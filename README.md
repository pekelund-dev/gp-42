# gp-42
GP-42 clone in Java.

## Detailed implementation plan
- [x] Create a desktop game loop using Java Swing (`JPanel` + `Timer`) and a fixed 736x404 playfield.
- [x] Reproduce the HUD from the source image (`BLACK`, `TIME`, `WHITE`, score values, and bottom text).
- [x] Draw a dotted maze-like race track with matching outer border and inner walls.
- [x] Render two small directional cars that visually match the reference style.
- [x] Implement two-player controls:
  - BLACK: `W/A/S/D`
  - WHITE: `Arrow keys`
- [ ] Add lap timing/scoring system exactly matching arcade behavior.
- [ ] Add collision and off-track penalties based on track boundaries.
- [ ] Add start sequence and round reset flow.

## Run
```bash
mvn test
mvn exec:java
```
