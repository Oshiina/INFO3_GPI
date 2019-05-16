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
import java.util.Random;

public class Rect {
  Model m_model;
  Color m_color;
  int m_x, m_y;
  int m_size_x, m_size_y;
  int m_red, m_green, m_blue;

  long m_lastResize;
  long m_lastMove;
  int gap;

  Rect(Model m, int x, int y,int sizex, int sizey) {
    m_model = m;
    m_x = x;
    m_y = y;
    m_size_x = sizex;
    m_size_y = sizey;
    Random rand = new Random();
    m_red = rand.nextInt(255);
    m_green = rand.nextInt(255);
    m_blue = rand.nextInt(255);
    m_color = new Color(m_red, m_green, m_blue);
  }

  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  void step(long now) {

  }

  void changeColor(int dc) {

  }
  
  /**
   * paints this Rect on the screen.
   * 
   * @param g
   */
  void paint(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(m_x, m_y, m_size_x, m_size_y);
  }

}