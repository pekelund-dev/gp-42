package dev.pekelund.gp42;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    // Game constants
    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;
    private static final int GAME_SPEED = 50; // milliseconds
    
    // Game state
    private Timer timer;
    private boolean running;
    private boolean gameOver;
    private int gameTime;
    private Random random;
    
    // Players (cars)
    private Car blackCar;
    private Car whiteCar;
    
    // Track
    private Track track;
    
    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(this);
        
        random = new Random();
        track = new Track(WIDTH, HEIGHT);
        
        initGame();
        
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }
    
    private void initGame() {
        running = true;
        gameOver = false;
        gameTime = 0;
        
        // Initialize black car - on bottom section of track
        blackCar = new Car(
            "BLACK",
            150,
            310,
            Color.BLACK,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
        );
        
        // Initialize white car - on right section of track
        whiteCar = new Car(
            "WHITE",
            600,
            200,
            Color.WHITE,
            KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
        );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !gameOver) {
            gameTime++;
            
            blackCar.update();
            whiteCar.update();
            
            checkCollisions();
            checkLapProgress();
        }
        
        repaint();
    }
    
    private void checkCollisions() {
        // Check track boundary collisions
        if (!track.isOnTrack(blackCar.getX(), blackCar.getY())) {
            blackCar.hitWall();
        }
        
        if (!track.isOnTrack(whiteCar.getX(), whiteCar.getY())) {
            whiteCar.hitWall();
        }
        
        // Check car collision
        if (blackCar.collidesWith(whiteCar)) {
            blackCar.hitWall();
            whiteCar.hitWall();
        }
    }
    
    private void checkLapProgress() {
        // Simple scoring: cars get points for staying on track and moving
        if (gameTime % 10 == 0) {
            if (track.isOnTrack(blackCar.getX(), blackCar.getY()) && blackCar.isMoving()) {
                blackCar.addScore(1);
            }
            if (track.isOnTrack(whiteCar.getX(), whiteCar.getY()) && whiteCar.isMoving()) {
                whiteCar.addScore(1);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw track
        track.draw(g2d);
        
        // Draw scores and time
        drawUI(g2d);
        
        // Draw cars
        blackCar.draw(g2d);
        whiteCar.draw(g2d);
        
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
        if (blackCar.getScore() >= 100 || whiteCar.getScore() >= 100) {
            g2d.setColor(Color.CYAN);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
            String msg = "EXTENDED PLAY FOR 100 POINTS";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            g2d.drawString(msg, x, HEIGHT - 15);
        }
    }
    
    private void drawUI(Graphics2D g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("BLACK", 20, 25);
        g.drawString(String.valueOf(blackCar.getScore()), 100, 25);
        
        g.setColor(Color.WHITE);
        g.drawString("WHITE", WIDTH - 120, 25);
        g.drawString(String.valueOf(whiteCar.getScore()), WIDTH - 40, 25);
        
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("TIME", WIDTH / 2 - 30, 25);
        g.drawString(String.valueOf(gameTime / 20), WIDTH / 2 + 20, 25);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        blackCar.handleKey(e.getKeyCode(), true);
        whiteCar.handleKey(e.getKeyCode(), true);
        
        // Restart game
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            initGame();
        }
        
        // Pause game
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            running = !running;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        blackCar.handleKey(e.getKeyCode(), false);
        whiteCar.handleKey(e.getKeyCode(), false);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GP-42 Racing");
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
