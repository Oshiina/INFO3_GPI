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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * This class is to illustrate a simple animated character. It does not much,
 * but it shows how to load an animation sprite and how to cycle through the
 * entire animation.
 * 
 * Pay attention to the finite state automaton, that is, the entire behavior
 * behaves as an automaton that progress a step at a time. Look at the method
 * step(long).
 * 
 * About the behavior of the cowboy. The cowboy can rotate around, that is the
 * animation. But since using arms may be dangerous, his gun may explode.
 * 
 * @author Pr. Olivier Gruber
 */

public class Cowboy extends Entity {

	int m_initx, m_inity;
	int m_nsteps;
	int m_hp;
	long m_debutsaut;
	boolean m_saut, m_finsaut;
	boolean m_explode;
	Explosion m_explosion;
	boolean m_fin;

	Cowboy(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(model, no, sprite, rows, columns, x, y, scale);
		updateHitbox();
		m_nsteps = 0;
		m_debutsaut = 0;
		m_saut = false;
		m_initx = x;
		m_inity = y;
		m_explode = false;
		m_hp = 3;
		m_fin = false;
	}

	void setExplosion(BufferedImage sprite, int rows, int columns) {
		m_explosion = new Explosion(m_model, sprite, rows, columns);
	}

	void updateHitbox() {
		m_hitbox_x = (int) (m_x + m_scale * 8);
		m_hitbox_y = (int) (m_y + m_scale * 8);
		m_hitbox_w = (int) ((m_w - 16) * m_scale);
		m_hitbox_h = (int) ((m_h - 15) * m_scale);
	}

	void saut(long now) {
		if (m_saut) {
			long dureesaut = now - m_debutsaut;
			if (m_debutsaut == 0) {
				m_debutsaut = now;
			} else if (dureesaut > 600L || m_finsaut) {
				m_dy = 1;
			}
		} else {
			Iterator<Rect> iter = m_model.rects();
			while (iter.hasNext()) {
				Rect r = iter.next();
				if (m_hitbox_y + m_dy + m_hitbox_h == r.m_y
						&& (m_hitbox_x + m_dx < r.m_x + r.m_size_x && m_hitbox_x + m_dx + m_hitbox_w > r.m_x)) {
					m_debutsaut = 0;
					m_finsaut = false;
				}
			}
		}
	}

	boolean surSol() {
		int x = m_hitbox_x;
		int y = m_hitbox_y;
		int h = m_hitbox_h;
		int w = m_hitbox_w;
		Iterator<Rect> iter = m_model.rects();
		while (iter.hasNext()) {
			Rect r = iter.next();
			if (y + h == r.m_y && x < r.m_x + r.m_size_x && x + w > r.m_x) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Simulation step. This is essentially a finite state automaton. Here, you
	 * decide what happens as time flies.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	void step(long now) {
		if (!m_explode && m_hp > 0) {
			long elapsed = now - m_lastMove;
			if (elapsed > 1L) {
				m_lastMove = now;
				saut(now);
				if (collisionTerrain()) {
					m_dy = 1;
					collisionTerrain();
				}
				m_model.m_cam.m_posx += m_dx;
				m_x += m_dx;
				m_y += m_dy;
				if (!surSol() && !m_saut) {
					m_dy = 1;
				}
				if (m_nsteps == 32) {
					m_nsteps = 0;
					if (m_dx < 0 && m_no_sprite != 7)
						selectSprite(7);
					else if (m_dx < 0)
						selectSprite(8);
					else if (m_dx > 0 && m_no_sprite != 19)
						selectSprite(19);
					else if (m_dx > 0)
						selectSprite(20);
				}
				updateHitbox();
				m_nsteps++;
				if (collisionGhost() || m_y > 760) {
					m_explode = true;
					setExplosion(m_model.m_explosionSprite, 11, 10);
					m_explosion.setPosition(m_initx + m_w, m_y + m_h, m_scale);
					m_explosion.step(now);
					m_hp--;
				}
			}

		} else {
			m_explosion.step(now);
		}
		if(m_x > 6000) {
			m_fin = true;
		}
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	void paint(Graphics g) {
		Image img = m_sprite;
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_initx, m_y, w, h, null);
	}

}
