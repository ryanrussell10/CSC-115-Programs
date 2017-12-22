
/*
 * Name: Ryan Russell
 * Student Number: V00873387
 */

public class IntegerLinkedList implements IntegerList
{
	
	IntegerNode head;
	IntegerNode tail;
	int count = 0;
	
	public IntegerLinkedList()
	{
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x)
	{
		IntegerNode n = new IntegerNode();
		n.value = x;
		n.prev = null;
		n.next = head;
		head = n;
		
		if(count == 0)
		{
			tail = n;
		} else {
			n.next.prev = n;
		}
		count++;
	}


	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x)
	{
		IntegerNode n = new IntegerNode();
		n.value = x;
		n.next = null;
		n.prev = tail;
		tail = n;
		
		if(count == 0) 
		{
			head = n;
		} else {
			n.prev.next = n;
		}
		count++;
		
	}
	
	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos)
	{
		//Case 1: Adding item to the front
		if(pos == 0)
		{
			addFront(x);
		}
		
		//Case 2: Adding item to the back
		else if(pos == (count))
		{
			addBack(x);
		} 
		
		//Case 3: Adding an item elsewhere in the list
		else {
			
			IntegerNode n = new IntegerNode();
			IntegerNode temp = head;
			
			n.setValue(x);
			for(int index = 0; index < (pos - 1); index++)
			{
				temp = temp.next;
			}
			n.next = temp.next;
			n.prev = temp;
			temp.next = n;
			temp.next.prev = n; 
			
			count++;
		}
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size()
	{
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int  get (int pos)
	{
		IntegerNode p = head;	
		int index = 0;
		
		while(index != pos)
		{
			index++;
			p = p.next;
		}
		return p.value;
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear()
	{
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value)
	{
		IntegerNode current = head;
	
		/*for(int position = 0; (position < count) && (current != null); position++)
		{
			if(current.getValue() == value)
			{
				removeAt(position);
				position--;
			}
			current = current.next;
		} */
		
		
		int index = 0;
		while((index < count) && (current != null))
		{
			if(current.value == value)
			{
				removeAt(index);
				index--;
			}
			index++;
			current = current.next;
		} 
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos)
	{
		IntegerNode frontTemp = head;
		IntegerNode backTemp = tail;
		
		//Case 1: Only one item in list
		if(count == 1)
		{
			head = null;
			tail = null;
			count--;
		}
		
		//Case 2: Removing last item in the list
		else if(pos == (count - 1))
		{
			backTemp.prev.next = null;
			tail = backTemp.prev;
			count--;
		}
		
		//Case 3: Removing first item in the list
		else if(pos == 0)
		{
			frontTemp.next.prev = null;
			head = frontTemp.next;
			count--;
		}
		
		//Case 4: Removing another list item
		else 
		{
			int index = 0;
			while(index != count)
			{
				if(index == pos - 1)
				{
					
					frontTemp.next = frontTemp.next.next;
					frontTemp = frontTemp.next;
					frontTemp.prev = frontTemp.prev.prev;
					
					count--;
					break;
				}
				frontTemp = frontTemp.next;
				index++; 
			} 
		}
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString()
	{
		IntegerNode p = head;
		String s = "{";
		while (p != null)
		{
			s += p.value;
			if (p.next != null)
				s+= ",";
			p = p.next;
		}
		s += "}";
		return s;
	}
}
