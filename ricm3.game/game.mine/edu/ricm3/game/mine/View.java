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
import java.util.Iterator;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.gray;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	// Controller m_ctr;

	public View(Model m) {
		m_model = m;
		// m_ctr = c;
	}

	public void step(long now) {

	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);
		// m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Paint our model, grabbing the elements,
		// in our case, the squares.

		// in our case, the squares.
		Iterator<Rect> iter = m_model.rects();
		while (iter.hasNext()) {
			Rect s = iter.next();
			s.paint(g);
		}

		Cowboy cowboys = m_model.cowboys();
		if(!cowboys.m_explode && cowboys.m_hp > 0) {
			cowboys.paint(g);
		}
		else {
			cowboys.m_explosion.paint(g);
		}
		
		for (int i = 1; i <= m_model.m_nbghosts; i++) {
			Ghost ghost = m_model.getGhost(i);
			ghost.paint(g);
		}

	}
}
