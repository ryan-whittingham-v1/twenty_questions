import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyBinaryTree {
	
	private TNode root;
	private BufferedReader reader;
	
	// Constructor
	public MyBinaryTree(InputStream fileIn) {
		reader = new BufferedReader(new InputStreamReader(fileIn));
		root = build(reader);
	}
	
	// Recursively build tree from bufferedReader and return the root node
	public TNode build(BufferedReader reader) {				
		String temp = ""; // Temp string to hold next line from file
		try {
			temp = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temp.charAt(0) == 'A') { // Line is an answer
			TNode newNode = new TNode(); // Create new node
			newNode.setType("A:");
			try {
				newNode.setData(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			newNode.right = newNode.left = null; // End of branch, Set left and right pointers to null 
			return newNode;
		}
		else if(temp.charAt(0) == 'Q'){ // Line is a question
			TNode newNode = new TNode();
			newNode.setType("Q:");
			try {
				newNode.setData(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newNode.left = build(reader); // Recursively build up the left sub tree
			newNode.right = build(reader); // Recursively build up the right sub tree
			return newNode; 
		}			
		return null;
	}
	
	// Get the root of the tree
	public TNode getRoot() {
		return root;
	}
	
	// NLR traverse to write entire tree to file
	public TNode write(TNode node, PrintWriter fileOut) {
		if(node == null) {
			return node;
		}
		fileOut.println(node.getType());
		fileOut.println(node);
		write(node.left, fileOut);
		write(node.right, fileOut);
		return null;
	}
}	
	


