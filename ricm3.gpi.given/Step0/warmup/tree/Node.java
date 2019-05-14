package warmup.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a node of a tree.
 * Each node has a name.
 * Therefore, each node is reachable through a path.
 * Each node may be attached an object.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */

public class Node {
	Node m_parent;
	String m_name;
	List<Node> m_children;
	Object m_attachment;

	/**
	 * @param name
	 * @throws IllegalArgumentException if the name contains 
	 *         the character Tree.pathSeparator
	 */
	protected Node(String name) {
		if (name.contains(Tree.pathSeparatorString)) {
			throw new IllegalArgumentException("Contains pathSeraparator");
		}
		this.m_name = name;
		this.m_children = new LinkedList <Node>();
	}

	/**
	 * @param name
	 * @throws IllegalArgumentException if the name contains 
	 *         the character Tree.pathSeparator
	 */
	public Node(Node parent, String name) {
		if (name.contains(Tree.pathSeparatorString)) { 
			throw new IllegalArgumentException("Contains pathSeraparator");
		}
		if(parent.child(name)!=null) {
			throw new IllegalStateException();
		}
		this.m_parent = parent;
		this.m_name = name;
		this.m_children = new LinkedList <Node>();
		parent.m_children.add(this);
	}

	public String toString() {
		if (m_name == null)
			return "";
		return m_name;
	}

	public Node parent() {
		return m_parent;
	}

	public void attach(Object e) {
		m_attachment = e;
	}

	public Object attachment() {
		return m_attachment;
	}

	public void name(String name) {
		m_name = name;
	}

	public String name() {
		return m_name;
	}

	public String path() {
		if (this.m_parent == null) {
			return (this.m_name);
		}
		return (this.m_parent.path()+Tree.pathSeparatorString+this.m_name);		
	}

	public void remove() {
		if (this.m_parent != null) {
			this.m_parent.m_children.remove(this);
		}
	}

	public Iterator<Node> children() {
		return this.m_children.iterator();
	}

	public Node child(String name) {
		Iterator<Node> iter = this.children();
		while(iter.hasNext()) {
			Node n = iter.next();
			if (n.m_name.equals(name)) {
				return n;
			}
		}
		return null;
	}

}
