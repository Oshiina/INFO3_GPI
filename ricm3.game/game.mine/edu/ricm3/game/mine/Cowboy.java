/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.mine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * This class is to illustrate a simple animated character.
 * It does not much, but it shows how to load an animation sprite
 * and how to cycle through the entire animation.
 * 
 * Pay attention to the finite state automaton, that is, 
 * the entire behavior behaves as an automaton that progress 
 * a step at a time. Look at the method step(long).
 * 
 * About the behavior of the cowboy.
 * The cowboy can rotate around, that is the animation.
 * But since using arms may be dangerous, his gun may explode.
 * 
 * @author Pr. Olivier Gruber
 */

public class Cowboy {
	BufferedImage m_sprite;
	int m_w, m_h;
	int m_hitbox_w, m_hitbox_h;
	int m_x, m_y;
	int m_hitbox_x,m_hitbox_y;
	int m_dx,m_dy;
	int m_nrows, m_ncols;
	int m_idx;
	int dir;
	float m_scale;
	long m_lastMove, m_lastReverse;
	boolean m_canExplode;
	boolean m_explode;
	BufferedImage[] m_sprites;
	Explosion m_explosion;
	Model m_model;

	Cowboy(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
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
		updateHitbox();
	}

	/*
	 * This is about splitting the animated sprite into the basic
	 * sub-images constituting the animation. 
	 */
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
	

	void setExplosion(BufferedImage sprite, int rows, int columns) {
		m_explosion = new Explosion(m_model,sprite, rows, columns);
	}
	
	
	void updateHitbox() {
		m_hitbox_x = (int) (m_x);
		m_hitbox_y = (int) (m_y + m_scale * 8);
		m_hitbox_w = (int) (m_w * m_scale);
		m_hitbox_h = (int) ((m_h - 15) * m_scale);
	}

	/**
	 * Simulation step. This is essentially a finite state automaton.
	 * Here, you decide what happens as time flies.
	 * @param now
	 *          is the current time in milliseconds.
	 */
	void step(long now) {
		if (m_canExplode && m_model.rand.nextInt(10000) > 9990) {
			m_explode = true;
			int w = (int)(m_scale * m_w);
			int h = (int)(m_scale * m_h);
			int x = m_x + w/2;
			int y = m_y + h/2;
			m_explosion.setPosition(x, y, 1F);
			return;
		}
		long elapsed = now - m_lastMove;
		if (elapsed > 5L) {
			m_lastMove = now;
			collision();
			m_x += m_dx;
			m_y += m_dy;
			updateHitbox();
		}
	}

	void collision() {
		Iterator<Rect> iter = m_model.rects();
		while(iter.hasNext()) {
			Rect r = iter.next();
			int x = m_hitbox_x;
			int y = m_hitbox_y;
			int w = m_hitbox_w;
			int h = m_hitbox_h;
			if (x+m_dx < r.m_x+r.m_size_x && x+m_dx+w > r.m_x && y+m_dy < r.m_y+r.m_size_y && y+m_dy+h > r.m_y) {
				this.m_dx = 0;
				this.m_dy = 0;
			}
			
		}
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	void paint(Graphics g) {
		if (m_explode) {
			m_explosion.paint(g);
			if (m_explosion.done()) {
				m_explode = false;
				m_canExplode = false;
			}
		} else {
			Image img = m_sprites[m_idx];
			int w = (int)(m_scale * m_w);
			int h = (int)(m_scale * m_h);
			g.drawImage(img, m_x, m_y, w, h, null);
			g.setColor(Color.red);
			g.drawRect(m_x, m_y, w, h);
			g.setColor(Color.blue);
			g.drawRect(m_hitbox_x, m_hitbox_y,m_hitbox_w,m_hitbox_h);
		}
	}

}
