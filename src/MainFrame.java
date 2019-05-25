import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Create database file variable
		File dbFile;
		
		// Selecting the database file to assign
		if(args.length==0) { // If no command line args select from option pane window
			Object[] possibleValues = { "Star Wars", "Pokemon", "Jurassic World" };
			Object selectedValue = JOptionPane.showInputDialog(null,
			"Select Topic", "Twenty Questions",
			JOptionPane.INFORMATION_MESSAGE, null,
			possibleValues, possibleValues[0]);
			if(selectedValue.equals("Star Wars")) {
				dbFile = new File("/home/ryan/eclipse-workspace/CSE223-PA4/src/Star_Wars");
			}
			else if(selectedValue.equals("Pokemon")) {
				dbFile = new File("/home/ryan/eclipse-workspace/CSE223-PA4/src/Pokemon");
			}
			else if(selectedValue.equals("Jurassic World")) {
				dbFile = new File("/home/ryan/eclipse-workspace/CSE223-PA4/src/Jurassic_World");
			}
			else { // Something went wrong
				dbFile = null;
				System.out.println("File error.");
			}
		}
		else { // Command line arg given
			dbFile = new File(args[0]);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(dbFile);
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
	public MainFrame(File dbFile) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(310, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 117, 119));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px,grow][139px,grow][139px,grow][138px,grow][139px,grow][139px,grow][50px,grow]", "[50px,grow][79px,grow][78px,grow][grow][79px,grow]"));
		
		DisplayScreen questionCounter = new DisplayScreen();
		questionCounter.setOpaque(false);
		questionCounter.setBorder(null);
		questionCounter.textColor("white");
		contentPane.add(questionCounter, "cell 2 0 3 1,grow");
		
		
		DisplayScreen display = new DisplayScreen();
		contentPane.add(display, "cell 1 1 5 1,grow");
		
		//String userResponse = "";
		Dimension buttonMaxSize = new Dimension(200, 100);//Button max size
		
		// Create new game
		Game game = new Game(dbFile);
		
		// Load database file into the game
		game.loadDb();
		
		// Start game at beginning of database file
		game.reset();
		
		// Ask first question
		display.say(game.askQuestion());
		questionCounter.say(game.getQuestionNum());
		
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
