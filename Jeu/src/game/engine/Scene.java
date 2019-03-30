package game.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * <b>Scene est une classe abstraite qui permet de créer des classes qui permettrons a leurs tour de mieux découper les difèrentes fonction.</b>
 *
 *
 * @see Scene
 *
 * @author Kilian Vuillaume (aka Dovabear)
 * @version 3.0
 */

public abstract class Scene{											//classe abstraite car on instancie pas de scenes sans rien !
    /**
     * Fonction qui va contenir la partie calcul de la scène, elle sera dailleur actualiser plus vite que la fonction draw.
     */
    public abstract void update();

    /**
     * La fonction draw va contenir tout ce qui devra etre effectuer sur le JPanel de la classe Game
     * cette fonction va donc contenir la partie graphique de la scene.
     * @see Game#disp
     * @param g
     *		ceci est la Graphics g appelé par la focntion paintComponent de la fonction (ne pas y toucher)
     * @param p
     *		ceci est une référence au panel, peut etre utile pour getHeigt() et autres fonctions
     */
    public abstract void draw(Graphics g , JPanel p);

    /**
     * startEvent va contenir toutes est instructions a executer au lancement de la scene.
     * elle est appellée automatiquement. Cette fonction peut servir a initialiser certaines variables.
     * @see Game#switchScene(int sceneId)
     */
    public abstract void startEvent();

    /**
     * Input est la fonction qui va gerer toutes les entrées CLAVIER de la scene.
     * @param e
     *		objet qui contient des informations sur la touche appuyer etc...
     * @param typeOfInput
     *		chaine de caractère qui va donner l'information sur le type d'input: kp (keyPressed) , kr(keyRealesed) , kt(keyTyped)
     * @see java.awt.event.KeyEvent
     */
    public abstract void input(KeyEvent e, String typeOfInput);

    /**
     * mouseInput va servir a gerer les inputs de la souris.
     * @param e
     *		objet qui contient des informations sur l'état de la souris etc...
     * @param typeOfInput
     *		chaine de caractère qui va donner l'information sur le type d'input: mC (clicked) , mP(pressed) , mR(realesed) , mEn(mouse Enter) , mEx(mouse Exit) , mD(mouseDraggged) , mM(mouse Moved)
     * @see java.awt.event.MouseEvent
     */
    public abstract void mouseInput(MouseEvent e, String typeOfInput);

    /**
     * mouseWheelInput va servir a gerer les inputs de la molette de la souris.
     * @param e
     *		objet qui va contenir les informations sur la molette de la souris
     * @see java.awt.event.MouseWheelEvent
     */
    public abstract void mouseWheelInput(MouseWheelEvent e);

    /**
     * exitEvent va contenir toutes les instructions a executer quand l'on quitte la scene.
     * elle est appellée automatiquement. Cette fonction peut servir a sauvegarder par exemple.
     * @see Game#switchScene(int sceneId)
     */
    public abstract void exitEvent();
}
