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
	
	public String getData() {
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
}
