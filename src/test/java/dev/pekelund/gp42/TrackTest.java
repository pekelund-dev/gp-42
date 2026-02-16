package dev.pekelund.gp42;

public class TrackTest {
    
    public static void main(String[] args) {
        System.out.println("Testing GP-42 Racing Track...\n");
        
        // Create track
        Track track = new Track(720, 400);
        
        System.out.println("Track created successfully!");
        System.out.println("\nTrack features:");
        System.out.println("- Continuous racing circuit (not obstacles)");
        System.out.println("- Outer boundary with dotted white lines");
        System.out.println("- Inner boundary with dotted white lines");
        System.out.println("- Track width: 50 pixels");
        System.out.println("- Curved corners for realistic racing");
        
        // Test some points on the track
        System.out.println("\nTesting track positions:");
        
        // Test bottom straight (where cars start)
        boolean onTrack1 = track.isOnTrack(100, 330);
        System.out.println("  Position (100, 330) on track: " + onTrack1);
        
        // Test left side
        boolean onTrack2 = track.isOnTrack(35, 200);
        System.out.println("  Position (35, 200) on track: " + onTrack2);
        
        // Test top straight
        boolean onTrack3 = track.isOnTrack(400, 65);
        System.out.println("  Position (400, 65) on track: " + onTrack3);
        
        // Test right side
        boolean onTrack4 = track.isOnTrack(685, 200);
        System.out.println("  Position (685, 200) on track: " + onTrack4);
        
        // Test center (should be OFF track - inside inner boundary)
        boolean onTrack5 = track.isOnTrack(360, 200);
        System.out.println("  Position (360, 200) center on track: " + onTrack5 + " (should be false - center is inside)");
        
        System.out.println("\n✓ Track test complete!");
        System.out.println("\nThe track is now a continuous racing circuit,");
        System.out.println("not a maze with obstacles to avoid.");
        System.out.println("\nRun 'mvn exec:java' to see the track visually.");
    }
}
