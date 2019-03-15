
/*+------------------------------------------------+
* | SceneManagementEngineV2 by Kilian Vuillaume	   |
* | Programme: Game (prog principal)			   |
* |________________________________________________|
* | Date de création: 10/03/2019				   |
* | Description: Petite démo du système que 	   |
* | 			 j'utilise pour faire mes petits   |
* | 			 jeux, histoire de bien découper   |
* | 			 les différents scripts. Qui a     |
* | 			 jusque aujourd'hui fait ses 	   |
* |      		 preuves :)						   |
* |________________________________________________| 
*/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * <b>Game est la classe principale du jeu, elle sert de "squelette" pour le code du jeu.</b>
 * <p>
 * La classe Game se compose de beaucoup d'èlements dont :
 * <ul>
 * <li>Deux gros Threads (processus) qui vont gérer respectivement: les calculs et la partie graphique(avec une fréqence d'actualisation plus basse).</li>
 * <li>4 Listeners qui vont gérer les entrées clavier (chaque listener a sa spécialitée).</li>
 * <li>Un JPanel et un JFrame pour gérer la fenetre du jeu et d'autres paramètres.</li>
 * </ul>
 * </p>
 * <p>
 * La classe Game manipulera principalement des objets de type Scene. <br>
 * Ces objets sont spécialement concu pour être "lu" par la classe Game, <br>
 * cet a dire que a le programme principal va se charger d'actualiser les calculs de la scène <br>
 * ainsi que les graphismes. Ce système permet un meilleur découpage du code et une simplification pour <br>
 * les colaborateurs. C'est en quelque sorte un genre de "moteur de jeu".
 * </p>
 * 
 * @see Scene
 * 
 * @author Kilian Vuillaume (aka Dovabear)
 * @version 3.0
 */
public class Game{

	/**
	* La fenêtre principale du jeu, modifiable et public pour pouvoir ajuster certains paramètres (comme le titre par exemple)
	* Cette fenètre va contenir le JPanel.
	* @see javax.swing.JFrame
	*/
	public static JFrame fenetre;

	/**
	* Le JPanel qui va afficher les graphismes du jeu , il est préferable de ne pas y toucher.
	* @see javax.swing.JPanel 
	*/
	public static JPanel disp;

	/**
	* sceneIndex va contenir la liste de toutes les scènes du jeu, ce qui permetra par la suite au
	* programme principal d'y accèder. Toute Scene non incluse dans cette liste ne sera pas accessible
	* au cours du jeu.
	* @see Scene
	*/
	public static Scene[] sceneIndex;

	/**
	* currentScene permet au programme principal de savoir quelle scène il doit "lire"
	* en effet cette variable est essentielle et c'est en la modifiant que l'on peut changer de scène.
	* Cependant ATTENTION! cette variable est publique mais ne doit surtout pas ètre modifier 
	* directement, si vous voulez changer de scene alors utiliser putôt la fonction switchScene()
	* @see Game#switchScene(int sceneId)
	*/
	public static int currentscene;//Contient l'id de la scene actuellement jouée

	/**
	* fonction princpale
	*/
	public static void main(String[] args) throws Exception {
		fenetre = new JFrame(); //On initialise la fenetre

		disp = new JPanel(true){//On crée un nouveau JPanel
			public void paintComponent(Graphics g){//On redéfinit la méthode paintcomponent
				sceneIndex[currentscene].draw(g,this);
			}
		};
		disp.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mC");
			}

			public void mouseEntered(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mEn");
			}

			public void mouseExited(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mEx");
			}

			public void mousePressed(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mP");
			}

			public void mouseReleased(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mR");
			}
		});
		disp.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mD");
			}

			public void mouseMoved(MouseEvent e){
				sceneIndex[currentscene].mouseInput(e,"mM");
			}
		});
		disp.addMouseWheelListener(new MouseWheelListener(){
			public void mouseWheelMoved(MouseWheelEvent e){
				sceneIndex[currentscene].mouseWheelInput(e);
			}
		});
		disp.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){
				//sceneIndex[currentscene].input(e,"kt");
			}

			public void keyPressed(KeyEvent e){
				sceneIndex[currentscene].input(e,"kp");
			}

			public void keyReleased(KeyEvent e){
				sceneIndex[currentscene].input(e,"kr");
			}
		});

		
		disp.setFocusable(true);

		//On définit 2,3 propriètées de la fenetre
		fenetre.setTitle("SceneManagementEngineV2");
		fenetre.setSize(1200,600);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setContentPane(disp);
		fenetre.setVisible(true);

		loadScenes();//On "Load toutes les scenes"
		sceneIndex[currentscene].startEvent();//On lance la premiére scene

		Thread graphProcess = new Thread(){//On crée le premier thread qui va gèrer les graphismes
			public void run(){
				while(true){
					disp.repaint();//On affiche direct les graphismes
					try{//On attend 16ms pour faire du 60fps
						sleep(16);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};

		Thread computeProcess = new Thread(){//On crée le second thread qui va gerer les calculs et qui est au passage (plus rapide) 
			public void run(){
				while(true){
					sceneIndex[currentscene].update();//On execute la fonction update de la scene
				}
			}
		};
		
	
		//On lance les deux processus
		graphProcess.start();
		computeProcess.start();
	}

	/**
	* Fonction importante du programme qui va servir a charger les scenes.
	* C'est ici que l'on ajoute ses scenes a la liste et que l'on définit la scene par défaut.
	* C'est aussi ici que l'on définit la taille de la liste.
	*/
	public static void loadScenes(){ //Fonction qui load les scenes (wow explicite/20)
		sceneIndex = new Scene[1];//On initialise la liste avec le nombre de scenes que l'on a
		currentscene = 0;//On initialise l'id de la scene actuelle
		sceneIndex[0] = new Menu();
	}

	/**
	* La fonction switchScene sert a changer la scene courante.
	* Bien évidemment si la scene que l'on veut charger n'est pas dans la liste ou si elle n'existe pas.
	* elle execute aussi les evenements de sorties de d'entrées des scenes concernées.
	* @see Scene
	* @param sceneId
	*		donne le numéro de la scene a charger.
	*/
	public static void switchScene(int sceneId){//Fonction qui va permettre le changement de scenes
		if(sceneIndex[sceneId] == null){return;}//Si l'on essaye de switch sur un scene qui n'existe pas on quite
		if(sceneId > sceneIndex.length){return;}//si l'on essaye de load une scene avec un id trop grand ou trop petit on quite
		sceneIndex[currentscene].exitEvent();// on execute deja les events de "fin" de la scene
		currentscene = sceneId;//On change la scene courante
		sceneIndex[currentscene].startEvent();//On execute ensuite les evenements de "début" de scenes
	}

}

