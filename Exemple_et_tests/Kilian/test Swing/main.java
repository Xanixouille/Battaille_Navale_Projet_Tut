
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.awt.*;

public class main{


		public static void main(String[] args) throws Exception{

			JFrame yolo = new JFrame("PD VA");
			JPanel voiture = new JPanel();
			test prout = new test();

			yolo.setVisible(true);
			yolo.setSize(800,600);
			yolo.setLocationRelativeTo(null);



			yolo.add(prout);

			while(true){

				prout.avance();
				Thread.sleep(1000);
			}

		}

}

