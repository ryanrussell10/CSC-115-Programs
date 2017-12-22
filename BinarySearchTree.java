/* Name: Ryan Russell
 * ID: V00873387
 * Date: March 30th, 2017
 * Fiilename: BinarySearchTree.java 
 * Details: CSC 115 Assignment 4 
 */


import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree<K extends Comparable<K>, V> {

	public static final int BST_PREORDER = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER = 3;

	// These are package friendly for the TreeView class
	BSTNode<K, V> root;
	int count;

	int findLoops;
	int insertLoops;

	public BinarySearchTree() {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}

	public void resetInsertLoops() {
		insertLoops = 0;
	}
	

	// Additional helper methods implemented to assist recursive methods below:

	/*
	 * This method does an in-order traversal of a binary search tree and is
	 * used for the recursive implementation of entryList(int which).
	 */
	private void doInOrder(BSTNode<K, V> n, List<Entry<K, V>> l) {
		if (n == null)
			return;

		doInOrder(n.left, l);
		l.add(new Entry<K, V>(n.key, n.value));
		doInOrder(n.right, l);
	}

	/*
	 * This method does an pre-order traversal of a binary search tree and is
	 * used for the recursive implementation of entryList(int which).
	 */
	private void doPreOrder(BSTNode<K, V> n, List<Entry<K, V>> l) {
		if (n == null)
			return;

		l.add(new Entry<K, V>(n.key, n.value));
		doPreOrder(n.left, l);
		doPreOrder(n.right, l);
	}

	/*
	 * This method does an post-order traversal of a binary search tree and is
	 * used for the recursive implementation of the entryList(int which) method.
	 */
	private void doPostOrder(BSTNode<K, V> n, List<Entry<K, V>> l) {
		if (n == null)
			return;

		doPostOrder(n.left, l);
		doPostOrder(n.right, l);
		l.add(new Entry<K, V>(n.key, n.value));
	}

	/*
	 * This method is used in the recursive implementation of the height()
	 * method, and is used to determine the height of a Binary Search Tree.
	 */
	private int doHeight(BSTNode<K, V> t) {
		if (t == null) {
			return 0;
		} else {
			int heightRightSubtree = 1 + doHeight(t.right);
			int heightLeftSubtree = 1 + doHeight(t.left);

			if (heightRightSubtree > heightLeftSubtree) {
				return (heightRightSubtree);
			} else {
				return (heightLeftSubtree);
			}
		}
	}

	/*
	 * This method is used in the recursive implementation of the insert(K k, V
	 * v) method and is used to insert a new BSTNode into a Binary Search Tree.
	 */
	private BSTNode<K, V> doInsert(BSTNode<K, V> n, K key, V value) {
		if (n == null) {
			count++;
			return new BSTNode<K, V>(key, value);
		}
		// If the two keys are the same, change the value of that node to the new key.
		if (key.compareTo(n.key) == 0) {
			n.value = value;
			return n;
		}
		
		//Otherwise, go either left or right.
		if (key.compareTo(n.key) < 0) {
			insertLoops++;
			n.left = doInsert(n.left, key, value);
		} else {
			insertLoops++;
			n.right = doInsert(n.right, key, value);
		}

		return n;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree. If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// the tree is a valid binary search tree
	//
	public void insert(K k, V v) {

		root = doInsert(root, k, v);

		/*
		 * BSTNode<K,V> n = new BSTNode(k,v); BSTNode<K,V> p = root;
		 * 
		 * if (root == null) { root = n; count++; return; } while(true) {
		 * if(n.key.compareTo(p.key) == 0) { p.value = n.value; return; } else
		 * if(n.key.compareTo(p.key) < 0) { if (p.left != null) { p = p.left; }
		 * else { p.left = n; count++; return; } } else { if (p.right != null) {
		 * p = p.right; } else { p.right = n; count++; return; } } }
		 */
	}

	//
	// Purpose:
	//
	// Return the value stored at key. Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	// the tree is a valid binary search tree
	//
	// Returns:
	// the value stored at key
	//
	// Throws:
	// KeyNotFoundException if key isn't in the tree
	//
	public V find(K key) throws KeyNotFoundException {
		BSTNode<K, V> p = root;

		while (true) {
			if (p == null) {
				throw new KeyNotFoundException();
			}
			// If the two keys are the same, return that node's value.
			if (key.compareTo(p.key) == 0) {
				return p.value;
			} 
			// Otherwise, go either left or right.
			else if (key.compareTo(p.key) < 0) {
				findLoops++;
				p = p.left;
			} else {
				findLoops++;
				p = p.right;
			}
		}
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	// the number of nodes in the tree.
	public int size() {
		return count;
	}

	//
	// Purpose:
	// Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	//
	// Purpose:
	//
	// Return the height of the tree. We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	// See the assignment PDF and the test program for
	// examples of height.
	//
	public int height() {
		return doHeight(root);
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	// From wikipedia (they call it breadth-first), the algorithm for level
	// order is:
	//
	// levelorder()
	// q = empty queue
	// q.enqueue(root)
	// while not q.empty do
	// node := q.dequeue()
	// visit(node)
	// if node.left != null then
	// q.enqueue(node.left)
	// if node.right != null then
	// q.enqueue(node.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	public List<Entry<K, V>> entryList() {
		List<Entry<K, V>> list = new LinkedList<Entry<K, V>>();
		LinkedList<BSTNode<K, V>> nodes = new LinkedList<BSTNode<K, V>>();

		nodes.addLast(root);

		while (!nodes.isEmpty()) {

			BSTNode<K, V> p = nodes.removeFirst();

			// If p is not null, add a new entry to the 
			// list and add the left and right to the "queue".
			if (p != null) {
				list.add(new Entry<K, V>(p.key, p.value));
				nodes.addLast(p.left);
				nodes.addLast(p.right);
			}
		}
		return list;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal
	// specified by the parameter which.
	//
	// If which is:
	// BST_PREORDER perform a pre-order traversal
	// BST_POSTORDER perform a post-order traversal
	// BST_INORDER perform an in-order traversal
	//
	public List<Entry<K, V>> entryList(int which) {
		List<Entry<K, V>> list = new LinkedList<Entry<K, V>>();

		// Depending on the input type of traversal, 
		// traverse the BST and return a list of all the entries in the tree.
		if (which == BST_PREORDER) {
			doPreOrder(root, list);
		} else if (which == BST_INORDER) {
			doInOrder(root, list);
		} else if (which == BST_POSTORDER) {
			doPostOrder(root, list);
		}

		return list;
	}

	// Your instructor had the following private methods in his solution:
	// private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private int doHeight (BSTNode<K,V> t)
}