package dev.pekelund.gp42;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public class Track {
    private int width;
    private int height;
    private Area trackArea;
    
    // Track boundaries
    private static final int MARGIN = 10;
    private static final int TOP_MARGIN = 40;
    private static final int BOTTOM_MARGIN = 40;
    private static final int CORNER_RADIUS = 15;
    
    // Dash patterns for track lines
    private static final float[] BORDER_DASH = {3, 3};
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        createTrack();
    }
    
    private void createTrack() {
        // Create a continuous maze path with rounded corners
        int left = MARGIN;
        int right = width - MARGIN;
        int top = TOP_MARGIN;
        int bottom = height - BOTTOM_MARGIN;
        
        // Start with full playable area
        RoundRectangle2D outer = new RoundRectangle2D.Double(left, top, right - left, bottom - top, 
                                                              CORNER_RADIUS, CORNER_RADIUS);
        trackArea = new Area(outer);
        
        // Create continuous maze path by subtracting rounded obstacles
        // This creates a winding path through the maze with rounded corners
        
        // Top-left section - create winding path
        addRoundedWall(80, 55, 160, 20);
        addRoundedWall(80, 55, 20, 90);
        addRoundedWall(80, 125, 90, 20);
        
        // Top-center path
        addRoundedWall(270, 55, 20, 100);
        addRoundedWall(270, 135, 70, 20);
        
        // Top-right winding section
        addRoundedWall(390, 55, 20, 70);
        addRoundedWall(405, 95, 90, 20);
        addRoundedWall(480, 95, 20, 50);
        
        // Right side path
        addRoundedWall(550, 80, 110, 20);
        addRoundedWall(550, 80, 20, 110);
        addRoundedWall(550, 170, 70, 20);
        
        // Center obstacles with rounded corners
        addRoundedWall(220, 175, 20, 50);
        addRoundedWall(340, 190, 50, 20);
        
        // Bottom-right section
        addRoundedWall(580, 240, 20, 80);
        addRoundedWall(480, 295, 100, 20);
        
        // Bottom section winding path
        addRoundedWall(100, 280, 350, 20);
        addRoundedWall(180, 210, 20, 85);
        addRoundedWall(280, 235, 110, 20);
        
        // Left bottom corner path
        addRoundedWall(35, 235, 70, 20);
    }
    
    private void addRoundedWall(double x, double y, double w, double h) {
        RoundRectangle2D wall = new RoundRectangle2D.Double(x, y, w, h, CORNER_RADIUS, CORNER_RADIUS);
        trackArea.subtract(new Area(wall));
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
        
        // Draw track boundaries with dotted white lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, BORDER_DASH, 0);
        g.setStroke(dashed);
        
        // Draw the track outline
        g.draw(trackArea);
    }
}
