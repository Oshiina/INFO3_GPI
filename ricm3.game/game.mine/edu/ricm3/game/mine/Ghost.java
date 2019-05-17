package edu.ricm3.game.mine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Ghost extends Entity{

	int gauche;

	Ghost(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(model, no, sprite, rows, columns, x, y, scale);
		updateHitbox();
		gauche = -1;
		m_dx = -1;
	}

	void updateHitbox() {
		m_hitbox_x = (int) (m_x + m_scale * 4);
		m_hitbox_y = m_y;
		m_hitbox_w = (int) ((m_w - 18) * m_scale);
		m_hitbox_h = (int) (m_h * m_scale );
	}

	void step(long now) {
		long elapsed = now - m_lastMove;		
		if (elapsed > 4L) {
			m_lastMove = now;
			if(collisionTerrain()) {
				gauche = -gauche;
				m_dx = gauche;
			}
			m_x += m_dx; 
			updateHitbox();
		}

	}


void paint(Graphics g) {
	Image img = m_sprite;
	int w = (int)(m_scale * m_w);
	int h = (int)(m_scale * m_h);
	 Camera cam = m_model.m_cam;
	  if((cam.m_posx + cam.m_w >= m_x && cam.m_posx <= m_x) || (cam.m_posx <= m_w + m_x && cam.m_posx + cam.m_w >= w + m_x) || (cam.m_posx >= m_x && cam.m_posx + cam.m_w <= m_x + w)) {
		  g.drawImage(img, m_x-cam.m_posx, m_y, w, h, null);
	  }
}

}
