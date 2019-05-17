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

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import edu.ricm3.game.GameController;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;
	Label m_hp;
	Music m_player;

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);
		m_hp.setText("hp : "+Integer.toString(m_model.cowboys().m_hp));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// if (Options.ECHO_KEYBOARD)
		// System.out.println("KeyTyped: " + e);
		/*
		 * if (e.getKeyChar() == ' ') { try { /* NEVER, EVER, DO THIS! NEVER LOOP FOR
		 * LONG, NEVER BLOCK, OR NEVER SLEEP, YOU WILL BLOCK EVERYTHING.
		 */
		/*
		 * System.err.println("You should not have done that!");
		 * System.out.println("ZZzzz...."); Thread.sleep(3000);
		 * System.out.println("Hey! I am back"); } catch (InterruptedException ex) { } }
		 */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			m_model.cowboys().m_dx = 1;
		} else if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
			m_model.cowboys().m_dx = -1;
		} else if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			m_model.cowboys().m_dy = -1;
			m_model.cowboys().m_saut = true;
		} else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			m_model.cowboys().m_dy = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			m_model.cowboys().m_dx = 0;
			m_model.cowboys().m_dy = 1;
		} else if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
			m_model.cowboys().m_dx = 0;
			m_model.cowboys().m_dy = 1;
		} else if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			m_model.cowboys().m_dy = 1;
			m_model.cowboys().m_saut = false;
			m_model.cowboys().m_finsaut = true;
		} else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			m_model.cowboys().m_dy = 1;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	public void notifyVisible() {
		Container cont = new Container();
		cont.setLayout(new FlowLayout());
		
		m_hp = new Label("hp : "+Integer.toString(m_model.cowboys().m_hp));
		cont.add(m_hp);


		File file;
		file = new File("game.sample/sprites/Future-RPG.wav");
		// file = new File("game.sample/sprites/Runaway-Food-Truck.wav");
		try {
			m_player = new Music(file);
			cont.add(m_player.getControls());
		} catch (Exception ex) {
		}
		m_game.addSouth(cont);
	}

	@Override
	public void actionPerformed(ActionEvent e) {


	}

}
