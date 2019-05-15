package warmup.layout;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a container within a tree of containers and components.
 * A container is a component that has children components.
 * The children are managed as an ordered set.
 * Children components are painted on top of their parent container.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Container extends Component {

	protected List<Component> m_children;

	Container() {
		super();
		this.m_children = new LinkedList<Component>();
	}

	public Container(Container parent) {
		super(parent);
		this.m_children = new LinkedList<Component>();
	}

	/**
	 * @return the number of components that are 
	 *         children to this container
	 */
	public int childrenCount() {
		return this.m_children.size();
	}

	/**
	 * @return the component indexed by the given
	 *         index.
	 */
	public Component childrenAt(int i) {
		if(i>=0 && i<this.childrenCount()){
			return this.m_children.get(i);
		}
		return null;
	}

	/**
	 * Select the component, on top, at the given location.
	 * The location is given in local coordinates.
	 * Reminder: children are above their parent.
	 * @param x
	 * @param y
	 * @return this selected component 
	 */
	public Component select(int x, int y) {
		if (this.inside(x, y)) {
			int nb_children = this.childrenCount();
			if (nb_children==0) {
				return this;
			}
			for(int i=0;i<nb_children;i++) {
				Component c = this.childrenAt(i);
				if (c.inside(x, y)) {
					return c.select(x,y);
				}
			}
			return this;
		}
		return null;
	}

}
