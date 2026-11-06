package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ComandoTeclas implements KeyListener {

    public boolean cimaPress;
    public boolean baixoPress;
    public boolean esqPress;
    public boolean dirPress;

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                cimaPress = true;
                break;

            case KeyEvent.VK_DOWN:
                baixoPress = true;
                break;

            case KeyEvent.VK_LEFT:
                esqPress = true;
                break;

            case KeyEvent.VK_RIGHT:
                dirPress = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                cimaPress = false;
                break;

            case KeyEvent.VK_DOWN:
                baixoPress = false;
                break;

            case KeyEvent.VK_LEFT:
                esqPress = false;
                break;

            case KeyEvent.VK_RIGHT:
                dirPress = false;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {

    }
}