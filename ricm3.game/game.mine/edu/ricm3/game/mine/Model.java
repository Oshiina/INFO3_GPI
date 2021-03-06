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
	LinkedList<Entity> m_entity;
	BufferedImage m_cowboySprite;
	BufferedImage m_explosionSprite;
	BufferedImage m_ghostSprite;
	Random rand = new Random();
	Camera m_cam;
	int m_nbghosts;

	public Model() {
		loadSprites();

		m_cam = new Camera(this);

		createMap();
		createEntities();

	}

	void createMap() {
		m_rects = new LinkedList<Rect>();
		m_rects.add(new Rect(this, 0, 600, 3000, 200, 100, 255, 20));
		m_rects.add(new Rect(this, 0, 620, 3000, 80, 88, 41, 0));
		m_rects.add(new Rect(this, 3200, 600, 3000, 200, 100, 255, 20));
		m_rects.add(new Rect(this, 3200, 620, 3000, 80, 88, 41, 0));
		m_rects.add(new Rect(this, 584, 450, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 884, 300, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 1401, 300, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, -300, 0, 300, 800, 88, 41, 0));
		m_rects.add(new Rect(this, 1800, 280, 700, 200, 100, 255, 20));
		m_rects.add(new Rect(this, 1800, 300, 700, 800, 88, 41, 0));
		m_rects.add(new Rect(this, 2800, 0, 1200, 300, 88, 41, 0));
		m_rects.add(new Rect(this, 4300, 450, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 4400, 380, 600, 400,100, 255, 20));
		m_rects.add(new Rect(this, 4400, 400, 600, 400,88,41,0));
		m_rects.add(new Rect(this, 5600, 450, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 5300, 300, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 5900, 250, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 5600, 100, 100, 20, 255, 125, 0));
		m_rects.add(new Rect(this, 6200, 0, 900, 800, 88, 41, 0));
	}

	void createEntities() {
		m_entity = new LinkedList<Entity>();
		m_entity.add(new Cowboy(this, 2, m_cowboySprite, 4, 6, 200, 477, 3F));
		m_entity.add(new Ghost(this, 1, m_ghostSprite, 4, 10, 800, 500, 2F));
		m_entity.add(new Ghost(this, 1, m_ghostSprite, 4, 10, 1900, 180, 2F));
		m_entity.add(new Ghost(this, 1, m_ghostSprite, 4, 10, 2800, 500, 2F));
		m_nbghosts = 3;
	}

	@Override
	public void shutdown() {

	}

	public Cowboy cowboys() {
		return (Cowboy) m_entity.get(0);
	}

	public Iterator<Rect> rects() {
		return m_rects.iterator();
	}

	public Iterator<Entity> entities() {
		return m_entity.iterator();
	}

	Ghost getGhost(int index) {
		return (Ghost) m_entity.get(index);
	}

	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		cowboys().step(now);
		for (int i = 1; i <= m_nbghosts; i++) {
			getGhost(i).step(now);
		}
	}

	private void loadSprites() {
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		File imageFile = new File("game.sample/sprites/winchester.png");
		try {
			m_cowboySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Long explosion set; png file; 64x64 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		imageFile = new File("game.mine/sprites/explosion01_set_64.png");
		try {
			m_explosionSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.mine/sprites/game_sprite_png_547353.png");
		try {
			m_ghostSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

}
