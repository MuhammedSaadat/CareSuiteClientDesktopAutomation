package automation.careSuiteClient.Frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


 


public class Frame {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @throws InterruptedException 
     */
	public static String lebel =null;
	public static String title =null;
	public static String url ="C://Users//Public//Pictures//Sample Pictures//Desert.jpg";

    public static void createAndShowGUIBlue() throws InterruptedException {
        //Create and set up the window.
        JFrame frame = new JFrame("IMPACT MESSAGE WINDOW");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
      //Set the frame icon to an image loaded from a file.
        frame.setIconImage(new ImageIcon(url).getImage());
        JLabel emptyLabel = new JLabel(lebel, SwingConstants.CENTER);
      
        emptyLabel.setPreferredSize(new Dimension(500, 150));  
        emptyLabel.setBorder(BorderFactory.createLineBorder(Color.blue));
        emptyLabel.setFont(new Font("Serif", Font.BOLD, 21));
        emptyLabel.setForeground(Color.blue);
        //emptyLabel.setText("<html><font color=blue>" + title + "</font><html>");
        //emptyLabel.setVerticalTextPosition(SwingConstants.CENTER);
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(3000);
        frame.setVisible(false);}
    
    public static void createAndShowGUIRed() throws InterruptedException {
        //Create and set up the window.
        JFrame frame = new JFrame("IMPACT MESSAGE WINDOW");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
      //Set the frame icon to an image loaded from a file.
        frame.setIconImage(new ImageIcon(url).getImage());
        JLabel emptyLabel = new JLabel(lebel, SwingConstants.CENTER);
      
        emptyLabel.setPreferredSize(new Dimension(500, 150));  
        emptyLabel.setBorder(BorderFactory.createLineBorder(Color.blue));
        emptyLabel.setFont(new Font("Serif", Font.BOLD, 21));
        emptyLabel.setForeground(Color.red);
        //emptyLabel.setText("<html><font color=blue>" + title + "</font><html>");
        //emptyLabel.setVerticalTextPosition(SwingConstants.CENTER);
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(3000);
        frame.setVisible(false);}
    

 /*
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    } */
}
