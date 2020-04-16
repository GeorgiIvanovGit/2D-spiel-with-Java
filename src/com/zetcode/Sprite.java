package com.zetcode;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *  we add another sprite type to our example—a missile. The missiles are launched with the Space key. 
 *  The Sprite class shares common code from the Missile and SpaceShip classes. 
 *  The constructor initiates the x and y coordinates and the visible variable.
 *  In the example, we have a spacecraft and aliens. We can move the spacecraft on the board using the cursor keys. Missiles destroying aliens are launched with the spacebar key. 
 *  The code that can be shared by all sprites (a craft, an alien, and a missile) is placed in the Sprite class.
 *  The getBounds() method returns the bounding rectangle of the sprite image. We need the bounds in collision detection.
 * @author G.I.
 *
 */
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected boolean collected; 
    protected Image image;

    public Sprite(int x, int y){

        this.x = x;
        this.y = y;
        visible = true;
        collected = false; 
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }
    
    public boolean isCollected() { //*************
    	return collected;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public void setCollected(Boolean collected) {
    	this.x+=5;
    	this.collected = collected;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}