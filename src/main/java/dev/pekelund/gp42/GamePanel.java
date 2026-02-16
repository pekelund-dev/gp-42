package dev.pekelund.gp42;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

final class GamePanel extends JPanel implements ActionListener, KeyListener {
    static final int WIDTH = 736;
    static final int HEIGHT = 404;

    private final Track track = new Track();
    private final Car black = new Car(Color.BLACK, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 205, 172, -0.45);
    private final Car white = new Car(Color.WHITE, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 325, 176, -0.45);
    private final boolean[] keys = new boolean[256];
    private final Timer timer = new Timer(16, this);

    GamePanel() {
        this(true);
    }

    GamePanel(boolean startTimer) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(95, 95, 95));
        setFocusable(true);
        addKeyListener(this);
        if (startTimer) {
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        track.draw(g2);
        drawHud(g2);
        black.draw(g2);
        white.draw(g2);

        g2.dispose();
    }

    private void drawHud(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2.drawString("BLACK", 48, 26);
        g2.drawString("TIME", 320, 26);

        g2.setColor(Color.WHITE);
        g2.drawString("WHITE", 584, 26);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        g2.drawString("114", 70, 44);
        g2.drawString("20", 334, 44);

        g2.setColor(Color.WHITE);
        g2.drawString("122", 600, 44);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 44));
        g2.drawString("EXTENDED PLAY FOR 100 POINTS", 40, 399);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        black.update(keys);
        white.update(keys);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }

    Car blackCar() {
        return black;
    }
}
