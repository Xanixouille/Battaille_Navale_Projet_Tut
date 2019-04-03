package game.engine.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Boutton {

    protected int m_xPos;
    protected int m_yPos;
    protected int m_width;
    protected int m_height;
    protected String m_text;
    protected Font m_police;
    protected boolean m_isLocked;

    public abstract void checkMouse(MouseEvent e ,String eventType);
    public abstract void update();
    public abstract void draw(Graphics g, JPanel p);
    public abstract void action();

}
