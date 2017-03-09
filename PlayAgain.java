import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Runtime;
import java.lang.Process;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;

public class PlayAgain extends JFrame{
	
	private static JFrame mainFrame;
	
	private static JPanel main;
	private static JPanel scorePanel;
	private static JPanel buttonPanel;
	
	private static JButton playAgainButton;
	private static JButton quitButton;
	
	private static JLabel scoreLabel;
	
	private static int lastScore;
	
	public static void main(String[] args){
		PlayAgain launch = new PlayAgain(Integer.parseInt(args[0]));
	}
	
	public PlayAgain(int score){
		
		lastScore = score;
		//Sets up the play again launcher
		mainFrame = new JFrame("Play Again");
		mainFrame.setVisible(true);
		mainFrame.setSize(500,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main = new JPanel();
		scorePanel = new JPanel();
		buttonPanel = new JPanel();

		
		//Adds the panel to the main
		mainFrame.add(main);
		
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		main.add(scorePanel);
		main.add(buttonPanel);
		
		scoreLabel = new JLabel("Previous Score: "+lastScore);
		scorePanel.add(scoreLabel);
		
		//Creates the buttons
		playAgainButton = new JButton("Play Again?");
		quitButton = new JButton("I dont want to have more fun");
		
		//Adds the buttons for the main screen
		
		main.add(playAgainButton);main.add(quitButton); 
		playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		playAgainButton.addActionListener(playAgainAction); 
		quitButton.addActionListener(quittingAction); 
	
	}
	
	public static ActionListener playAgainAction = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			mainFrame.dispose();
			try{
				Runtime rt = Runtime.getRuntime();
				Process p = rt.exec("javac *.java");
				Process p1 = rt.exec("java Launcher");
			
			}
			catch(IOException IOe){
				System.err.println("Error loading launcher!");
			}
		}
	};
	
	public static ActionListener quittingAction = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	};
	
	
}