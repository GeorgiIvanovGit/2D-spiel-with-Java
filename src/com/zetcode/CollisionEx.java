package com.zetcode;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * 
 * Main class! Here we combine PopUp and adding the Board classe.
 * 
 * @author G.I.
 *
 */

@SuppressWarnings("serial")
public class CollisionEx extends JFrame {
	
	private boolean first = true;
	
    public CollisionEx() {  	
        initUI();
    }
    
    private void initUI() {	
    	cretaUI();
        add(new Board(this));
        setResizable(false);
        pack(); 
        setTitle("G.I.Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
     
    }

    public static void main(String[] args) {
    	
    		EventQueue.invokeLater(() -> {
            CollisionEx ex = new CollisionEx();
            ex.setVisible(true);

        });
    }
    
    private JFrame frame;   
    public void cretaUI() {

    	   frame = new PopUpMenu();
           frame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
           System.exit(0);
      }
 });
           frame.pack();
           frame.setVisible(false);           
 }

	public void nueStarten() {
		
		if (first) {
		removeAll();
		main(null);
		}
		
	first = false;
  }
	
}


