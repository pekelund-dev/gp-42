package dev.pekelund.gp42;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.event.KeyEvent;
import org.junit.jupiter.api.Test;

class CarTest {
    @Test
    void carAcceleratesWhenUpKeyHeld() {
        Car car = new Car(Color.BLACK, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 10, 10, 0);
        boolean[] keys = new boolean[256];
        keys[KeyEvent.VK_W] = true;

        car.update(keys);

        assertTrue(car.speed() > 0.0);
        assertTrue(car.x() > 10.0);
    }

    @Test
    void carBrakesWhenDownKeyHeld() {
        Car car = new Car(Color.BLACK, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 10, 10, 0);
        boolean[] keys = new boolean[256];
        keys[KeyEvent.VK_W] = true;
        car.update(keys);

        keys[KeyEvent.VK_W] = false;
        keys[KeyEvent.VK_S] = true;
        car.update(keys);

        assertTrue(car.speed() < 0.05);
    }
}
