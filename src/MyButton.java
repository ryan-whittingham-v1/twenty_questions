import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class MyButton extends JPanel {
	
	private String keyName="";
	private String keyValueStr="";
	private int keyValueInt;
			
	// Constructor
	public MyButton(String text) {
		this.setBackground(new Color(175, 38, 38));
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setLayout(new GridLayout(0, 1, 0, 0));
		keyName = keyValueStr = text;
		JLabel keyLabel = new JLabel(keyName, SwingConstants.CENTER);
		keyLabel.setForeground(Color.WHITE);
		keyLabel.setFont(new Font("FreeSans", Font.BOLD, 24));
		this.add(keyLabel);
	}
	
	public void mouseOver() {
		this.setBackground(new Color(102, 22, 22));
	}

	public void mouseExit() {
		this.setBackground(new Color(175, 38, 38));
	}
	
	public void mouseDown() {
		this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	}
	
	public void mouseUp() {
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));		
	}
	
	public void setValue(int x) {
		keyValueInt = x;
	}
	
	public void setValue(String text) {
		keyValueStr = text;
	}
	
	public String getValueStr() {
		return keyValueStr;		
	}
	
	public int getValueInt() {
		return keyValueInt;
	}
}
