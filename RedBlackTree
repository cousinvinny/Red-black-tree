package cs146F22.Nguyen.project4;

public class RedBlackTree {
	private RedBlackTree.Node root;

	public class Node {
		String key;
		Node parent;
		Node leftChild;
		Node rightChild;
		boolean isRed;
		int color;

		public Node(String data) {
			this.key = data;
			leftChild = null;
			rightChild = null;
		}

		public int compareTo(Node n) { // this < that <0
			return key.compareTo(n.key); // this > that >0
		}

		public boolean isLeaf() {
			if (this.equals(root) && this.leftChild == null && this.rightChild == null)
				return true;
			if (this.equals(root))
				return false;
			if (this.leftChild == null && this.rightChild == null) {
				return true;
			}
			return false;
		}
	}


	public boolean isLeaf(RedBlackTree.Node n) {
		if (n.equals(root) && n.leftChild == null && n.rightChild == null)
			return true;
		if (n.equals(root))
			return false;
		if (n.leftChild == null && n.rightChild == null) {
			return true;
		}
		return false;
	}

	public interface Visitor {
		/**
		 * This method is called at each node.
		 * 
		 * @param n the visited node
		 */
		void visit(Node n);
	}

	public void visit(Node n) {
		System.out.println(n.key);
	}

	public void printTree() { // preorder: visit, go left, go right
		RedBlackTree.Node currentNode = root;
		printTree(currentNode);
	}

	public void printTree(RedBlackTree.Node node) {
		System.out.print(node.key);
		if (node.isLeaf()) {
			return;
		}
		printTree(node.leftChild);
		printTree(node.rightChild);
	}

	// place a new node in the RB tree with data the parameter and color it red.
	/*
	 * (RedBlackTree.Node) need a node object to hold data for insertion into RB tree - insertNode
	 * (RedBlackTree.Node) node object to hold location of node in the tree - x, y.
	 */
	public void addNode(String data) { // this < that <0. this > that >0
		RedBlackTree.Node insertNode = new RedBlackTree.Node(data);
		RedBlackTree.Node y = new RedBlackTree.Node(null);
		insertNode.color = 0;
		insertNode.isRed = true;
		insertNode.leftChild = null;
		insertNode.rightChild = null;
		if (root == null) {
			root = insertNode;
			insertNode.parent = null;
			insertNode.isRed = false;
			insertNode.color = 1;
		} 
		else {
			RedBlackTree.Node x = root;
			while (x != null) {
				y = x;
				if (insertNode.compareTo(x) < 0)
					x = x.leftChild;
				else
					x = x.rightChild;
			}
			insertNode.parent = y;
			if(insertNode.compareTo(y) < 0)
				y.leftChild = insertNode;
			else
				y.rightChild = insertNode;
		}
		fixTree(insertNode);
	}

	public void insert(String data) {
		addNode(data);
	}

	/*
	 * Returns a tree node if the node has the data being looked for
	 * (RedBlackTree.Node) Node object to traverse the RB tree - current
	 */
	public RedBlackTree.Node lookup(String k) {
		if (root == null)
			return null;
		RedBlackTree.Node current = root;
		while (current != null && !current.key.equals(k)) {
			if (k.compareTo(current.key) < 0) {
				if (current.leftChild != null)
					current = current.leftChild;
				else
					return null;
			} else if (k.compareTo(current.key) > 0) {
				if (current.rightChild != null)
					current = current.rightChild;
				else
					return null;
			}
		}
		return current;
	}

	//returns the opposite child from current node
	public RedBlackTree.Node getSibling(RedBlackTree.Node n) {
		if (n.compareTo(n.parent) < 0)
			return n.parent.rightChild;
		else
			return n.parent.leftChild;
	}

	//returns the sibling of current node's parent node
	public RedBlackTree.Node getAunt(RedBlackTree.Node n) {
		if (n.compareTo(n.parent.parent) < 0)
			return n.parent.parent.rightChild;
		else
			return n.parent.parent.leftChild;
	}

	//returns the parent of the parent of the current node.
	public RedBlackTree.Node getGrandparent(RedBlackTree.Node n) {
		return n.parent.parent;
	}

