package game.engine.ui;

import game.engine.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BouttonSansFond extends Boutton{

    private float m_zoomValue;
    private boolean m_mousein;

    public BouttonSansFond(int x , int y , int taille , String text) throws IOException, FontFormatException {
        m_xPos =x;
        m_yPos =y;
        m_width = taille*3;
        m_height = taille;
        m_isLocked = false;
        m_text = text;
        m_police = Font.createFont(Font.TRUETYPE_FONT,Game.class.getResourceAsStream("/polices/bitcrusher.ttf"));
        m_police = m_police.deriveFont((float)taille);
        m_zoomValue = taille;
    }

    @Override
    public void checkMouse(MouseEvent e,String evenType) {
        if(e.getX() < m_xPos || e.getY() < m_yPos || e.getX() > (m_xPos + m_width) || e.getY() > (m_yPos + m_height)){
            m_mousein = false;
            return;
        }
        m_mousein = true;

        if(evenType == "mP"){
            action();
        }


    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        if(Game.debugModeEnabled){
            g.setColor(Color.RED);
            g.drawLine(m_xPos,m_yPos,m_xPos+m_width, m_yPos);
            g.drawLine(m_xPos,m_yPos+m_height,m_xPos+m_width, m_yPos+m_height);
            g.drawLine(m_xPos,m_yPos,m_xPos,m_yPos+m_height);
            g.drawLine(m_xPos+m_width,m_yPos,m_xPos+m_width,m_yPos+m_height);
        }

        if(m_zoomValue <= m_height*1.3 && m_mousein){
            m_zoomValue += 1;
        }else if(m_zoomValue > m_height && !m_mousein){
            m_zoomValue -= 1;
        }
        m_police = m_police.deriveFont(m_zoomValue);

        g.setColor(Color.BLACK);
        g.setFont(m_police);
        g.drawString(m_text,m_xPos,(m_yPos+m_height));
    }

    @Override
    public void action() {

    }
}
