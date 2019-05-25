import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String userResponse = "";
		
		File file;
		
		// Create file object of database file
		if(args.length==0) {
			file = new File("src/PA4-data");
		}
		else {
			file = new File(args[0]);
		}
		
		// Scanner for reading database file
		Scanner fileIn = null;
		
		// Create scanner to parse file
		try {
			fileIn = new Scanner(file);
		}
		catch(Exception e) {
			System.out.println("File not found");
		}
		
		// Scanner for reading user input
		Scanner userIn = new Scanner(System.in);
		
		// Create new tree from file
		MyBinaryTree mbt = new MyBinaryTree(fileIn);
		
		fileIn.close(); // close fileIn scanner
		/*
		System.out.println("" + mbt.root);
		System.out.println("" + mbt.root.left);
		System.out.println("" + mbt.root.left.left);
		System.out.println("" + mbt.root.left.right);
		System.out.println("" + mbt.root.left.right.left);
		System.out.println("" + mbt.root.left.right.right);
		System.out.println("" + mbt.root.right);
		*/
		
		// Ask question
		TNode currentNode = mbt.getRoot();
		
		while(currentNode != null) {
			System.out.println(currentNode);
			
			// Get user response
			userResponse = userIn.nextLine();
			
			// Handle user response 
			if(userResponse.equals("y")) { // YES
				if(currentNode.left == null) { // No more questions (Answer right)
					System.out.println("Nice! I got it.");
					break;
				}
				else { // Otherwise move to the next question 
					currentNode = currentNode.left;
				}
			}
			else if(userResponse.equals("n")){ // NO
				if(currentNode.right == null) { // No more questions (Answer wrong)
					System.out.println("No! What were you thinking of?");
					userResponse = userIn.nextLine(); // Capture user response
					TNode newNode = new TNode(userResponse); // Create new node to store user Response
					newNode.setType("A:"); // Set new node type to answer
					TNode wrongNode = new TNode(currentNode.toString()); // Create new node to store incorrect answer
					wrongNode.setType("A:"); // Set wrong node type to answer
					System.out.println("Please provide a question that distinguishs your answer from the wrong one:");
					userResponse = userIn.nextLine(); // Capture user response
					currentNode.setData(userResponse); // Set currentNode text to new question
					currentNode.setType("Q:"); // Change currentNode to type question
					currentNode.left = newNode; // Assign left node of current to new node
					currentNode.right = wrongNode; // Assign right node of current to wrong node
					break;
				}
				else { // Otherwise move to the next question
					currentNode = currentNode.right;
				}
			}
		}
		
		mbt.print(mbt.getRoot());
		
		// Create PrintWriter to write to file
		PrintWriter fileOut;
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
