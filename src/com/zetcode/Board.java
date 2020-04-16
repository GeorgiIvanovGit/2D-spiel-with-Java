package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *  This is the Board class.
In the doDrawing() method, we draw the spaceship with the drawImage() method.
 We get the image and the coordinates from the sprite class.
The actionPerformed() method is called every DELAY ms. We call the step() method.
We move the sprite and repaint the part of the board that has changed. I use a small optimisation technique that repaints only the small area of the window that actually changed.
In the Board class we listen for key events. The overridden methods of the KeyAdapter class delegate the processing to the methods of the Craft class. 
In the doDrawing() method, we draw the craft and all the available missiles. 
In the updateMissiles() method we parse all missiles from the missiles list. 
Depending on what the isVisible() method returns, we either move the missile or remove it from the container.
These are the initial positions of alien ships. with pos !
The initAliens() method creates a list of alien objects. The aliens take their initial positions from the pos array. 
Inside the paintComponent() method, we either draw game sprites or write the game over message. This depends on the ingame variable. 
The drawObjects() method draws game sprites on the window. First, we draw the craft sprite. 
In this loop we draw all aliens; they are drawn only if they have not been previously destroyed. This is checked by the isVisible() method. 
In the top-left corner of the window, we draw how many letters we need. 
The drawGameOver() draws a game over message in the middle of the window. 
The message is displayed at the end of the game,
either when we destroy all alien ships or when we collide with one of them. 
Each action event represents one game cycle. The game logic is factored into specific methods. For instance,
the updateMissiles() moves all the available missiles. 
Inside the updateAliens() method, we first check if there are any alien objects left in the aliens list.
The game is finished if the list is empty. If it is not empty, we go trough the list and move all its items.
The destroyed aliens are removed from the list. 
The checkCollisions() method checks for possible collisions.
First, we check if the craft object collides with any of the alien objects.
We get the rectangles of the objects with the getBounds() method.
 The intersects() method checks if the two rectangles intersect. 
 code checks the collisions between missiles and aliens and letters and character.
 * @author G.I.
 *
 */

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private SpaceShip spaceship;
    private List<Letter> collectedLetters = new ArrayList<>();	
    private List<Letter> letters;
    private List<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 1024;
    private final int B_HEIGHT = 768;
    private final int DELAY = 10;
    private CollisionEx collisionEx;

    private final int[][] pos = {
    	{1024, 29}, {500, 59}, {1024, 530},
    	{780, 109}, {580, 139}, {680, 540},
    	{790, 259}, {1024, 50}, {790, 530},
    	{980, 209}, {560, 45}, {510, 450},
    	{930, 159}, {590, 330}, {530, 560},
        {940, 59}, {990, 30}, {920, 520},
        {900, 259}, {1024, 50}, {540, 570},
        {810, 220}, {860, 220}, {740, 520},
        {820, 128}, {490, 170}, {700, 500}
    };

    public Board(CollisionEx collisionEx) {
    	this.collisionEx = collisionEx;
        initBoard();
    }
      
    private void initBoard() {
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;
        initAliens();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        spaceship = new SpaceShip(ICRAFT_X, ICRAFT_Y);
        initletters();       
        timer = new Timer(DELAY, this);
        timer.start();
        
    }
    
    public void initAliens() {
        
        	aliens = new ArrayList<>();
        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }

	public void initletters() {
        
        letters = new ArrayList<>();
    	
        for(LetterType type : LetterType.values()) {
        	int x = (int)(Math.random()*1100);
        	int y = (int)(Math.random()*520);
        	letters.add(new Letter(type,x, y));
        }
    }

    @Override
    public void paintComponent(Graphics g) {   	
    	super.paintComponent(g);

        if (ingame) {
        ImageIcon img = new ImageIcon("images/backgroundsky.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);	
            drawObjects(g);
            
        } else {

            drawGameOver(g);          
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
	private void drawObjects(Graphics g) {

        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), 
                        missile.getY(), this);
            }
        }

        for (Letter letter : letters) {
            if (letter.isVisible()) {
                g.drawImage(letter.getImage(), letter.getX(), letter.getY(), this);
            }
           
            
        for (Letter collectedLettersname : collectedLetters) { 	
        	if (collectedLettersname.isCollected())	{ 
        		g.drawImage(collectedLettersname.getImage(), collectedLettersname.getX(), collectedLettersname.getY(),this);
        		
        	}
        }
        
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }
    }
    
        Font GamecountFont = new Font("Sanserif", Font.ROMAN_BASELINE,20);
        g.setColor(Color.BLACK);  
        g.setFont(GamecountFont);
        g.drawString("Lettersleft " + letters.size(), 20,30);
       
    }
    private void drawGameOver(Graphics g) {
    	   	
        String msg = "Game Over";
        String msm = "Click here to Restart";
        Font small = new Font("Helvetica", Font.BOLD, 55);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.BLUE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString(msm, (B_WIDTH - fm.stringWidth(msm)) / 2,
                B_HEIGHT / 3);
           
        JButton nue = new JButton();
        this.add(nue);
        this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				collisionEx.nueStarten();							
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
								
			}

			@Override
			public void mouseExited(MouseEvent arg0) {				
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
		
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
								
			}      	
        });
    }

	@Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        updateShip();
        updateMissiles();
        updateLetters();
        checkCollisions();
        updateAliens();
        repaint(); 
        
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updateShip() {

        if (spaceship.isVisible()) {
            spaceship.move();
        }
    }

    private void updateMissiles() {

        List<Missile> ms = spaceship.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateLetters() {

        for (int i = 0; i < letters.size(); i++) {

        	Letter a = letters.get(i);
            
            if (a.isVisible() && !a.isCollected()) {
                a.move();
            }else { 	
                letters.remove(i); 
            }
        }
    }
    
    private void updateAliens() {

        if (aliens.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            
            if (a.isVisible()) {
                a.move();
            }else {
               aliens.remove(i);    
            }
        }
    }

    public int getNextCollectedLetterPossition() {
    		if(collectedLetters.isEmpty()) {
    			return 1;
    		}else {
    			return collectedLetters.size() * 10;
    	}
    }
     
    public void checkCollisions() {
    	      
        	 Rectangle spaceshipRectangle = spaceship.getBounds();     	  
        	 
        	  for (Letter letter : letters) {
              	
                  Rectangle letterletterr6 = letter.getBounds();
                  
                    for(Alien alien : aliens) {
              	
                    	Rectangle r3 = alien.getBounds(); 
              	
                    	if (spaceshipRectangle.intersects(letterletterr6)) {
                  
	                      spaceship.setVisible(true);
	                      letter.setCollected(true);
	                      letter.x = getNextCollectedLetterPossition();
	                      letter.y = 650;
	                      collectedLetters.add(letter);
	                      ingame = true;
                    	}
                  
                    	if (spaceshipRectangle.intersects(r3)) {
                    		ingame = false;
            	  	
                    	}  
         }
  	  }
        
        
        List<Missile> ms = spaceship.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Letter letter : letters) {

                Rectangle r2 = letter.getBounds();
                
                for(Alien aliena : aliens) {
                	
                	Rectangle rr3 = aliena.getBounds();
                
                if (r1.intersects(r2)) {
                	
                    m.setVisible(true);
                    letter.setVisible(true);
                   
                	}
                if (r1.intersects(rr3)) {
                	
                	m.setVisible(false);
                	aliena.setVisible(false);
                	
                	}
                }
            }
        }
    } 

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }
    }
}
