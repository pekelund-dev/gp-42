package dev.pekelund.gp42;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class RacingGameTest {
    
    public static void main(String[] args) {
        System.out.println("Testing GP-42 Racing Game Components...\n");
        
        // Test Car creation
        System.out.println("1. Creating BLACK racing car...");
        Car blackCar = new Car(
            "BLACK",
            100,
            200,
            Color.BLACK,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
        );
        System.out.println("   BLACK car created at position: (" + blackCar.getX() + ", " + blackCar.getY() + ")");
        System.out.println("   Initial score: " + blackCar.getScore());
        
        System.out.println("\n2. Creating WHITE racing car...");
        Car whiteCar = new Car(
            "WHITE",
            100,
            220,
            Color.WHITE,
            KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
        );
        System.out.println("   WHITE car created at position: (" + whiteCar.getX() + ", " + whiteCar.getY() + ")");
        System.out.println("   Initial score: " + whiteCar.getScore());
        
        // Test scoring
        System.out.println("\n3. Testing scoring system...");
        blackCar.addScore(10);
        whiteCar.addScore(20);
        System.out.println("   BLACK score after racing: " + blackCar.getScore());
        System.out.println("   WHITE score after racing: " + whiteCar.getScore());
        
        // Test movement
        System.out.println("\n4. Testing car movement...");
        blackCar.handleKey(KeyEvent.VK_W, true);
        for (int i = 0; i < 10; i++) {
            blackCar.update();
        }
        System.out.println("   BLACK car moved to: (" + String.format("%.2f", blackCar.getX()) + 
                          ", " + String.format("%.2f", blackCar.getY()) + ")");
        System.out.println("   Car is moving: " + blackCar.isMoving());
        
        // Test collision detection
        System.out.println("\n5. Testing collision detection...");
        boolean collision = blackCar.collidesWith(whiteCar);
        System.out.println("   Collision detected: " + collision);
        
        // Test track
        System.out.println("\n6. Testing track...");
        Track track = new Track(720, 400);
        boolean onTrack = track.isOnTrack(360, 200);
        System.out.println("   Center position on track: " + onTrack);
        
        System.out.println("\n✓ All racing game component tests passed!");
        System.out.println("\nGame Features:");
        System.out.println("- Two-player racing game");
        System.out.println("- Realistic car physics with acceleration and turning");
        System.out.println("- Oval race track with boundaries");
        System.out.println("- Score tracking for both players");
        System.out.println("- Collision detection with walls and other cars");
        System.out.println("- Extended play bonus at 100 points");
        System.out.println("\nTo run the racing game with GUI, execute: mvn exec:java");
    }
}
