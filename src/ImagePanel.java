import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private BufferedImage image;
	private String imagePath;
	
	// Constructor
    public ImagePanel(String text) {
    	imagePath = text;
    	this.setOpaque(false);
    	try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // Draw Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null); // see javadoc for more info on the parameters            
    }
}
