package com.zetcode;
/**
 * Letters get the img and movement from here 
 * also how much letters will be in // comment if more letters are needed.
 * @author G.I
 *
 */
public class Letter extends Sprite {

	private final int INITIAL_X = 2024;
	
	@SuppressWarnings("unused")
	private final LetterType Type;

	public Letter(LetterType type, int x, int y) {
		super(x, y);
		Type = type;
		initLetter(type);
	}

	private void initLetter(LetterType type) {
    	String imagePath = "";
    	
    	switch(type) {
    	case A:
    		imagePath = "images/a.png";
    		break;
//    	case B:
//    		imagePath = "images/alien.png";
//    		break;
//    	case C:
//    		imagePath = "images/alien.png";
//    		break;
    	case D:
    		imagePath = "images/d.png";
    		break;
//    	case E:
//    		imagePath = "images/alien.png";
//    		break;
//    	case F:
//    		imagePath = "images/alien.png";
//    		break;
//    	case G:
//    		imagePath = "images/alien.png";
//    		break;
//    	case H:
//    		imagePath = "images/alien.png";
//    		break;
    	case I:
    		imagePath = "images/i.png";
    		break;
//    	case J:
//    		imagePath = "images/alien.png";
//    		break;
//    	case K:
//    		imagePath = "images/alien.png";
//    		break;
//    	case L:
//    		imagePath = "images/alien.png";
//    		break;
//    	case M:
//    		imagePath = "images/alien.png";
//    		break;
//    	case N:
//    		imagePath = "images/alien.png";
//    		break; 												
//    	case O:
//    		imagePath = "images/alien.png";
//    		break;
//    	case P:
//    		imagePath = "images/alien.png";
//    		break;
//    	case Q:
//    		imagePath = "images/alien.png";
//    		break;
//    	case R:
//    		imagePath = "images/alien.png";
//    		break;
//    	case S:
//    		imagePath = "images/alien.png";
//    		break;
//    	case T:
//    		imagePath = "images/alien.png";
//    		break;
//    	case U:
//    		imagePath = "images/alien.png";
//    		break;
//    	case W:
//    		imagePath = "images/alien.png";
//    		break;
//    	case X:
//    		imagePath = "images/alien.png";
//    		break;
//    	case Y:
//    		imagePath = "images/alien.png";
//    		break;
//    	case Z:
//    		imagePath = "images/alien.png";
//    		break;
		default:
			imagePath = "images/alien.png";
			break;
    	}
    	
    	loadImage(imagePath);
        getImageDimensions();
    }

	public void move() {

		if (x < 0) {
			x = INITIAL_X;
		}

		x -= 1;
	}
}