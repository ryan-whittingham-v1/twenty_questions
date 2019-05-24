public class TNode {
	
	private String data; // Node data
	private String type; // Node type
	public TNode left; // Yes response
	public TNode right; // No response
	
	// Constructor
	public TNode() {
		
	}
	
	public TNode(String text) {
		data = text;
	}
	
	public void setData(String text) {
		data = text;
	}
	
	public String getData(String text) {
		return data;
	}
	
	public void setType(String text) {
		type = text;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return data;
	}
	
	
	/*
	public String toString() {
		String returnString = "";
		
		returnString = returnString + data + " ";
		
		if(left != null) {
			//System.out.println("left node");
			returnString = returnString + left;
		}
		
		if (right != null) {
			returnString=returnString + right;
		}
		
		return(returnString);
	}
	*/
	/*
	// add new Tnode method
	  public void add(String text) {
		  if (left==null){
				  left=new TNode(text);
		  } 
		  else {
				  left.add(text); // add to (existing) left child
		  }
			  return;
		  // finished insert on left

	// otherwise, insert on the right
		  if( x == 1) {
			  if (right==null){
				  right=new TNode(text, x);
			  } else {
				  right.add(text, x); // add to (existing) right child
			  }
			  return;
		  }
	  }
	  */
}
