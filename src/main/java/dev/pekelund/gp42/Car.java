package dev.pekelund.gp42;

import java.awt.*;

public class Car {
    // Car constants - bigger with visible details
    private static final int CAR_SIZE = 16;
    private static final int CAR_WIDTH = 16;
    private static final int CAR_HEIGHT = 16;
    private static final double MAX_SPEED = 2.5;
    private static final double ACCELERATION = 0.12;
    private static final double FRICTION = 0.04;
    private static final double TURN_SPEED = 0.1;
    
    private String name;
    private Color color;
    private double x, y;
    private double speed;
    private double angle; // in radians
    private int score;
    
    // Control keys
    private int upKey, downKey, leftKey, rightKey;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public Car(String name, double x, double y, Color color,
               int upKey, int downKey, int leftKey, int rightKey) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.score = 0;
        this.speed = 0;
        this.angle = 0;
        
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }
    
    public void update() {
        // Handle acceleration
        if (upPressed) {
            speed += ACCELERATION;
            if (speed > MAX_SPEED) {
                speed = MAX_SPEED;
            }
        } else if (downPressed) {
            speed -= ACCELERATION;
            if (speed < -MAX_SPEED / 2) {
                speed = -MAX_SPEED / 2;
            }
        } else {
            // Apply friction
            if (speed > 0) {
                speed -= FRICTION;
                if (speed < 0) speed = 0;
            } else if (speed < 0) {
                speed += FRICTION;
                if (speed > 0) speed = 0;
            }
        }
        
        // Handle turning (only when moving)
        if (Math.abs(speed) > 0.1) {
            if (leftPressed) {
                angle -= TURN_SPEED;
            }
            if (rightPressed) {
                angle += TURN_SPEED;
            }
        }
        
        // Update position
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }
    
    public void handleKey(int keyCode, boolean pressed) {
        if (keyCode == upKey) {
            upPressed = pressed;
        } else if (keyCode == downKey) {
            downPressed = pressed;
        } else if (keyCode == leftKey) {
            leftPressed = pressed;
        } else if (keyCode == rightKey) {
            rightPressed = pressed;
        }
    }
    
    public void hitWall() {
        speed = -speed * 0.5; // Bounce back with reduced speed
        x += Math.cos(angle) * speed * 2;
        y += Math.sin(angle) * speed * 2;
    }
    
    public boolean collidesWith(Car other) {
        double dx = x - other.x;
        double dy = y - other.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < CAR_SIZE;
    }
    
    public void draw(Graphics2D g) {
        // Draw car as simple text character like in the reference image
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(x, y);
        g2.rotate(angle);
        
        // Draw car as a simple symbol (like # in the image)
        g2.setColor(color);
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2.drawString("#", -10, 5);
        
        g2.dispose();
    }
    
    public void addScore(int points) {
        score += points;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public boolean isMoving() {
        return Math.abs(speed) > 0.1;
    }
}
