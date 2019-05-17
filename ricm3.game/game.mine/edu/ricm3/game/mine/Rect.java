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


public class Rect {
  Model m_model;
  Color m_color;
  int m_x, m_y;
  int m_size_x, m_size_y;

  long m_lastResize;
  long m_lastMove;
  int gap;

  Rect(Model m, int x, int y,int sizex, int sizey, int r, int v, int b) {
    m_model = m;
    m_x = x;
    m_y = y;
    m_size_x = sizex;
    m_size_y = sizey;
    m_color = new Color(r, v, b);;
  }

  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  void step(long now) {

  }
  
  /**
   * paints this Rect on the screen.
   * 
   * @param g
   */
  void paint(Graphics g) {
	  Camera cam = m_model.m_cam;
	  if((cam.m_posx + cam.m_w >= m_x && cam.m_posx <= m_x) || (cam.m_posx <= m_size_x + m_x && cam.m_posx + cam.m_w >= m_size_x + m_x)) {
		    g.setColor(m_color);
		    g.fillRect(m_x-cam.m_posx, m_y, m_size_x, m_size_y);
	  }
  }

}
