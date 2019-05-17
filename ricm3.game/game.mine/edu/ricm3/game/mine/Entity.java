package edu.ricm3.game.mine;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Entity {
	
	int m_w, m_h;
	int m_x, m_y;
	int m_hitbox_w, m_hitbox_h;
	int m_hitbox_x,m_hitbox_y;
	int m_dx,m_dy;
	int m_nrows, m_ncols;
	int m_no_sprite;
	float m_scale;
	long m_lastMove, m_lastReverse;
	boolean m_canExplode;
	boolean m_explode;
	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	Explosion m_explosion;
	Model m_model;
	
	Entity(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale){
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_x = x;
		m_y = y;
		m_dx = 0;
		m_dy = 0;
		m_scale= scale;
		splitSprite();
		selectSprite(no);
	}
	
	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}
	
	void selectSprite(int no) {
		m_sprite = m_sprites[no];
		m_no_sprite = no;
	}
	
	boolean collision() {
		boolean col = false;
		int x = m_hitbox_x;
		int y = m_hitbox_y;
		int w = m_hitbox_w;
		int h = m_hitbox_h;
		Iterator<Rect> iter = m_model.rects();
		while(iter.hasNext()) {
			Rect r = iter.next();
			if (x+m_dx < r.m_x+r.m_size_x && x+m_dx+w > r.m_x && y+m_dy < r.m_y+r.m_size_y && y+m_dy+h > r.m_y) {
				this.m_dx = 0;
				this.m_dy = 0;
				col = true;
			}
		}
		return col;
	}
	
	boolean collision(Entity e) {
		
		
		return false;
	}
	
}
