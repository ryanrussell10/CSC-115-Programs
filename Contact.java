/* 
 * Name: Ryan Russell
 * ID: V00873387
 * Date: 01/17/2017
 * Filename: Contact.java 
 * Details: CSC 115 Assignment 1
*/

public class Contact {
	private String name;
	private PhoneNumberList	numbers;
	
	// Purpose:
	//	initialize this instance of Contact
	// 	with no PhoneNumber
	//
	public Contact (String theName)
	{
		name = theName;
		numbers = new PhoneNumberList();
	}
	
	// Purpose:
	//	initialize this instance of Contact
	//	add p to the list of phone numbers associated with 
	//	this Contact
	//
	public Contact (String theName, PhoneNumber p)
	{
		name = theName;
		numbers = new PhoneNumberList();
		numbers.add(p);
	}

	// Purpose: 
	//	return the name associated with this instance
	//
	public String getName ()
	{
		return name;
	}

	// Purpose:
	//	change the name associated with this instance to be newName
	//
	public void setName(String newName)
	{
		name = newName;
	}

	// Purpose:
	//	add a new phone number to this contact
	//	there is no maximum number of phone numbers that can be
	//	assigned to a contact.
	//
	public void addNumber (PhoneNumber p)
	{
		numbers.add(p);
	}

	// Purpose:
	//	remove p from the list of PhoneNumbers associated with this contact
	//	if p is not in the list, do nothing.
	//
	public void removeNumber (PhoneNumber p)
	{
		int numbersPosition = numbers.find(p);
		numbers.remove(numbersPosition);
	}

	// Purpose:
	//	return the count of PhoneNumbers associated with this contact
	//
	public int getNumberCount()
	{
		return numbers.size();
	}
	
	// Purpose:
	//	return the phone at index pos from this contact
	//	
	// Pre-condition:
	//	pos >= 0 AND
	//	pos < this.getNumberCount()
	//
	public PhoneNumber getNumber (int pos)
	{
		return numbers.get(pos);
	}

	// Purpose:
	//	return a String representation of this contact	
	//
	public String toString()
	{
		String s = name;
		
		for (int i=0;i<numbers.size();i++)
		{
			s += "\n";
			s += numbers.get(i);
		}
		return s;
	}
}
