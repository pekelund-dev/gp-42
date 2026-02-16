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
    
    // Dash patterns for track lines
    private static final float[] BORDER_DASH = {5, 5};
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createTrack();
    }
    
    private void createTrack() {
        // Create outer boundary - full screen area
        Rectangle2D outer = new Rectangle2D.Double(10, 40, width - 20, height - 80);
        trackArea = new Area(outer);
        
        // Create walls/obstacles that match the image layout
        // The image shows a complex maze-like track with internal walls
        
        // Top-left section walls
        addWall(120, 80, 120, 40);   // Horizontal wall
        addWall(120, 80, 40, 50);    // Vertical wall
        
        // Top-middle section
        addWall(320, 60, 40, 90);    // Vertical divider
        
        // Top-right curves
        addWall(500, 80, 100, 40);   // Horizontal
        addWall(500, 120, 40, 60);   // Vertical
        
        // Middle section horizontal walls
        addWall(80, 180, 160, 30);   // Left horizontal
        addWall(400, 180, 200, 30);  // Right horizontal
        
        // Center obstacles
        addWall(280, 140, 50, 50);   // Center-ish obstacle
        
        // Bottom section walls
        addWall(100, 260, 400, 30);  // Long horizontal bottom wall
        addWall(140, 290, 40, 40);   // Small bottom-left obstacle
        
        // Right side vertical walls
        addWall(600, 120, 40, 120);  // Right side wall
        
        // Subtract walls from track area
        for (Rectangle2D wall : walls) {
            trackArea.subtract(new Area(wall));
        }
    }
    
    private void addWall(double x, double y, double w, double h) {
        Rectangle2D wall = new Rectangle2D.Double(x, y, w, h);
        walls.add(wall);
    }
    
    public boolean isOnTrack(double x, double y) {
        // Check if point is in track area (not in walls)
        return trackArea.contains(x, y);
    }
    
    public void draw(Graphics2D g) {
        // Draw gray background for track
        g.setColor(new Color(100, 100, 100));
        g.fillRect(0, 0, width, height);
        
        // Draw track surface (darker gray)
        g.setColor(new Color(80, 80, 80));
        g.fill(trackArea);
        
        // Draw walls/obstacles (darker)
        g.setColor(new Color(60, 60, 60));
        for (Rectangle2D wall : walls) {
            g.fill(wall);
        }
        
        // Draw track boundaries with dotted white lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, BORDER_DASH, 0);
        g.setStroke(dashed);
        
        // Draw outer boundary
        g.drawRect(10, 40, width - 20, height - 80);
        
        // Draw walls with dotted outline
        for (Rectangle2D wall : walls) {
            g.draw(wall);
        }
    }
}
