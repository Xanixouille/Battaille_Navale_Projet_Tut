package game.engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GenerateurDeParticules {
    private List<Particules> m_particules;
    private List<Particules> m_toRmv;
    private long m_timer = 0;
    private Random rng;
    private int m_xpos;
    private int m_ypos;
    private int m_width;
    private int m_heigth;
    private int m_spawnDelay;
    private int m_maxEntities;
    private BufferedImage m_particleSprite;

    public GenerateurDeParticules(int m_xpos, int m_ypos, int m_width, int m_heigth, int m_spawnDelay, int m_maxEntities, BufferedImage m_particleSprite) {
        this.m_xpos = m_xpos;
        this.m_ypos = m_ypos;
        this.m_width = m_width;
        this.m_heigth = m_heigth;
        this.m_spawnDelay = m_spawnDelay;
        this.m_maxEntities = m_maxEntities;
        this.m_particleSprite = m_particleSprite;
        rng = new Random();
        m_particules = new ArrayList<Particules>();
        m_toRmv = new ArrayList<Particules>();
    }

    public void update(){




    }

    public void draw(Graphics g, JPanel p){
        g.setColor(Color.WHITE);
        for ( Particules pt: m_particules) {
                if(pt.getM_y() + pt.getM_h() < 0){
                    m_toRmv.add(pt);
                    continue;
                }
                pt.draw(g,p);
        }

        for(Particules pt :m_toRmv){
            m_particules.remove(pt);
        }
        m_toRmv.clear();

        if(m_timer >= m_spawnDelay){
            m_timer = 0;
            if(m_particules.size() < m_maxEntities){
                creerParticule();
            }
        }

        m_timer += 12;
    }

    private void creerParticule(){

        int x = rng.nextInt(((m_xpos+m_width) - m_xpos )+1) + m_xpos;
        int y = rng.nextInt(((m_ypos+m_heigth) - m_ypos )+1) + m_ypos;
        int speed = rng.nextInt(5)+1;
        float s = rng.nextFloat()*0.1f;
        m_particules.add(new Particules(x,y,(int)(m_particleSprite.getHeight()*s),(int)(m_particleSprite.getWidth()*s),
                speed , m_particleSprite));
    }

    public void setM_width(int m_width) {
        this.m_width = m_width;
    }

    public void setM_heigth(int m_heigth) {
        this.m_heigth = m_heigth;
    }

    public void setM_ypos(int m_ypos) {
        this.m_ypos = m_ypos;
    }
}
