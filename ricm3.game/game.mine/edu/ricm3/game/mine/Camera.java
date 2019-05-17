package edu.ricm3.game.mine;


public class Camera {

	int m_posx,m_posy;
	int m_h,m_w;
	Model m_model;
	
	
	Camera(Model m){
		m_posx = 0;
		m_posy = 0;
		m_w = 1024;
		m_h = 768;
		m_model = m;
	}
	
}
