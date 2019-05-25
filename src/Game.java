import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
	
public class Game {
	String userResponse;
	File file;
	Scanner fileIn;
	MyBinaryTree mbt;
	TNode currentNode;
	PrintWriter fileOut;
	int questionCount;
	String questionCountString;
	
	public Game(File file) {		
		this.file = file;
	}
	
	public void loadDb() {
		// Create scanner to parse file
					try {
						fileIn = new Scanner(file);
					}
					catch(Exception e) {
						System.out.println("File not found");
					}
					// Create new tree from file
					mbt = new MyBinaryTree(fileIn);
					fileIn.close(); // close fileIn scanner
	}
	
	// Reset to the game to the root node
	public void reset() {
		currentNode = mbt.getRoot();
		questionCount = 0;
		questionCountString = "";
	}
	
	public String getQuestionNum() {
		questionCountString = Integer.toString(questionCount);
		return ("Question # " + questionCountString);
	}
			
	// Ask question
	public String askQuestion() {
			questionCount++;
			return currentNode.toString();
	}
	
	// Yes button click
	public void yesClick() {
					if(currentNode.left == null) { // No more questions (Answer right)
						JOptionPane.showMessageDialog(null, "Cool! I was right. Let's play again!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					else { // Otherwise move to the next question 
						currentNode = currentNode.left;
					}
	}
	
	// No button click
	public void noClick() {
					if(currentNode.right == null) { // No more questions (Answer wrong)
						String userResponse = JOptionPane.showInputDialog("Really? I'm never wrong. What were you thinking of?");
						while(userResponse.length() == 0) { // User did not type in anything
							userResponse = JOptionPane.showInputDialog("Please tell me what you were thinking of");
						}
						TNode newNode = new TNode(userResponse); // Create new node to store user Response
						newNode.setType("A:"); // Set new node type to answer
						TNode wrongNode = new TNode(currentNode.toString()); // Create new node to store incorrect answer
						wrongNode.setType("A:"); // Set wrong node type to answer
						userResponse = JOptionPane.showInputDialog("Please provide a question that distinguishes " + newNode + "from " + wrongNode);
						while(userResponse.length() == 0) { // User did not type in anything
							userResponse = JOptionPane.showInputDialog("What is a question that distinguishes " + newNode + "from " + wrongNode);
						}
						currentNode.setData(userResponse); // Set currentNode text to new question
						currentNode.setType("Q:"); // Change currentNode to type question
						currentNode.left = newNode; // Assign left node of current to new node
						currentNode.right = wrongNode; // Assign right node of current to wrong node
						updateDB();
						JOptionPane.showMessageDialog(null, "Thanks! Now let's try again", "Game Over", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					else { // Otherwise move to the next question
						currentNode = currentNode.right;
					}
	}
			
			
	// Create PrintWriter to write to file
	
	public void updateDB() {
		    try{
		      fileOut=new PrintWriter(file);
		    }catch (Exception e){
		      System.out.println("Couldn't open output file: " + e);
		      return; 
		    }
			
			//Write the tree to file
			mbt.write(mbt.getRoot(), fileOut);
			fileOut.flush();
	}

	

}
