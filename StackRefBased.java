/* 
 * Name: Ryan Russell
 * ID: V00873387
 * Date: 03/10/2017
 * Filename: StackRefBased.java
 * Details: CSC 115 Assignment 3 Part 1
*/


/*
 * The class StackRefBased<T> contains methods used for operations on the Stack ADT.
 * This particular class is implemented using generics.
 */
public class StackRefBased<T> implements Stack<T> {
	
	int count = 0;
	StackNode<T> head; 

	/*
	 * StackRefBased constructor.
	 */
    public StackRefBased() {
    	head = null;
    	count = 0;
    }

    /*
     * Returns the size of the stack.
     */
    public int size() {
        return count;
    }

    /*
     * Returns true if the stack is empty and false otherwise.
     */
    public boolean isEmpty() {
        if(count == 0) {
        	return true;
        } else {
        	return false;
        }
    }

    /*
     * Pushes a node onto the stack.
     * @param data.
     */
    public void push(T data) {
        StackNode<T> n = new StackNode(data);
        n.next = head;
        head = n;
        count++;
    }

    /*
     * Pops the node off the top of the stack and returns the data within.
     * @throws StackEmptyException.
     */
    public T pop() throws StackEmptyException {
        if(count == 0) {
        	throw new StackEmptyException("Can't pop right now!");
        } else {
        	count--;
        	T output = head.data;
        	head = head.next;
        	return output;
        }
    }

    /*
     * Returns the data of the node at the top of the stack.
     * @throws StackEmptyException.
     */
    public T peek() throws StackEmptyException {
        if(count == 0) {
        	throw new StackEmptyException("There's nothing to peek at!");
        }
        return head.data;
    }

    /*
     * Clears the stack.
     */
    public void makeEmpty() {
    	head = null;
    	count = 0;
    }

    /*
     * Implementation of the toString method.
     */
    public String toString() {
    	String s = "";
    	StackNode<T> p = head;
    	
    	while(p != null) {
    		s =  p.data + s;
    		if(p.next != null) {
    			s = " " + s;
    		}
    		p = p.next;
    	}
    	return s;
   	}
}

