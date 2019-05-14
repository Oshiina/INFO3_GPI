package warmup.tree.tests;

import warmup.tree.Leaf;
import warmup.tree.Node;
import warmup.tree.NotFoundException;
import warmup.tree.Tree;

public class Test00 {

	public static void ensure(boolean cond) {
		if (!cond)
			throw new RuntimeException("Failed assert.");
	}

	public static void main(String args[]) {
		try {
			test00();
			System.out.println("test00 passed");
		} catch (NotFoundException e) {
			System.out.println("test00 failed");
		}	
		try {
			test01();
			System.out.println("test01 failed");
		} catch (IllegalArgumentException e) {
			System.out.println("test01 passed");
		}
		test02();
		System.out.println("test02 passed");
		try {
			test03();
			System.out.println("test03 failed");
		} catch (NotFoundException e) {
			System.out.println("test03 passed");
		}
		try {
			test04();
			System.out.println("test04 passed");
		} catch (NotFoundException e) {
			System.out.println("test04 failed");
		}
		try {
			test05();
			System.out.println("test05 passed");
		} catch (NotFoundException e) {
			System.out.println("test05 failed");
		}
		try {
			test06();
			System.out.println("test06 failed");
		} catch (IllegalArgumentException e) {
			System.out.println("test06 passed");
		}
		try {
			test07();
			System.out.println("test07 failed");
		} catch (IllegalStateException e) {
			System.out.println("test07 passed");
		}
		try {
			test08();
			System.out.println("test08 failed");
		} catch (IllegalStateException e) {
			System.out.println("test08 passed");
		}
		try {
			test09();
			System.out.println("test09 failed");
		} catch (IllegalStateException e) {
			System.out.println("test09 passed");
		}
		try {
			test10();
			System.out.println("test10 failed");
		} catch (IllegalStateException e) {
			System.out.println("test10 passed");
		}
		test11();
		System.out.println("test11 passed");

	}


	// test si child et find fonctionnent	
	public static void test00() throws NotFoundException {
		Tree t = new Tree();
		ensure(t.name().equals(""));
		Node n = new Node(t,"toto");
		ensure(n.parent().equals(t));
		ensure(t.child("toto").equals(n));
		ensure(t.find("/toto").equals(n));
	}


	// vérification d'exception en cas de creation avec un '/'
	public static void test01() throws IllegalArgumentException{
		Tree t = new Tree();
		new Node(t,"to/to");
	}

	//vérification de null en cas d'inexsitance du noeud
	public static void test02(){
		Tree t = new Tree();
		ensure(t.child("titi")==null);
	}

	// vérification d'exception si noeud non trouvé avec find
	public static void test03() throws NotFoundException {
		Tree t = new Tree();
		t.find("/titi");
	}

	// vérification noeud bien trouvé avec find
	public static void test04() throws NotFoundException{
		Tree t = new Tree();
		Node n = new Node(t,"toto");
		ensure(t.find("/toto").equals(n));
	}

	// vérification bonne création avec makepath
	public static void test05() throws NotFoundException {
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
	public static void test06() throws IllegalArgumentException {
		Tree t = new Tree();
		String path;
		path = "toto";
		t.makePath(path, false);
	}

	// verification d'exception en cas de node sur leaf sur makepath
	public static void test07() throws IllegalStateException{
		Tree t = new Tree();
		String path;
		path = "/toto/tata";
		t.makePath(path, true);
		t.makePath(path, false);
	}

	// verification d'exception en cas de leaf sur node sur makepath
	public static void test08() throws IllegalStateException{
		Tree t = new Tree();
		String path;
		path = "/toto/tata";
		t.makePath(path, false);
		t.makePath(path, true);
	}

	// verification leaf impossible avec leaf en parent
	public static void test09() throws IllegalStateException{
		Tree t = new Tree();
		Leaf l = new Leaf(t,"toto");
		new Leaf(l,"tata");
	}
	
	// verification node impossible avec leaf en parent
	public static void test10() throws IllegalStateException{
		Tree t = new Tree();
		Leaf l = new Leaf(t,"toto");
		new Node(l,"tata");
	}

	//verification path fonctionne
	public static void test11() {
		Tree t = new Tree();
		String path = "/toto/tata";
		Node n = t.makePath(path,false);
		ensure(n.path().equals(path));
	}
}
