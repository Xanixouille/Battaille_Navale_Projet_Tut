
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.awt.*;

public class test extends JPanel{
	BufferedImage icon;
	public test() throws Exception{
		icon = ImageIO.read(new File("ico.png")); // pour load les images
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(icon, 0, 0, null );
	}

}