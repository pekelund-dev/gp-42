package dev.pekelund.gp42;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class MovementTest {
    
    public static void main(String[] args) {
        System.out.println("Testing GP-42 Car Movement...\n");
        
        // Create a car
        Car blackCar = new Car(
            "BLACK",
            100,
            100,
            Color.BLACK,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
        );
        
        System.out.println("Initial position: (" + blackCar.getX() + ", " + blackCar.getY() + ")");
        System.out.println("Initial isMoving: " + blackCar.isMoving());
        
        // Simulate pressing W key (accelerate)
        System.out.println("\nSimulating W key press (accelerate forward)...");
        blackCar.handleKey(KeyEvent.VK_W, true);
        
        // Update for several frames
        for (int i = 0; i < 20; i++) {
            blackCar.update();
        }
        
        System.out.println("After 20 updates with W pressed:");
        System.out.println("  Position: (" + String.format("%.2f", blackCar.getX()) + ", " + String.format("%.2f", blackCar.getY()) + ")");
        System.out.println("  isMoving: " + blackCar.isMoving());
        
        // Release W key
        blackCar.handleKey(KeyEvent.VK_W, false);
        
        // Simulate turning
        System.out.println("\nSimulating D key press (turn right)...");
        blackCar.handleKey(KeyEvent.VK_D, true);
        
        for (int i = 0; i < 20; i++) {
            blackCar.update();
        }
        
        System.out.println("After 20 updates with D pressed:");
        System.out.println("  Position: (" + String.format("%.2f", blackCar.getX()) + ", " + String.format("%.2f", blackCar.getY()) + ")");
        System.out.println("  isMoving: " + blackCar.isMoving());
        
        if (blackCar.isMoving()) {
            System.out.println("\n✓ Car movement is working!");
        } else {
            System.out.println("\n✗ Car is not moving!");
        }
        
        System.out.println("\nTo test in GUI: run 'mvn exec:java' and press W/A/S/D or arrow keys");
    }
}
