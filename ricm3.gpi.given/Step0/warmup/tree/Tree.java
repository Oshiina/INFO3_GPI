package warmup.tree;

/**
 * This is a tree of named node.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */
public class Tree extends Node {

	public static final char pathSeparator = '/';
	public static final String pathSeparatorString = "/";

	public Tree() {
		super("");
	}

	/**
	 * Finds a node corresponding to a path in the tree.
	 * If the path does not exists, throws NotFoundException
	 *  
	 * @param path
	 *          of the searched node
	 * @return the node named by the given path
	 * @throws NotFoundException
	 *           if the path does not exist
	 */
	public Node find(String path) throws NotFoundException {   
		Node n = this;
		int i;
		String paths[] = path.split(pathSeparatorString);
		int size = paths.length; 

		if (size > 0 && !paths[0].equals("")) {
			throw new IllegalArgumentException();
		}
		for (i=1;i<size;i++) {
			n = n.child(paths[i]);
			if(n==null) {
				throw new NotFoundException(path);
			}
		}
		return n;
	}

	/**
	 * Make a path in the tree, leading either to a leaf or to a node.
	 * @throws IllegalStateException
	 *         if the path should be to a leaf but it already exists
	 *         to a node, of if the path should be to a node but it already
	 *         exists to a leaf. 
	 */

	public Node makePath(String path, boolean isLeaf) {
		Node n = this;
		int i=1;
		String paths[] = path.split(pathSeparatorString);
		int size = paths.length; 
		if (size > 0 && !paths[0].equals("")) {
			throw new IllegalArgumentException();
		}
		while (i<size && n!=null) {
			Node n1 = n.child(paths[i]);
			if (n1==null) break;
			n = n1;
			i++;
		}
		while (i<size) {
			if (i==size-1 && isLeaf) {
				Leaf l = new Leaf(n,paths[i]);
				return l;
			}
			Node n2 = new Node(n,paths[i]);
			n = n2;
			i++;
		}
		if(n.children()!=null && isLeaf || !isLeaf && n.children()==null) {
			throw new IllegalStateException();
		}		
		return n;
	}


	public String toString() {
		TreePrinter p = new TreePrinter(this);
		return p.toString();
	}

}
