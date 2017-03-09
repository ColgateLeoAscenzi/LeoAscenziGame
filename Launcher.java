import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Runtime;
import java.lang.Process;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;

public class Launcher extends JFrame{
	//Must be static in order to be called inside the listener
	
	//Main Frame
	private static JFrame launchFrame;
	
	//Buttons
	private static JButton playButton;
	private static JButton loadButton;
	private static JButton quitButton;
	private static JButton clearButton;
	private static JButton backButton;
	
	//Panels
	private static JPanel main;
	private static JPanel loadMain;
	private static JPanel loadLeft;
	private static JPanel loadRight;
	
	//Current save
	private static String currentSave;
	
	public static void main(String[] args){
		Launcher launch = new Launcher();
	}
	
	public Launcher(){
		//Sets up the main launcher
		launchFrame = new JFrame("Launcher");
		launchFrame.setVisible(true);
		launchFrame.setSize(500,500);
		launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main = new JPanel();

		
		//Adds the panel to the main
		launchFrame.add(main);
		
		
		//Creates the buttons
		playButton = new JButton("Play");
		loadButton = new JButton("Load");
		quitButton = new JButton("Quit");
		clearButton = new JButton("Clear");
		backButton = new JButton("Back");
		
		//Adds the buttons for the main screen
		
		main.add(loadButton); main.add(quitButton); main.add(playButton);
		loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		playButton.addActionListener(playAction); 
		loadButton.addActionListener(loadAction); 
		quitButton.addActionListener(quitAction); 
		clearButton.addActionListener(clearAction);
		backButton.addActionListener(backAction);
	}
	
	//Creates an anonymous listener
	public static ActionListener playAction = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			launchFrame.dispose();
			
			//LITRERAL GENIUS MCGUEVER TECHNIQUE
			//Since making a new instance of leoascenzigame wouldn't work beecause
			//of rendering launcher and game
			//I here close the launcher, and call the command line to compile and run the game
			try{
				Runtime rt = Runtime.getRuntime();
				Process p = rt.exec("javac *.java");
				if(currentSave != null){
				Process p1 = rt.exec("java LeoAscenziGame "+currentSave);
				}
				else{
					Process p1 = rt.exec("java LeoAscenziGame 10 5 9");
				}
			}
			catch(IOException IOe){
				System.err.println("Error launching game!");
			}
			//LeoAscenziGame game = new LeoAscenziGame(10,9,5);
			//game.play();
			
			
		}
	};
	
	public static ActionListener clearAction = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			currentSave = null;
		}
	};
	
	public static ActionListener backAction = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			loadMain.setVisible(false);
			main.setVisible(true);
			main.add(playButton);
		}
	};
	
	public static ActionListener loadAction = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			loadMain = new JPanel();
			loadLeft = new JPanel();
			loadRight = new JPanel();
			
			//Makes the main invisible
			main.setVisible(false);
			
			//Adds the normal container to the frame
			launchFrame.add(loadMain);
			
			//Sets up a container for each frame
			loadMain.setLayout(new BoxLayout(loadMain, BoxLayout.Y_AXIS));
			loadRight.setLayout(new BoxLayout(loadRight, BoxLayout.PAGE_AXIS));
			
			//Adds label
			JLabel savesLabel = new JLabel("Select a saved game:");
			
			
			//Adds them to the JPanel instad of the main
			loadMain.add(loadRight);
			loadMain.add(loadLeft);

			loadLeft.add(playButton);
			loadLeft.add(clearButton);
			loadLeft.add(backButton);
			
			playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			loadRight.add(savesLabel);
			
			//Loads all the files that start with save
			File[] files = new File(System.getProperty("user.dir")).listFiles();
			for(File file: files){
				if(file.getName().substring(0,4).equals("save")){
						JButton tempButton = new JButton(file.getName());
						tempButton.addActionListener(buttonText);
						loadRight.add(tempButton);
				}
			}
			
		}
	};
	
	//Sets the current save to the text of the button
	public static ActionListener buttonText = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			//Casts the source of the event to a button, then gets its text
			if(e.getSource() instanceof JButton){
				currentSave = ((JButton) (e.getSource())).getText();
			}
			else{
				System.out.println("Somehow, something other than a button called this method...");
			}
		}
	};
	
	public static ActionListener quitAction = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			launchFrame.dispose();
			
			
			
		}
	};
	
	
}