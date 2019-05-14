package warmup.tree.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warmup.tree.Leaf;
import warmup.tree.Node;
import warmup.tree.NotFoundException;
import warmup.tree.Tree;

public class Test00JUnit {

	public static void ensure(boolean cond) {
		if (!cond)
			throw new RuntimeException("Failed assert.");
	}

	@Before
	public void before() {
		// Executed before each test
	}

	@After
	public void after() {
		// Executed after each test
	}



	// test si child et find fonctionnent
	@Test
	public void test00() throws NotFoundException {
		Tree t = new Tree();
		ensure(t.name().equals(""));
		Node n = new Node(t,"toto");
		ensure(n.parent().equals(t));
		ensure(t.child("toto").equals(n));
		ensure(t.find("/toto").equals(n));
	}


	// vérification d'exception en cas de creation avec un '/'
	@Test(expected = IllegalArgumentException.class)
	public void test01() throws IllegalArgumentException{
		Tree t = new Tree();
		new Node(t,"to/to");
	}

	//vérification de null en cas d'inexsitance du noeud
	@Test
	public void test02(){
		Tree t = new Tree();
		ensure(t.child("titi")==null);
	}

	// vérification d'exception si noeud non trouvé avec find
	@Test(expected = NotFoundException.class)
	public void test03() throws NotFoundException {
		Tree t = new Tree();
		t.find("/titi");
	}

	// vérification noeud bien trouvé avec find
	@Test
	public void test04() throws NotFoundException{
		Tree t = new Tree();
		Node n = new Node(t,"toto");
		ensure(t.find("/toto").equals(n));
	}

	// vérification bonne création avec makepath
	@Test
	public void test05() throws NotFoundException {
		Tree t = new Tree();
		String path;
		path = "/toto";
		Node n = t.makePath(path, false);
		ensure(t.find("/toto").equals(n));
		path = "/toto/tata/titi";
		n = t.makePath(path,true);
		ensure(t.find("/toto/tata/titi").equals(n));
	}

	// vérification exepction en cas de mauvais path sur makepath
	@Test(expected = IllegalArgumentException.class)
	public void test06() throws IllegalArgumentException {
		Tree t = new Tree();
		String path;
		path = "toto";
		t.makePath(path, false);
	}

	// verification d'exception en cas de node sur leaf sur makepath
	@Test(expected = IllegalStateException.class)
	public void test07() throws IllegalStateException{
		Tree t = new Tree();
		String path;
		path = "/toto/tata";
		t.makePath(path, true);
		t.makePath(path, false);
	}

	// verification d'exception en cas de leaf sur node sur makepath
	@Test(expected = IllegalStateException.class)
	public void test08() throws IllegalStateException{
		Tree t = new Tree();
		String path;
		path = "/toto/tata";
		t.makePath(path, false);
		t.makePath(path, true);
	}

	// verification leaf impossible avec leaf en parent
	@Test(expected = IllegalStateException.class)
	public void test09() throws IllegalStateException{
		Tree t = new Tree();
		Leaf l = new Leaf(t,"toto");
		new Leaf(l,"tata");
	}

	// verification node impossible avec leaf en parent
	@Test(expected = IllegalStateException.class)
	public void test10() throws IllegalStateException{
		Tree t = new Tree();
		Leaf l = new Leaf(t,"toto");
		new Node(l,"tata");
	}

	//verification path fonctionne
	@Test
	public void test11() {
		Tree t = new Tree();
		String path = "/toto/tata";
		Node n = t.makePath(path,false);
		ensure(n.path().equals(path));
	}
}
