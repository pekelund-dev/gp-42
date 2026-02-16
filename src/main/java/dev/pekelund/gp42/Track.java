package dev.pekelund.gp42;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private int width;
    private int height;
    private Area trackArea;
    private List<Rectangle2D> walls;
    
    // Track boundaries
    private static final int MARGIN = 10;
    private static final int TOP_MARGIN = 40;
    private static final int BOTTOM_MARGIN = 40;
    
    // Dash patterns for track lines
    private static final float[] BORDER_DASH = {3, 3};
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createTrack();
    }
    
    private void createTrack() {
        // Create the complex maze-like track matching the image
        // Start with full playable area
        int left = MARGIN;
        int right = width - MARGIN;
        int top = TOP_MARGIN;
        int bottom = height - BOTTOM_MARGIN;
        
        Rectangle2D outer = new Rectangle2D.Double(left, top, right - left, bottom - top);
        trackArea = new Area(outer);
        
        // Create the maze walls to match the image layout
        // The image shows a complex circuit with many internal walls creating paths
        
        // Top section walls
        addWall(80, 55, 160, 15);   // Top-left horizontal
        addWall(80, 55, 15, 80);    // Top-left vertical down
        addWall(80, 120, 80, 15);   // Left middle horizontal
        
        // Top-center section
        addWall(270, 55, 15, 90);   // Center-top vertical
        addWall(270, 130, 60, 15);  // Center horizontal
        
        // Top-right section  
        addWall(390, 55, 15, 60);   // Right-top vertical
        addWall(405, 95, 80, 15);   // Right-top horizontal
        addWall(470, 95, 15, 40);   // Right vertical
        
        // Right side
        addWall(550, 85, 100, 15);  // Top-right horizontal
        addWall(550, 85, 15, 100);  // Right-side vertical
        addWall(550, 170, 60, 15);  // Right-middle horizontal
        
        // Center obstacles
        addWall(220, 175, 15, 40);  // Center-left vertical
        addWall(345, 195, 40, 15);  // Center horizontal
        
        // Bottom-right section
        addWall(580, 245, 15, 70);  // Bottom-right vertical
        addWall(490, 300, 90, 15);  // Bottom-right horizontal
        
        // Bottom section
        addWall(100, 285, 340, 15); // Long bottom horizontal
        addWall(180, 210, 15, 75);  // Bottom-left vertical
        addWall(280, 240, 100, 15); // Bottom-center horizontal
        
        // Left bottom corner
        addWall(40, 240, 60, 15);   // Bottom-left horizontal
        
        // Subtract all walls from the track area
        for (Rectangle2D wall : walls) {
            trackArea.subtract(new Area(wall));
        }
    }
    
    private void addWall(double x, double y, double w, double h) {
        Rectangle2D wall = new Rectangle2D.Double(x, y, w, h);
        walls.add(wall);
    }
    
    public boolean isOnTrack(double x, double y) {
        return trackArea.contains(x, y);
    }
    
    public void draw(Graphics2D g) {
        // Draw background (dark gray like in image)
        g.setColor(new Color(90, 90, 90));
        g.fillRect(0, 0, width, height);
        
        // Draw track surface (slightly lighter gray)
        g.setColor(new Color(100, 100, 100));
        g.fill(trackArea);
        
        // Draw walls (darker)
        g.setColor(new Color(70, 70, 70));
        for (Rectangle2D wall : walls) {
            g.fill(wall);
        }
        
        // Draw all boundaries with dotted white lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, BORDER_DASH, 0);
        g.setStroke(dashed);
        
        // Draw outer boundary
        g.drawRect(MARGIN, TOP_MARGIN, width - 2 * MARGIN, height - TOP_MARGIN - BOTTOM_MARGIN);
        
        // Draw all wall outlines
        for (Rectangle2D wall : walls) {
            g.draw(wall);
        }
    }
}
