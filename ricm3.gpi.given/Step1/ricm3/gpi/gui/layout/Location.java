package ricm3.gpi.gui.layout;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Location {
  private int m_x, m_y;

  public Location() {
    this(0, 0);
  }
  
  public Location(int x, int y) {
    m_x = x;
    m_y = y;
  }

  public void set(int x, int y) {
    m_x = x;
    m_y = y;
  }

  public void translate(int x, int y) {
    m_x += x;
    m_y += y;
  }

  public int x() {
    return m_x;
  }

  public int y() {
    return m_y;
  }
}
