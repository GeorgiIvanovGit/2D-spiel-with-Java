package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * PopUp window with 2 buttons to start and quit the game!
 * Opens before the main window!
 * 
 * @author G.I.
 *
 */

@SuppressWarnings("serial")
public final class PopUpMenu extends JFrame {

   private class SetupDialog extends JDialog {
	       
	public SetupDialog() {  	  
    	 super();
    	 pack(); 
         setModal(true);
         setLayout(new BorderLayout());
         
         add(new JButton(new AbstractAction("LET'S PLAY") {
        	 public void actionPerformed(ActionEvent s) {
				setVisible(false);
				setFont(new Font("Arial", Font.PLAIN, 40));							
			}			
         }), BorderLayout.CENTER);
         
         add(new JButton(new AbstractAction("QUIT") {
               public void actionPerformed(ActionEvent e) {
                  setVisible(false);
                  setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);                 
               }
         }), BorderLayout.SOUTH);
         
         setResizable(false);
         setPreferredSize(new Dimension(400, 400));
         setTitle("G.I.Game");
         setFont(new Font("Arial", Font.PLAIN, 40));
         
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		}   
   }
   
   public PopUpMenu() {
	   
      super();
      final SetupDialog dialog = new SetupDialog();
      dialog.pack();
      dialog.setVisible(true);
 
   }
}