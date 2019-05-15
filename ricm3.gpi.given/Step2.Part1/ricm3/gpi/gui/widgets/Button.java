package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;

/**
 * A widget that is a simple button than can be clicked.
 * To know about click events on a button, you need to register
 * an ActionListener on that button.
 * 
 * A button can have a label, that is, a name.
 * 
 * A button may also have two images, one for when the button 
 * is pressed down and the other for when the button is released.
 * 
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */
public class Button extends Component {

  String m_label;
  Font m_font;
  Image m_pressed;
  Image m_released;
  ActionListener m_al;
  
  public Button(Container parent) {
    super(parent);
  }

  public String getLabel() {
    return m_label;
  }
  
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(m_pressed,  m_x, m_y, m_width, m_height);
  }

  public void setActionListener(ActionListener al) {
    m_al = al;
  }

  public void setFont(Font f) {
	  m_font = f;
  }

  public void setLabel(String txt) {
    m_label = txt;
    Window win = Window.getWindow();
    m_font = win.font(Window.MONOSPACED, 12F);
  }

  public void setImages(Image released, Image pressed) {
    m_pressed = pressed;
    m_released = released;
  }

}
