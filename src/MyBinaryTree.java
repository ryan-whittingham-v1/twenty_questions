import java.io.File;
import java.util.Scanner;

public class MyBinaryTree {
	
	TNode root;
	
	public MyBinaryTree(Scanner fileIn) {
		root = read(fileIn);
	}
	
	
	//            
	public void build(Scanner fileIn) {
		root = read(fileIn);
	}
	
	// Recursively read from file to build tree and return root node
	public TNode read(Scanner fileIn) {
			
			String temp = ""; // Temp string to hold next line from file
			
			temp = fileIn.nextLine(); // Get line from file
			
			if(temp.charAt(0) == 'A') { // Line is an answer
				TNode newNode = new TNode(); // Create new node
				newNode.setType("A:");
				newNode.setData(fileIn.nextLine()); // Store the answer in the new node
				newNode.right = newNode.left = null; // End of branch, Set left and right pointers to null 
				return newNode;
			}
			else { // Line is a question
				TNode newNode = new TNode();
				newNode.setType("Q:");
				newNode.setData(fileIn.nextLine());
				newNode.left = read(fileIn); // Recursively build up the left sub tree
				newNode.right = read(fileIn); // Recursively build up the right sub tree
				return newNode; 
			}			
	}
	
	public TNode getRoot() {
		return root;
	}
	
	// NLR traverse and print tree
	public TNode print(TNode node) {
		if(node == null) {
			return node;
		}
		System.out.println(node.getType());
		System.out.println(node);
		print(node.left);
		print(node.right);
		return null;
	}
	

}	
	


