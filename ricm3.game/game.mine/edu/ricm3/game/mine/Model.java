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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import edu.ricm3.game.GameModel;


public class Model extends GameModel {
	LinkedList<Rect> m_rects;
	BufferedImage m_cowboySprite;
	BufferedImage m_explosionSprite;
	Cowboy m_cowboys;
	Random rand = new Random();

	public Model() {
		loadSprites();
		m_cowboys = new Cowboy(this,2,m_cowboySprite,4,6,200,477,3F);

		m_rects = new LinkedList<Rect>();
		
		m_rects.add(new Rect(this, 0,600,1024,200,100,255,20));
		m_rects.add(new Rect(this, 0,620,1024,80,88,41,0));
		m_rects.add(new Rect(this, 300,400,200,20,255,125,0));
	}

	@Override
	public void shutdown() {

	}


	public Cowboy cowboys() {
		return m_cowboys;
	}

	public Iterator<Rect> rects() {
		return m_rects.iterator();
	}
	/**
	 * Simulation step.
	 * 
	 * @param now
	 *          is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {

		m_cowboys.step(now);
	}

	private void loadSprites() {
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size
		 * Krasi Wasilev ( http://freegameassets.blogspot.com)
		 */
		File imageFile = new File("game.sample/sprites/winchester.png");
		try {
			m_cowboySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Long explosion set; png file; 64x64 px sprite size
		 * Krasi Wasilev ( http://freegameassets.blogspot.com)
		 */
		imageFile = new File("game.sample/sprites/explosion01_set_64.png");
		try {
			m_explosionSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

}
