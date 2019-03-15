
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.awt.*;

public class test extends JPanel{
	BufferedImage icon;
	int b = 0;
	public test() throws Exception{
		super.setDoubleBuffered(true);
		icon = ImageIO.read(new File("chat.png")); // pour load les images
		
	}

	public void paintComponent(Graphics g){
		clear(g);
		repaint();
		g.drawImage(icon, 0, b, 100 , 200,  this );
	}

	public void avance(){
			b +=10;
	}

	public void clear(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	}

}