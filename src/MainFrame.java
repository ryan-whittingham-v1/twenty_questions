import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.awt.Color;

/*Ryan Whittingham
CSE 223
5/28/2019

PA4 - 20 Questions Game
-----------------------
This program utilizes a decision tree to try and guess what the user is thinking in 
as few tries as possible. It constructs nodes by parsing a provided text file that is formatted
for NLR tree order. If the program is unable to guess correctly, it asks for user input to update 
the text file. */

public class MainFrame extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame(args); // Pass args array to Main Frame
					frame.setVisible(true);
					frame.setTitle("Twenty Questions");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MainFrame(String[] args) {
		
		String filePath = ""; // String to hold text file filepath
		Object selectedValue; // Optionpane option
		String imagePath = ""; // String to hold background images filepath
		InputStream fileIn = null; // Inputstream for reading in text file
		
		if(args.length==0) { // If no command line args select from option pane window
			Object[] possibleValues = { "Star Wars", "Pokemon", "Jurassic World" }; 
			selectedValue = JOptionPane.showInputDialog(null, // OptionPane for user to select game topic
			"Select Topic", "Twenty Questions",
			JOptionPane.INFORMATION_MESSAGE, null,
			possibleValues, possibleValues[0]);
			if(selectedValue.equals("Star Wars")) {
				filePath = "/home/ryan/Desktop/twentyQuestionsData/Star_Wars";
				imagePath = "/resources/images/Star-Wars-Ralph-McQuarrie-6.jpg";
			}
			else if(selectedValue.equals("Pokemon")) {
				filePath = "/home/ryan/Desktop/twentyQuestionsData/Pokemon";
				imagePath = "/resources/images/psyduck.jpg";
			}
			else {
				filePath = "/home/ryan/Desktop/twentyQuestionsData/Jurassic_World";
				imagePath = "/resources/images/JWorld2.jpeg";
			}
				
			// For accessing file in JAR
			//fileIn = getClass().getResourceAsStream(filePath); 	
		}
		else if (args.length > 0) { // Command line arg given
				filePath = args[0];
				imagePath = "/resources/images/3958.jpg";
		}
		
		// Create input stream for database file
		try {
			fileIn = new FileInputStream(filePath);
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
				
		setExtendedState(MAXIMIZED_BOTH); // Launch to fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(310, 200, 550, 350);
		ImagePanel contentPane = new ImagePanel(imagePath); // Background image
		contentPane.setBackground(new Color(69, 117, 119));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px,grow][139px,grow][139px,grow][138px,grow][139px,grow][139px,grow][50px,grow]", "[50px,grow][79px,grow][25px,grow][grow][30px,grow]"));
		
		// Display for question number
		DisplayScreen questionCounter = new DisplayScreen();
		questionCounter.setOpaque(false);
		questionCounter.setBorder(null);
		questionCounter.textColor("white");
		contentPane.add(questionCounter, "cell 2 0 3 1,grow");
		
		// Display for asking questions
		DisplayScreen display = new DisplayScreen();
		contentPane.add(display, "cell 1 1 5 1,grow");
		
		// Create new game from the file input stream
		Game game = new Game(fileIn);
		try {
			fileIn.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		// Set game file out path
		game.setFileOut(filePath);
		
		// Start game at beginning of database file
		game.reset();
		
		// Ask first question
		display.say(game.askQuestion());
		questionCounter.say(game.getQuestionNum());
		
		Dimension buttonMaxSize = new Dimension(200, 100);//Button max size
		
		MyButton panel = new MyButton("Yes");
		panel.setMaximumSize(buttonMaxSize);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MyButton key = (MyButton) e.getSource();
				key.mouseOver();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseExit();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseDown();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseUp();
				game.yesClick();
				display.say(game.askQuestion());
				questionCounter.say(game.getQuestionNum());
			}
		});
		contentPane.add(panel, "cell 2 3,grow");
		
		MyButton panel_1 = new MyButton("No");
		panel_1.setMaximumSize(buttonMaxSize);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseOver();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseExit();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseDown();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				MyButton myBtn = (MyButton) e.getSource();
				myBtn.mouseUp();
				game.noClick();
				display.say(game.askQuestion());
				questionCounter.say(game.getQuestionNum());
			}
		});
		contentPane.add(panel_1, "cell 4 3,grow");
	}
}
