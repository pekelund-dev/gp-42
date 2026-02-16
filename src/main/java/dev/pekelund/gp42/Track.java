package dev.pekelund.gp42;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Track {
    private int width;
    private int height;
    private Area trackArea;
    private Path2D outerBoundary;
    private Path2D innerBoundary;
    
    // Track boundaries
    private static final int MARGIN = 10;
    private static final int TOP_MARGIN = 40;
    private static final int BOTTOM_MARGIN = 40;
    private static final int TRACK_WIDTH = 50; // Width of the racing path
    
    // Dash patterns for track lines
    private static final float[] BORDER_DASH = {5, 5};
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        createTrack();
    }
    
    private void createTrack() {
        // Create a continuous racing circuit matching the image
        // The image shows a complex circuit with curves and straights
        
        int left = MARGIN;
        int right = width - MARGIN;
        int top = TOP_MARGIN;
        int bottom = height - BOTTOM_MARGIN;
        
        // Create outer boundary - follows outer edge of track
        outerBoundary = new Path2D.Double();
        outerBoundary.moveTo(left, top);
        
        // Top edge with curves
        outerBoundary.lineTo(right - 100, top);
        outerBoundary.quadTo(right - 50, top, right, top + 50); // Top right curve
        
        // Right edge
        outerBoundary.lineTo(right, bottom - 50);
        outerBoundary.quadTo(right, bottom, right - 50, bottom); // Bottom right curve
        
        // Bottom edge
        outerBoundary.lineTo(left + 50, bottom);
        outerBoundary.quadTo(left, bottom, left, bottom - 50); // Bottom left curve
        
        // Left edge back to start
        outerBoundary.lineTo(left, top);
        outerBoundary.closePath();
        
        // Create inner boundary - creates the track width
        innerBoundary = new Path2D.Double();
        int innerLeft = left + TRACK_WIDTH;
        int innerRight = right - TRACK_WIDTH;
        int innerTop = top + TRACK_WIDTH;
        int innerBottom = bottom - TRACK_WIDTH;
        
        innerBoundary.moveTo(innerLeft, innerTop);
        
        // Top edge inner
        innerBoundary.lineTo(innerRight - 80, innerTop);
        innerBoundary.quadTo(innerRight - 40, innerTop, innerRight, innerTop + 40);
        
        // Right edge inner  
        innerBoundary.lineTo(innerRight, innerBottom - 40);
        innerBoundary.quadTo(innerRight, innerBottom, innerRight - 40, innerBottom);
        
        // Bottom edge inner
        innerBoundary.lineTo(innerLeft + 40, innerBottom);
        innerBoundary.quadTo(innerLeft, innerBottom, innerLeft, innerBottom - 40);
        
        // Left edge inner back to start
        innerBoundary.lineTo(innerLeft, innerTop);
        innerBoundary.closePath();
        
        // Track is the area between outer and inner boundaries
        trackArea = new Area(outerBoundary);
        trackArea.subtract(new Area(innerBoundary));
    }
    
    public boolean isOnTrack(double x, double y) {
        return trackArea.contains(x, y);
    }
    
    public void draw(Graphics2D g) {
        // Draw background
        g.setColor(new Color(100, 100, 100));
        g.fillRect(0, 0, width, height);
        
        // Draw track surface (darker gray)
        g.setColor(new Color(80, 80, 80));
        g.fill(trackArea);
        
        // Draw track boundaries with dotted white lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, BORDER_DASH, 0);
        g.setStroke(dashed);
        
        // Draw outer boundary
        g.draw(outerBoundary);
        
        // Draw inner boundary
        g.draw(innerBoundary);
        
        // Draw center line (optional)
        g.setColor(new Color(150, 150, 150));
        Stroke centerDash = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                           0, new float[]{3, 3}, 0);
        g.setStroke(centerDash);
        // Draw a middle guide line (simplified)
        int midOffset = TRACK_WIDTH / 2;
        g.drawOval(MARGIN + midOffset, TOP_MARGIN + midOffset, 
                   width - 2 * MARGIN - 2 * midOffset, 
                   height - TOP_MARGIN - BOTTOM_MARGIN - 2 * midOffset);
    }
}
