package game.scenes;

import game.engine.Game;
import game.engine.GenerateurDeParticules;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;

public class Menu extends Scene {

    public BouttonSansFond bouttonJouer;
    public BouttonSansFond bouttonOptions;
    public BouttonSansFond bouttonQuitter;
    public BouttonSansFond bouttonCredits;

    public GenerateurDeParticules gn;

    public Menu() throws IOException, FontFormatException {
        bouttonJouer = new BouttonSansFond(22 ,300,40,"Jouer"){
            @Override
            public void action() {

            }
        };
        bouttonOptions = new BouttonSansFond(22,360,40,"Options");
        bouttonQuitter = new BouttonSansFond(22,480,40,"Quitter"){
            @Override
            public void action() {
                Game.quit();
            }
        };
        bouttonCredits = new BouttonSansFond(22,420,40,"Crédits");
        gn = new GenerateurDeParticules(0, 0,0,0,100,1000,
                ImageIO.read(Game.class.getResourceAsStream("/images/bulle_1.png")));
    }

    @Override
    public void update() {
        gn.update();
    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());
        gn.draw(g,p);
        bouttonJouer.draw(g,p);
        bouttonOptions.draw(g,p);
        bouttonCredits.draw(g,p);
        bouttonQuitter.draw(g,p);
    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        gn.setM_ypos(Game.fenetre.getHeight()-1);
        gn.setM_heigth(1);
        gn.setM_width(Game.fenetre.getWidth());
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        bouttonJouer.checkMouse(e,typeOfInput);
        bouttonOptions.checkMouse(e,typeOfInput);
        bouttonCredits.checkMouse(e,typeOfInput);
        bouttonQuitter.checkMouse(e,typeOfInput);
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {

    }
}
