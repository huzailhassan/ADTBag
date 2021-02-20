import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T> {
    
    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;
    
    public ResizeableArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } else {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
        }
        
    }
    
    //SECURE METHODS
    public void checkIntegrity() {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    }
    
    //UML METHODS
    public int getCurrentSize() {
        return numberOfEntries;
    }
    
    public boolean isFull() {
        return numberOfEntries == bag.length;
    }
    
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public boolean add(T newEntry) {
        checkIntegrity();
        boolean result = true;
        if (isFull()) {
            doubleCapacity();
        } // end if
        
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        
        return true;
    }
    
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }
    
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }
    
    public void clear() {
        while (!isEmpty())
            remove();
    }
    
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
    }
    
    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }
    
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    }
    
    public int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;
        
        while (!found && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            } // end if
            index++;
        } // end while
        
        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array
        
        return where;
    }
    
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;
        
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            }
        }
        return counter;
    }
    
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        // Could replace with System.arraycopy() (faster)
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }
    
    public T removeEntry(int givenIndex) {
        T result = null;
        
        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex];                   // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null;            // Remove last entry
            numberOfEntries--;
        } // end if
        
        return result;
    }
    
    public BagInterface<T> union(BagInterface<T> otherBag) {
        BagInterface<T> newBag = new ResizeableArrayBag<T>(getCurrentSize() + otherBag.getCurrentSize());
        for (int i = 0; i < getCurrentSize(); i++) {
            newBag.add(bag[i]);
        }
        for (T elem : otherBag.toArray()) {
            newBag.add(elem);
        }
        return newBag;
    }
    
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        T[] secondBag = otherBag.toArray();
        BagInterface<T> firstBag = new ResizeableArrayBag<>(bag.length);
        
        for (int i = 0; i < numberOfEntries; i++)
            firstBag.add(bag[i]);
    
        for (T t : secondBag) {
            if (firstBag.contains(t)) {
                firstBag.remove(t);
            }
        }
        
        return firstBag;
    }
    
    public BagInterface<T> intersection(BagInterface<T> otherBag) {
        // large enough to avoid a resize under all cases
        BagInterface<T> newBag = new ResizeableArrayBag<T>(getCurrentSize() + otherBag.getCurrentSize());
        
        for (T elem : bag) {
            int freq = getFrequencyOf(elem);
            int otherFreq = otherBag.getFrequencyOf(elem);
            int bagFreq = newBag.getFrequencyOf(elem);
            if (Math.min(freq, otherFreq) - bagFreq > 0) {
                newBag.add(elem);
            }
        }
        return newBag;
    }
    
    
}

