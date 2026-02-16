package dev.pekelund.gp42;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class Track {
    private int width;
    private int height;
    private Area trackArea;
    private Path2D outerBoundary;
    private Path2D innerBoundary;
    
    // Track boundaries
    private static final int MARGIN = 20;
    private static final int TOP_MARGIN = 50;
    private static final int BOTTOM_MARGIN = 60;
    private static final int TRACK_WIDTH = 60; // Width of racing path
    
    // Dash patterns for track lines
    private static final float[] BORDER_DASH = {5, 5};
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        createTrack();
    }
    
    private void createTrack() {
        // Create a proper racing track with inner and outer boundaries
        // Like the reference image shows - a continuous path to race around
        
        int left = MARGIN;
        int right = width - MARGIN;
        int top = TOP_MARGIN;
        int bottom = height - BOTTOM_MARGIN;
        
        // Create outer boundary - complex shape matching the image
        outerBoundary = new Path2D.Double();
        outerBoundary.moveTo(left, top);
        
        // Top edge with curves
        outerBoundary.lineTo(left + 200, top);
        outerBoundary.quadTo(left + 250, top, left + 260, top + 30);
        outerBoundary.lineTo(left + 260, top + 80);
        outerBoundary.quadTo(left + 260, top + 110, left + 290, top + 120);
        outerBoundary.lineTo(right - 100, top + 120);
        outerBoundary.quadTo(right - 50, top + 120, right, top + 170);
        
        // Right edge
        outerBoundary.lineTo(right, bottom - 50);
        outerBoundary.quadTo(right, bottom, right - 50, bottom);
        
        // Bottom edge
        outerBoundary.lineTo(left + 50, bottom);
        outerBoundary.quadTo(left, bottom, left, bottom - 50);
        
        // Left edge
        outerBoundary.lineTo(left, top);
        outerBoundary.closePath();
        
        // Create inner boundary - creates the racing path width
        innerBoundary = new Path2D.Double();
        int innerLeft = left + TRACK_WIDTH;
        int innerRight = right - TRACK_WIDTH;
        int innerTop = top + TRACK_WIDTH;
        int innerBottom = bottom - TRACK_WIDTH;
        
        innerBoundary.moveTo(innerLeft, innerTop);
        
        // Inner path following outer shape but smaller
        innerBoundary.lineTo(innerLeft + 140, innerTop);
        innerBoundary.quadTo(innerLeft + 180, innerTop, innerLeft + 190, innerTop + 25);
        innerBoundary.lineTo(innerLeft + 190, innerTop + 60);
        innerBoundary.quadTo(innerLeft + 190, innerTop + 80, innerLeft + 210, innerTop + 85);
        innerBoundary.lineTo(innerRight - 80, innerTop + 85);
        innerBoundary.quadTo(innerRight - 40, innerTop + 85, innerRight, innerTop + 125);
        
        innerBoundary.lineTo(innerRight, innerBottom - 40);
        innerBoundary.quadTo(innerRight, innerBottom, innerRight - 40, innerBottom);
        
        innerBoundary.lineTo(innerLeft + 40, innerBottom);
        innerBoundary.quadTo(innerLeft, innerBottom, innerLeft, innerBottom - 40);
        
        innerBoundary.lineTo(innerLeft, innerTop);
        innerBoundary.closePath();
        
        // Track is the area BETWEEN outer and inner boundaries
        trackArea = new Area(outerBoundary);
        trackArea.subtract(new Area(innerBoundary));
    }
    
    public boolean isOnTrack(double x, double y) {
        return trackArea.contains(x, y);
    }
    
    public void draw(Graphics2D g) {
        // Draw background (dark gray)
        g.setColor(new Color(90, 90, 90));
        g.fillRect(0, 0, width, height);
        
        // Draw track surface (the racing path between boundaries)
        g.setColor(new Color(100, 100, 100));
        g.fill(trackArea);
        
        // Draw track boundaries with dotted white lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, BORDER_DASH, 0);
        g.setStroke(dashed);
        
        // Draw both boundaries
        g.draw(outerBoundary);
        g.draw(innerBoundary);
    }
}
