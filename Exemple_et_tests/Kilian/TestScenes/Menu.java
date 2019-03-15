
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends Scene{

	boutton test;
	public static Color gc = Color.RED; 

	public Menu(){
		test = new boutton(10,10){
			public void action(){
				gc = Color.BLUE;
			}};
	}

	public void startEvent(){

	}

	public void update(){

	}

	public void draw(Graphics g , JPanel p){
		g.setColor(gc);
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		test.draw(g);
	}

	public void input(KeyEvent e , String typeOfInput){

	}

	public void mouseInput(MouseEvent e , String typeOfInput){
		if(typeOfInput == "mC"){
			test.isIn(e);
		}
	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){

	}

}

class boutton {

	private int xPos;
	private int yPos;

	public boutton(int x , int y ){
		xPos = x;
		yPos = y;
	}

	public void action(){

	}

	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(xPos,yPos,10,20);
	}

	public void isIn(MouseEvent e){
		if(e.getX() >= xPos && e.getX() <= xPos + 10
			&& e.getY() >= yPos && e.getY() <= yPos + 20){
			action();
		}
	}
}