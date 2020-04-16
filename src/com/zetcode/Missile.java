package com.zetcode;

/**
 * Here we have a new sprite called Missile. 
 * The missile moves at constant speed. When it hits the right border of the Board,
 * it becomes invisible. It is then removed from the list of missiles. 
 * Missiles move in one direction only. They disappear after they reach the right window border. 
 * @author G.I.
 *
 */

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 1024;
    private final int MISSILE_SPEED = 20;

    public Missile(int x, int y) {
        super(x, y);
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("images/baggage.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += MISSILE_SPEED;
        	if (x > BOARD_WIDTH)
            visible = false;
    }
}