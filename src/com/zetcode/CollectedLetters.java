package com.zetcode;

import javax.swing.ImageIcon;

/**
 * Letter class that will be for the letters that are collected down.
 * @author G.I.
 *
 */

public class CollectedLetters  extends Sprite{

	public CollectedLetters(int x, int y, ImageIcon p) {
		super(x, y);
		
		 initCollectedLetters();
	}
	
	private void initCollectedLetters() {
	
		loadImage("images/baggage.png");
        getImageDimensions();
        
	}
	
	
}
