public class ResizeableArrayBag<T> implements BagInterface<T> {

private  T[] bag;
private int numberOfEntries;
private boolean integrityOK = false;
private static final int MAX_CAPACITY = 10000;

//SECURE METHODS
public void checkIntegrity()
{
   if (!integrityOK)
      throw new SecurityException("ArrayBag object is corrupt.");
} 

public void ArrayBag(int desiredCapacity)
	{
      if (desiredCapacity <= MAX_CAPACITY)
      {
         // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
         bag = tempBag;
         numberOfEntries = 0;
         integrityOK = true;
      }
      else {
         throw new IllegalStateException("Attempt to create a bag whose" +  "capacity exceeds allowed maximum.");	
      }

}

//UML METHODS
public int getCurrentSize()
{
    return numberOfEntries;
} 

public boolean isFull() 
{
    return numberOfEntries == bag.length;
}

public boolean isEmpty()
{
    return numberOfEntries == 0;
}

public boolean add(T newEntry)
{
	 checkIntegrity();
      boolean result = true;
      if (isFull())
      {
         	result = false;
      }
      else
      { 
   bag[numberOfEntries] = newEntry;
   numberOfEntries++;
      }
      
      return result;
} 


public T remove()
{
    checkIntegrity();
    T result = removeEntry(numberOfEntries - 1);		
    return result;
} 

public boolean remove(T anEntry)
{
 checkIntegrity();
  int index = getIndexOf(anEntry);
  T result = removeEntry(index);         
  return anEntry.equals(result);
} 

public void clear() 
{
    while (!isEmpty())
    remove();
} 

public int getFrequencyOf(T anEntry)
{
 checkIntegrity();
  int counter = 0;
  
  for (int index = 0; index < numberOfEntries; index++)
  {
     if (anEntry.equals(bag[index]))
     {
            counter++;
     } 
    }
    return counter;
}

public boolean contains(T anEntry)
{
    checkIntegrity();
    return getIndexOf(anEntry) > -1; // or >= 0
} 

public T[] toArray() {
    @SuppressWarnings("unchecked")
    T[] result = (T[])new Object[numberOfEntries];
    for (int index = 0; index < numberOfEntries; index++) {
        result[index] = bag[index];
    }
    return result;
}

public int getIndexOf(T anEntry)
    {
    int where = -1;
    boolean found = false;
    int index = 0;

    while (!found && (index < numberOfEntries))
    {
    if (anEntry.equals(bag[index]))
    {
    found = true;
    where = index;
    } // end if
    index++;
     } // end while

    // Assertion: If where > -1, anEntry is in the array bag, and it
    // equals bag[where]; otherwise, anEntry is not in the array

    return where;
}

public T removeEntry(int givenIndex)
{
	T result = null;
  
	if (!isEmpty() && (givenIndex >= 0))
	{
     result = bag[givenIndex];                   // Entry to remove
     bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
     bag[numberOfEntries - 1] = null;            // Remove last entry
     numberOfEntries--;
	} // end if
  
  return result;
}

@Override
public TT union(BagInterface<T> otherBag) {
    // TODO Auto-generated method stub
    return null;
}




//NOTE FOR TOMMOROW: WHHICH ONE OF THESE DO I USE????
// https://pastebin.com/SeZLSnAg
//EROR in this file FROM union(T anotherBag) not being implented btw if you decide to be retarded tomorrow
//WTF A NAME CLASH??


}

