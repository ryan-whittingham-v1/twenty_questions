import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
	
public class Game {
	String userResponse;
	String filePath;
	InputStream fileIn;
	MyBinaryTree mbt;
	TNode currentNode;
	PrintWriter fileOut;
	int questionCount;
	String questionCountString;
	
	// Constructor
	public Game(InputStream fileIn) {		
		this.fileIn = fileIn;
		mbt = new MyBinaryTree(fileIn);
	}
		
	// Reset to the game to the root node
	public void reset() {
		currentNode = mbt.getRoot();
		questionCount = 0;
		questionCountString = "";
	}
	
	// Get question number
	public String getQuestionNum() {
		questionCountString = Integer.toString(questionCount);
		return ("Question # " + questionCountString);
	}
			
	// Ask question
	public String askQuestion() {
			questionCount++;
			if(currentNode.getType().equals("A:")) { // Answer node
				return("I've got it...is it " + currentNode.toString() + "?");
			}
			else {
				return currentNode.toString(); 
			}
	}
	
	// Yes button clicked
	public void yesClick() {
		if(currentNode.left == null) { // No more questions, answer correct
			JOptionPane.showMessageDialog(null, "Cool! I was right. Let's play again!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			reset(); // reset the game
		}
		else { // Otherwise move to the next question 
			currentNode = currentNode.left;
		}
	}
	
	// No button clicked
	public void noClick() {
		if(currentNode.right == null) { // No more questions (Answer wrong)
			userResponse = null;
			while(userResponse == null) { // While user response is empty
				userResponse = JOptionPane.showInputDialog("No! What were you thinking of?");
				if(userResponse == null) { // If cancel button pressed
					return; // exit method
				}
			}
			TNode newNode = new TNode(userResponse); // Create new node to store user Response
			newNode.setType("A:"); // Set new node type to answer
			TNode wrongNode = new TNode(currentNode.toString()); // Create new node to store incorrect answer
			wrongNode.setType("A:"); // Set wrong node type to answer
			userResponse = null;
			while(userResponse == null) { // While user response is empty
				userResponse = JOptionPane.showInputDialog("Please provide a question that distinguishes " + newNode + " from " + wrongNode);
				if(userResponse == null) { // If cancel button pressed
					return; // exit method
				}
			}
			currentNode.setData(userResponse); // Set currentNode text to new question
			currentNode.setType("Q:"); // Change currentNode to type question
			currentNode.left = newNode; // Assign left node of current to new node
			currentNode.right = wrongNode; // Assign right node of current to wrong node
			updateDB(); // Write updated decision tree to the text file
			JOptionPane.showMessageDialog(null, "Thanks! Now let's try again", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			reset();
		}
		else { // Otherwise move to the next question
			currentNode = currentNode.right;
		}
	}
	
	// Set file out path
	public void setFileOut(String filePath) {
			this.filePath = filePath; 
	
	}

	// Write tree to file	
	public void updateDB() {
		try {
			fileOut = new PrintWriter(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		mbt.write(mbt.getRoot(), fileOut);
		fileOut.flush();
	}
}
