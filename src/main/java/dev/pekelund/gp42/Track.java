package dev.pekelund.gp42;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

final class Track {
    private final Path2D outer = new Path2D.Double();
    private final Path2D inner = new Path2D.Double();

    Track() {
        outer.moveTo(25, 78);
        outer.lineTo(710, 78);
        outer.lineTo(722, 90);
        outer.lineTo(722, 360);
        outer.lineTo(700, 386);
        outer.lineTo(40, 386);
        outer.lineTo(14, 360);
        outer.lineTo(14, 100);
        outer.closePath();

        inner.moveTo(122, 110);
        inner.lineTo(336, 110);
        inner.lineTo(352, 126);
        inner.lineTo(352, 274);
        inner.lineTo(560, 274);
        inner.lineTo(592, 246);
        inner.lineTo(592, 126);
        inner.lineTo(560, 95);
        inner.lineTo(432, 95);
        inner.lineTo(432, 130);
        inner.lineTo(390, 130);
        inner.lineTo(390, 95);
        inner.lineTo(355, 95);
        inner.lineTo(355, 246);
        inner.lineTo(246, 246);
        inner.lineTo(246, 212);
        inner.lineTo(96, 212);
        inner.lineTo(96, 176);
        inner.lineTo(122, 176);
        inner.closePath();
    }

    void draw(Graphics2D g2) {
        g2.setColor(new Color(235, 235, 235));
        g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[] {1f, 8f}, 0f));
        g2.draw(outer);
        g2.draw(inner);

        g2.drawLine(355, 95, 355, 274);
        g2.drawLine(370, 95, 370, 274);

        g2.drawLine(28, 78, 337, 78);
        g2.drawLine(422, 78, 708, 78);

        g2.drawLine(42, 386, 700, 386);
        g2.drawLine(20, 100, 20, 356);
        g2.drawLine(722, 90, 722, 360);
    }
}
