package com.zetcode;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * 	In the first example we have a Character. We can move the Character on the board using the cursor keys.
 *  This class represents a Character. In this class we keep the image of the sprite and the coordinates of the sprite.
 *  The keyPressed() and keyReleased() methods control whether the sprite is moving.
 *  The move() method changes the coordinates of the sprite. These x and y values are used in the paintComponent() method to draw the image of the sprite.
 *  When we release the left cursor key, we set the dx variable to zero. The Character will stop moving.
 *  If we press the Space key, we fire. 
 *  The fire() method creates a new Missile object and adds it to the list of missiles.
 *  The getMissiles() method returns the list of missiles. It is called from the Board class.
 *  All the missiles fired by the spacecraft are stored in the missiles list. 
 *  When we fire a missile, a new Missile object is added to the missiles list. It is retained in the list until it collides with an alien or goes out of the window. 
 * @author G.I.
 *
 */
public class SpaceShip extends Sprite {

    private int dx;
    private int dy;
    private List<Missile> missiles;
    
    public SpaceShip(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("images/girlcharacter.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_R) {
        	
        }

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}