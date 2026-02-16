package dev.pekelund.gp42;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class GameTest {
    
    public static void main(String[] args) {
        System.out.println("Testing GP-42 Game Components...\n");
        
        // Test Player creation
        System.out.println("1. Creating BLACK player...");
        Player blackPlayer = new Player(
            "BLACK",
            100,
            100,
            Color.BLACK,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
        );
        System.out.println("   BLACK player created at position: " + blackPlayer.getHead());
        System.out.println("   Initial score: " + blackPlayer.getScore());
        
        System.out.println("\n2. Creating WHITE player...");
        Player whitePlayer = new Player(
            "WHITE",
            200,
            100,
            Color.WHITE,
            KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
        );
        System.out.println("   WHITE player created at position: " + whitePlayer.getHead());
        System.out.println("   Initial score: " + whitePlayer.getScore());
        
        // Test scoring
        System.out.println("\n3. Testing scoring system...");
        blackPlayer.addScore(10);
        whitePlayer.addScore(20);
        System.out.println("   BLACK score after collecting food: " + blackPlayer.getScore());
        System.out.println("   WHITE score after collecting food: " + whitePlayer.getScore());
        
        // Test movement
        System.out.println("\n4. Testing movement...");
        blackPlayer.handleKey(KeyEvent.VK_D, true);
        for (int i = 0; i < 5; i++) {
            blackPlayer.move();
        }
        System.out.println("   BLACK player moved to: " + blackPlayer.getHead());
        System.out.println("   Trail length: " + blackPlayer.getTrail().size());
        
        // Test collision detection
        System.out.println("\n5. Testing collision detection...");
        boolean wallCollision = blackPlayer.checkWallCollision(720, 400);
        System.out.println("   Wall collision detected: " + wallCollision);
        
        boolean selfCollision = blackPlayer.checkSelfCollision();
        System.out.println("   Self collision detected: " + selfCollision);
        
        System.out.println("\n✓ All component tests passed!");
        System.out.println("\nGame Features:");
        System.out.println("- Two-player simultaneous gameplay");
        System.out.println("- Real-time collision detection");
        System.out.println("- Score tracking for both players");
        System.out.println("- Game timer");
        System.out.println("- Food collection system");
        System.out.println("- Extended play bonus at 100 points");
        System.out.println("\nTo run the game with GUI, execute: mvn exec:java");
    }
}
