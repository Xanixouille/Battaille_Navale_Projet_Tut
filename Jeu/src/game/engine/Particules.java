package game.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Particules {
    private int m_x;
    private int m_y;
    private int m_w;
    private int m_h;
    private int m_xSpeed;
    private BufferedImage m_sprite;

    public Particules(int m_x, int m_y, int m_w, int m_h, int m_xSpeed, BufferedImage m_sprite) {
        this.m_x = m_x;
        this.m_y = m_y;
        this.m_w = m_w;
        this.m_h = m_h;
        this.m_xSpeed = m_xSpeed;
        this.m_sprite = m_sprite;
    }

    public void draw(Graphics g , JPanel p){
        m_y -= m_xSpeed;
        g.drawImage(m_sprite,m_x,m_y,m_w,m_h,p);
    }

    public int getM_x() {
        return m_x;
    }

    public int getM_y() {
        return m_y;
    }

    public int getM_w() {
        return m_w;
    }

    public int getM_h() {
        return m_h;
    }
}
