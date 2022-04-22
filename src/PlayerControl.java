package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


public class PlayerControl implements KeyListener, ActionListener {

    private Player katch;
    private final int right;
    private final int left;
    private Timer time;
    private int delay = 10;

    public PlayerControl(Player katch, int left, int right) {
        this.katch = katch;
        this.right = right;
        this.left = left;
    }

    @Override
    public void actionPerformed(ActionEvent ke){
        time.start();
    }


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == left) {
            this.katch.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.katch.toggleRightPressed();
        }
        time = new Timer(delay, this);
        time.start();


    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == left) {
            this.katch.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.katch.unToggleRightPressed();
        }
    }
}