	/*
	 * Rotates the subtree counterclockwise
	 * (RedBlackTree.Node) node object to hold right child of current node
	 */
	public void rotateLeft(RedBlackTree.Node n) {
		RedBlackTree.Node y = n.rightChild;
		n.rightChild = y.leftChild;
		if (y.leftChild != null)
			y.leftChild.parent = n;
		y.parent = n.parent;
		if (n.parent == null)
			root = y;
		else if (n == n.parent.leftChild)
			n.parent.leftChild = y;
		else
			n.parent.rightChild = y;
		y.leftChild = n;
		n.parent = y;
	}

	/*
	 * Rotates the subtree clockwise
	 * (RedBlackTree.Node) node object to hold left child of current node
	 */
	public void rotateRight(RedBlackTree.Node n) {
		RedBlackTree.Node y = n.leftChild;
		n.leftChild = y.rightChild;
		if (y.rightChild != null)
			y.rightChild.parent = n;
		y.parent = n.parent;
		if (n.parent == null)
			root = y;
		else if (n == n.parent.rightChild)
			n.parent.rightChild = y;
		else
			n.parent.leftChild = y;
		y.rightChild = n;
		n.parent = y;
	}

	/*
	 * Recolors the subtree or recolors and rotates depending on 4 cases
	 */
	public void fixTree(RedBlackTree.Node current) {
		if (current == root) {// 1) current is the root node. Make it black and quit.
			current.color = 1;
			current.isRed = false;
			return;
		}

		if (current.color == 0) // Parent is black. Quit, the tree is a Red Black Tree.
			return;

		if (current.isRed == true && current.parent.isRed == true) { // The current node is red and the parent node is
																		// red.
			if (getAunt(current) == null || getAunt(current).isRed == false) {
				// case A, rotate the parent left and then continue recursively fixing the tree
				// starting with the original parent
				if (current.parent == getGrandparent(current).leftChild && current == current.parent.rightChild) {
					RedBlackTree.Node parent = current.parent;
					rotateLeft(current.parent);

					fixTree(parent);
				}
				// case B, rotate the parent right and then continue recursively fixing the tree
				// starting with the original parent
				else if (current.parent == getGrandparent(current).rightChild && current == current.parent.rightChild) {
					RedBlackTree.Node parent = current.parent;
					rotateRight(current.parent);

					fixTree(parent);
				}

				// case C, make the parent black, make the grandparent red. rptate the
				// grandparent to the right and quit
			} else if (current.parent == getGrandparent(current).rightChild && current == current.parent.leftChild) {
				current.parent.isRed = true;
				current.parent.color = 0;
				rotateRight(current.parent);
				return;
			}
			// case D make the parent black, make the grandparent red, rotate the
			// grandparent to the right and quit, the tree is balanced
			else if (current.parent == getGrandparent(current).leftChild && current == current.parent.leftChild) {
				current.parent.isRed = false;
				current.parent.color = 1;
				getGrandparent(current).isRed = true;
				getGrandparent(current).color = 0;
				rotateRight(getGrandparent(current));
				return;
			}
		} else { // if the aunt is red, make the parent black, make the aunt black, make the
					// grandparent red and continue recursively fix up the tree starting with the
					// grandparent
			if (getAunt(current).isRed == true) {
				current.parent.isRed = false;
				current.parent.color = 1;
				getAunt(current).isRed = false;
				getAunt(current).color = 1;
				getGrandparent(current).isRed = true;
				getGrandparent(current).color = 0;
				fixTree(getGrandparent(current));
			}
		}
	}

	//returns true if node has no data, false if there is data in node
	public boolean isEmpty(RedBlackTree.Node n) {
		if (n.key == null) {
			return true;
		}
		return false;
	}

	//returns true if node is a left child, false if a right child
	public boolean isLeftChild(RedBlackTree.Node parent, RedBlackTree.Node child) {
		if (child.compareTo(parent) < 0) {// child is less than parent
			return true;
		}
		return false;
	}

	public void preOrderVisit(Visitor v) {
		preOrderVisit(root, v);
	}

	private static void preOrderVisit(RedBlackTree.Node n, Visitor v) {
		if (n == null) {
			return;
		}
		v.visit(n);
		preOrderVisit(n.leftChild, v);
		preOrderVisit(n.rightChild, v);
	}
}
