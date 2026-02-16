package dev.pekelund.gp42;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Track {
    private int width;
    private int height;
    private Area trackArea;
    
    // Track boundaries
    private static final int MARGIN = 30;
    private static final int TRACK_WIDTH = 60;
    
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        createTrack();
    }
    
    private void createTrack() {
        // Create outer boundary (oval track)
        int outerWidth = width - 2 * MARGIN;
        int outerHeight = height - 2 * MARGIN;
        Ellipse2D outer = new Ellipse2D.Double(MARGIN, MARGIN, outerWidth, outerHeight);
        
        // Create inner boundary (smaller oval)
        int innerWidth = outerWidth - 2 * TRACK_WIDTH;
        int innerHeight = outerHeight - 2 * TRACK_WIDTH;
        int innerX = MARGIN + TRACK_WIDTH;
        int innerY = MARGIN + TRACK_WIDTH;
        Ellipse2D inner = new Ellipse2D.Double(innerX, innerY, innerWidth, innerHeight);
        
        // Track is the area between outer and inner ovals
        trackArea = new Area(outer);
        trackArea.subtract(new Area(inner));
    }
    
    public boolean isOnTrack(double x, double y) {
        return trackArea.contains(x, y);
    }
    
    public void draw(Graphics2D g) {
        // Draw track surface
        g.setColor(new Color(80, 80, 80));
        g.fill(trackArea);
        
        // Draw track boundaries with dotted lines
        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                        0, new float[]{5, 5}, 0);
        g.setStroke(dashed);
        g.draw(trackArea);
        
        // Draw center line (optional)
        g.setStroke(new BasicStroke(1));
        int centerWidth = width - 2 * MARGIN - TRACK_WIDTH;
        int centerHeight = height - 2 * MARGIN - TRACK_WIDTH;
        int centerX = MARGIN + TRACK_WIDTH / 2;
        int centerY = MARGIN + TRACK_WIDTH / 2;
        
        Ellipse2D centerLine = new Ellipse2D.Double(centerX, centerY, centerWidth, centerHeight);
        float[] dashPattern = {3, 3};
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                    0, dashPattern, 0));
        g.setColor(new Color(150, 150, 150));
        g.draw(centerLine);
    }
}
