package dev.pekelund.gp42;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

final class Car {
    private static final double ACCELERATION = 0.045;
    private static final double TURN_SPEED = 0.07;
    private static final double FRICTION = 0.95;

    private final Color color;
    private final int up;
    private final int down;
    private final int left;
    private final int right;

    private double x;
    private double y;
    private double angle;
    private double speed;

    Car(Color color, int up, int down, int left, int right, double x, double y, double angle) {
        this.color = color;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    void update(boolean[] keys) {
        if (keys[up]) {
            speed += ACCELERATION;
        }
        if (keys[down]) {
            speed -= ACCELERATION;
        }
        speed *= FRICTION;

        if (keys[left]) {
            angle -= TURN_SPEED;
        }
        if (keys[right]) {
            angle += TURN_SPEED;
        }

        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(x, y);
        g2.rotate(angle);

        g2.setColor(color.darker());
        g2.fillRect(-1, -5, 2, 10);

        Polygon arrow = new Polygon(new int[] {0, 3, 0, -3}, new int[] {-6, -1, 2, -1}, 4);
        g2.setColor(color);
        g2.fillPolygon(arrow);

        g2.setTransform(old);
    }

    double x() {
        return x;
    }

    double y() {
        return y;
    }

    double speed() {
        return speed;
    }
}
