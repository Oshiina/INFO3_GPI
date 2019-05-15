package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Location;

/**
 * A canvas to draw on.
 * 
 * The intended usage is to subclass this canvas, usually
 * listening to mouse and keyboard events
 * 
 * The default painting for a canvas is to fill the canvas
 * with the background color. Your subclass may override 
 * this behavior.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */

public class Canvas extends Component {

	int x1,y1,x2,y2;
	boolean init;
	
  public Canvas(Container parent) {
    super(parent);
    init = true;
  }
  
  @Override
  public void paint(Graphics g) {
	  if(init) {
		System.out.println("coucou");
		g.setColor(m_bgColor);
		Location l = new Location(0,0);
		this.toGlobal(l);
		g.fillRect(l.x(), l.y(), m_width, m_height);
		init = false;
	  }
	  else {
		  System.out.println("nonstp");
		  g.setColor(m_fgColor);
		  g.drawLine(x1, y1, x2, y2);
	  }
	}

  public static class CL implements MouseListener {
		boolean m_down;
		String m_msg;
		Canvas m_c;

		public CL(Canvas c,String msg) {
			m_msg = msg;
			m_c = c;
		}

		@Override
		public void mouseReleased(int x, int y, int buttons) {
			m_down = false;
			m_c.x2 = x;
			m_c.y2 = y;
			m_c.repaint();
			
		}

		@Override
		public void mousePressed(int x, int y, int buttons) {
			m_down = true;
			m_c.x1 = x;
			m_c.y1 = y;
		}

		@Override
		public void mouseMoved(int x, int y) {
			if(m_down) {

			}
		}

		@Override
		public void mouseExited() {
			
		}

		@Override
		public void mouseEntered(int x, int y) {
			
		}

  }
}

