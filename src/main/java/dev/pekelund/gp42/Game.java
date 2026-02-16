package dev.pekelund.gp42;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    // Game constants
    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;
    private static final int CELL_SIZE = 4;
    private static final int GAME_SPEED = 100; // milliseconds
    
    // Border constants
    private static final int BORDER_LEFT = 10;
    private static final int BORDER_RIGHT = 20;
    private static final int BORDER_TOP = 40;
    private static final int BORDER_BOTTOM = 80;
    
    // Food constants
    private static final int FOOD_SPAWN_INTERVAL = 30;
    private static final int MAX_FOOD_COUNT = 10;
    private static final int FOOD_COLLECTION_RADIUS = CELL_SIZE * 3;
    private static final int FOOD_POINTS = 10;
    
    // Game state
    private Timer timer;
    private boolean running;
    private boolean gameOver;
    private int gameTime;
    private Random random;
    
    // Players
    private Player blackPlayer;
    private Player whitePlayer;
    
    // Food/targets
    private List<Point> food;
    
    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(this);
        
        random = new Random();
        food = new ArrayList<>();
        
        initGame();
        
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }
    
    private void initGame() {
        running = true;
        gameOver = false;
        gameTime = 0;
        
        // Initialize black player
        blackPlayer = new Player(
            "BLACK",
            WIDTH / 4,
            HEIGHT / 2,
            Color.BLACK,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
        );
        
        // Initialize white player
        whitePlayer = new Player(
            "WHITE",
            3 * WIDTH / 4,
            HEIGHT / 2,
            Color.WHITE,
            KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
        );
        
        // Spawn initial food
        spawnFood(5);
    }
    
    private void spawnFood(int count) {
        for (int i = 0; i < count; i++) {
            int x = CELL_SIZE * (random.nextInt((WIDTH - BORDER_RIGHT - BORDER_LEFT) / CELL_SIZE) + BORDER_LEFT);
            int y = CELL_SIZE * (random.nextInt((HEIGHT - BORDER_BOTTOM - BORDER_TOP) / CELL_SIZE) + BORDER_TOP / CELL_SIZE);
            food.add(new Point(x, y));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !gameOver) {
            gameTime++;
            
            blackPlayer.move();
            whitePlayer.move();
            
            checkCollisions();
            checkFoodCollection();
            
            // Spawn new food occasionally
            if (gameTime % FOOD_SPAWN_INTERVAL == 0 && food.size() < MAX_FOOD_COUNT) {
                spawnFood(1);
            }
        }
        
        repaint();
    }
    
    private void checkCollisions() {
        // Check wall collisions
        if (blackPlayer.checkWallCollision(WIDTH, HEIGHT)) {
            gameOver = true;
            running = false;
        }
        
        if (whitePlayer.checkWallCollision(WIDTH, HEIGHT)) {
            gameOver = true;
            running = false;
        }
        
        // Check self collision
        if (blackPlayer.checkSelfCollision()) {
            gameOver = true;
            running = false;
        }
        
        if (whitePlayer.checkSelfCollision()) {
            gameOver = true;
            running = false;
        }
        
        // Check player collision
        if (blackPlayer.checkCollision(whitePlayer.getTrail())) {
            gameOver = true;
            running = false;
        }
        
        if (whitePlayer.checkCollision(blackPlayer.getTrail())) {
            gameOver = true;
            running = false;
        }
    }
    
    private void checkFoodCollection() {
        Point blackHead = blackPlayer.getHead();
        Point whiteHead = whitePlayer.getHead();
        
        food.removeIf(f -> {
            if (isFoodCollected(f, blackHead)) {
                blackPlayer.addScore(FOOD_POINTS);
                return true;
            }
            if (isFoodCollected(f, whiteHead)) {
                whitePlayer.addScore(FOOD_POINTS);
                return true;
            }
            return false;
        });
    }
    
    private boolean isFoodCollected(Point food, Point playerHead) {
        return Math.abs(food.x - playerHead.x) < FOOD_COLLECTION_RADIUS && 
               Math.abs(food.y - playerHead.y) < FOOD_COLLECTION_RADIUS;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw border
        drawBorder(g2d);
        
        // Draw scores and time
        drawUI(g2d);
        
        // Draw food
        g2d.setColor(Color.YELLOW);
        for (Point f : food) {
            g2d.fillOval(f.x - 2, f.y - 2, 4, 4);
        }
        
        // Draw players
        blackPlayer.draw(g2d);
        whitePlayer.draw(g2d);
        
        // Draw game over message
        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 30));
            String msg = "GAME OVER - PRESS R TO RESTART";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            int y = HEIGHT / 2;
            g2d.drawString(msg, x, y);
        }
        
        // Draw extended play message
        if (blackPlayer.getScore() >= 100 || whitePlayer.getScore() >= 100) {
            g2d.setColor(Color.CYAN);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
            String msg = "EXTENDED PLAY FOR 100 POINTS";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            g2d.drawString(msg, x, HEIGHT - 15);
        }
    }
    
    private void drawBorder(Graphics2D g) {
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, new float[]{5, 5}, 0);
        g.setStroke(dashed);
        g.drawRect(BORDER_LEFT, BORDER_TOP, WIDTH - BORDER_LEFT - BORDER_RIGHT, 
                   HEIGHT - BORDER_TOP - BORDER_BOTTOM);
    }
    
    private void drawUI(Graphics2D g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("BLACK", 50, 25);
        g.drawString(String.valueOf(blackPlayer.getScore()), 130, 25);
        
        g.setColor(Color.WHITE);
        g.drawString("WHITE", WIDTH - 150, 25);
        g.drawString(String.valueOf(whitePlayer.getScore()), WIDTH - 70, 25);
        
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("TIME", WIDTH / 2 - 30, 25);
        g.drawString(String.valueOf(gameTime), WIDTH / 2 + 20, 25);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        blackPlayer.handleKey(e.getKeyCode(), true);
        whitePlayer.handleKey(e.getKeyCode(), true);
        
        // Restart game
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            food.clear();
            initGame();
        }
        
        // Pause game
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            running = !running;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        blackPlayer.handleKey(e.getKeyCode(), false);
        whitePlayer.handleKey(e.getKeyCode(), false);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GP-42 Clone");
            Game game = new Game();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
