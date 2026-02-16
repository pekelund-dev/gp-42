package dev.pekelund.gp42;

import java.awt.*;
import java.util.LinkedList;

public class Player {
    // Constants
    private static final int MAX_TRAIL_LENGTH = 100;
    private static final int COLLISION_THRESHOLD = 3;
    
    private String name;
    private Color color;
    private LinkedList<Point> trail;
    private int score;
    private int dx, dy;
    private int speed = 4;
    
    // Control keys
    private int upKey, downKey, leftKey, rightKey;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public Player(String name, int startX, int startY, Color color,
                  int upKey, int downKey, int leftKey, int rightKey) {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.trail = new LinkedList<>();
        
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        
        // Initialize position
        trail.add(new Point(startX, startY));
        dx = 0;
        dy = 0;
    }
    
    public void move() {
        // Update direction based on key presses
        updateDirection();
        
        // Move if there's a direction
        if (dx != 0 || dy != 0) {
            Point head = trail.getLast();
            Point newHead = new Point(head.x + dx, head.y + dy);
            trail.add(newHead);
            
            // Keep trail limited
            if (trail.size() > MAX_TRAIL_LENGTH) {
                trail.removeFirst();
            }
        }
    }
    
    private void updateDirection() {
        if (upPressed && dy == 0) {
            dx = 0;
            dy = -speed;
        } else if (downPressed && dy == 0) {
            dx = 0;
            dy = speed;
        } else if (leftPressed && dx == 0) {
            dx = -speed;
            dy = 0;
        } else if (rightPressed && dx == 0) {
            dx = speed;
            dy = 0;
        }
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
    
    public boolean checkWallCollision(int width, int height) {
        if (trail.isEmpty()) return false;
        Point head = trail.getLast();
        return head.x < 10 || head.x > width - 10 || 
               head.y < 40 || head.y > height - 40;
    }
    
    public boolean checkSelfCollision() {
        if (trail.size() < 5) return false;
        Point head = trail.getLast();
        
        for (int i = 0; i < trail.size() - 5; i++) {
            Point segment = trail.get(i);
            if (isColliding(head, segment)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCollision(LinkedList<Point> otherTrail) {
        if (trail.isEmpty() || otherTrail.isEmpty()) return false;
        Point head = trail.getLast();
        
        for (Point segment : otherTrail) {
            if (isColliding(head, segment)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isColliding(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) < COLLISION_THRESHOLD && 
               Math.abs(p1.y - p2.y) < COLLISION_THRESHOLD;
    }
    
    public void draw(Graphics2D g) {
        if (trail.isEmpty()) return;
        
        g.setColor(color);
        
        // Draw trail
        for (int i = 0; i < trail.size() - 1; i++) {
            Point p1 = trail.get(i);
            Point p2 = trail.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        
        // Draw head
        Point head = trail.getLast();
        g.fillRect(head.x - 3, head.y - 3, 6, 6);
        
        // Draw direction indicator
        if (dx != 0 || dy != 0) {
            g.drawLine(head.x, head.y, head.x + dx * 2, head.y + dy * 2);
        }
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
    
    public Point getHead() {
        return trail.isEmpty() ? new Point(0, 0) : trail.getLast();
    }
    
    public LinkedList<Point> getTrail() {
        return trail;
    }
}
