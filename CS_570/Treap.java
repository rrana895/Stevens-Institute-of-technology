// Name: Rachi Rana

package assignment_5;

import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/** 
 * treap is a binary search tree (BST)
 * maintain heap priorities 
*/

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		
		/** 
		 * Data Fields
		*/
		
		public E data; //  key  for  the  search
		public int priority; //  random  heap  priority
		public Node<E> left; 
		public Node<E> right;
		
		/** 
		 * 
		 * Constructor
		 * 
		 * new node create given data and priority
		 * 
		 */
		
		public Node(E data, int priority) {
			if(data == null)
				throw new IllegalArgumentException(); // throws exception data null
			this.data = data;
			this.priority = priority;
			left = right = null; 
		}
		
		/** 
		 * 
		 * Methods
		 * */
		
		/** 
		 * 
		 * right rotation
		 * 
		 * return reference to the root of the result
		 * 
		 * 
		 * */
		public Node<E> rotateRight() {
			if(left == null) return this;
			Node<E> sP = left;
			left = left.right;
			sP.right = this;
			return sP;
			
		}
		
		/** 
		 * left rotation
		 * 
		 * retrun reference to the root of the result
		 * 
		 * 
		 * */
		
		public Node<E> rotateLeft() {
			if(right == null) return this;
			Node<E> sP = right;
			right = right.left;
			sP.left = this;
			return sP;
		}
		
		/**
		 * 
		 * String representation of a node
		 * 
		 *  */
		
		public String toString() {
			return this.data.toString();
		}
		
	}
	
	/** 
	 * 
	 * Data Fields
	 * 
	*/
	
	private Random priorityGenerator;
	private Node<E> root;
	private HashSet<Integer> priorities;
	
	/** Constructor 
	 * 
	 * creates an empty treap
	 * 
	 * Initialize priorityGenerator using new Random()
	 * 
	 * */
	
	public Treap() {
		root = null;
		priorityGenerator = new Random();
		priorities = new HashSet<Integer>(); // create set 
	}
	
	/** creates an empty treap  
	 * 
	 * initializes priorityGenerator using new Random(seed)
	 * 
	 * */
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
		priorities = new HashSet<Integer>();
	}
	
	/** Methods*/
	
	/** 
	 * 
	 * add key to treap with priority in random
	 * 
	 * Wrapper function
	 * 
	 * */
	
	public boolean add(E key) {
		int p = priorityGenerator.nextInt();
		while(priorities.contains(p)) {
			p = priorityGenerator.nextInt();}
			
		return add(key, p);
	}
	
	/**
	 *  
	 * add element in key while create a new node
	 * 
	 * */
	
	public boolean add(E key, int priority) {
		if (priorities.contains(priority))
            throw new IllegalArgumentException();
		
		if (root == null) {
            root = new Node<E>(key, priority);
            priorities.add(priority);
            return true;
        }
		
		Stack<Node<E>> way = new Stack<Node<E>>(); // store treap nodes when adding new nodes
		Node<E> now = root;
		Node<E> temp = new Node<E>(key, priority);
		
		while (now != null) {
			if (now.data.compareTo(key) == 0)
				return false; 
			if (now.data.compareTo(key) > 0) {
				way.push(now);
				if (now.left == null) {
					now.left = temp;
					reheap(temp, way);
					return true;
					
				} else
					now = now.left;
				
			} else {
				way.push(now);
				
				if (now.right == null) {
					now.right = temp;
					reheap(temp, way);
					return true;
					
				} else {
					now = now.right;
				}
			}
		}
		return false;
	}
			
	
	/** 
	 * 
	 * parameters that should include the stack 
	 * 
	 * */
	
	private void reheap(Node<E> curr, Stack<Node<E>> way) {
		while (!way.isEmpty()) {
			Node<E> parent = way.pop();
			if (parent.priority < curr.priority) { // heap is at maximum
				if (parent.data.compareTo(curr.data) > 0)
					curr = parent.rotateRight(); // rotate right
				else
					curr = parent.rotateLeft(); // rotate left
				if (!way.isEmpty())
					if (way.peek().left == parent)
						way.peek().left = curr;
					else
						way.peek().right = curr;
				else
					this.root = curr;
			} else
				break;
		}
	}
	
	/**
	 *  
	 * Treap delete
	 * 
	 * */
	
	public boolean delete(E key) {
		if (root == null) {
			return false;
			
		} else if(!find(key)) {
			return false;
			
		} else {
			root = delete(key, root);
			return true;
		}
    }
	
	/** 
	 * 
	 * remove node use of rotation 
	 * 
	 * */
	
	private Node<E> delete(E key, Node<E> root2) {
		// TODO Auto-generated method stub
		if (root2 == null) {
			return root2;
		} else {
			
			if (root2.data.compareTo(key) < 0) {
				root2.right = delete(key, root2.right);
				
			} else {
				
				if (root2.data.compareTo(key) > 0) {
					root2.left = delete(key, root2.left);
					
				} else {
					if (root2.right == null) {
						root2 = root2.left;
					} else if (root2.left == null) {
						root2 = root2.right;
						
					} else {
						if (root2.right.priority < root2.left.priority) {
							root2 = root2.rotateRight();
							root2.right = delete(key, root2.right);
							
						} else {
							root2 = root2.rotateLeft();
							root2.left = delete(key, root2.left);
						}
					}
				}
			}
		}
		return root2;
	}
	
	/** 
	 * node find with key
	 * 
	 * return true if found
	 * 
	 * return false if not found
	 * 
	 * 
	 * */
	private boolean find(Node<E> root, E key) {
		if (root == null)
            return false;
        int i = root.data.compareTo(key);
        return (i < 0) ? find(root.right, key) : (i > 0) ? find(root.left, key) : true;
	}
	
	public boolean find(E key) {
		return find(root, key);
	}
	
	
	private StringBuilder toString(Node<E> curr, int n) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < n; i++)
            build.append("\n");
        if (curr == null)
            return build.append("**null"); // for null
        build.append("(key = ");
        build.append(curr.toString());
        build.append(", priority = ");
        build.append(curr.priority + ")" + "\n");
        build.append(toString(curr.left, n + 1));
        build.append(toString(curr.right, n + 1));
        return build;
    }

    /**
     * 
     * return a string representation of the pre-order traversal of a treap
     * 
     * 
     */
	
    public String toString() {
        return toString(root, 0).toString();
    }
    
	/** 
	 * 
	 * Main method 
	 * 
	 * */
    
    public static void main(String[] args) {
    	
    	Treap<Integer> testTree = new Treap<Integer>();
    	//add
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		
		System.out.println(testTree.toString());
		
		testTree.find(2);//find
		
		
		testTree.delete(1);//delete
		

		System.out.println("\nAfter delete \n" +testTree.toString());//delete
		
		System.out.println("Node finder : "+ testTree.find(4));//find
		System.out.println(testTree.toString());
	}
}


