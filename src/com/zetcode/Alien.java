package com.zetcode;

/**
 * This is the Alien class. 
 * Aliens return to the screen on the right side after they have disappeared on the left. 
 * 
 */
	
public class Alien extends Sprite {
	
    private final int INITIAL_X = 1024;

    public Alien(int x, int y) {
        super(x, y);
        initAlien();
    }

    private void initAlien() {

        loadImage("images/bee1.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }
}