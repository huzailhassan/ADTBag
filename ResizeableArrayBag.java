import java.util.Arrays;

/**
 * {@inheritDoc} Implementation of {@link BagInterface} that uses an array
 * internally to store data. Implements automatic array resizing.
 * <p>The size of the internal array is doubled every time an entry is added to
 * the bag that causes the internal array to exceed its capacity.</p>
 */
public class ResizeableArrayBag<T> implements BagInterface<T> {
    
    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    /**
     * Maximum allowed capacity of the bag. Set to 10000.
     */
    public static final int MAX_CAPACITY = 10000;
    
    /**
     * Instantiates the ResizeableArrayBag.
     *
     * @param desiredCapacity Base capacity of the bag.
     */
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
    
    /**
     * Checks to see if the bag has been instantiated or not. Throws an {@link
     * SecurityException} if it has not been.
     */
    public void checkIntegrity() {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    }
    
    //UML METHODS
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }
    
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if addition of entry causes bag to exceed
     *                               {@link #MAX_CAPACITY}
     */
    @Override
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
    
    /**
     * Helper method to get the first index of a given entry in the internal
     * array.
     *
     * @param anEntry Entry to find in the internal array.
     * @return Index of given entry, -1 if the entry is not found.
     */
    private int getIndexOf(T anEntry) {
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
    
    /**
     * Helper method to remove entry by internal array index and then shift the
     * remaining entries to compensate.
     *
     * @param givenIndex Index to remove entry at
     * @return The entry that was removed
     * @see #remove()
     * @see #remove(T)
     */
    private T removeEntry(int givenIndex) {
        T result = null;
        
        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex];                   // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null;            // Remove last entry
            numberOfEntries--;
        } // end if
        
        return result;
    }
    
    @Override
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }
    
    @Override
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }
    
    @Override
    public void clear() {
        while (!isEmpty())
            remove();
    }
    
    /* isFull, checkCapacity, and doubleCapacity are all helper methods for
    array resizing.*/
    
    /**
     * Helper method for array resizing. Checks to see if numberOfEntries is the
     * same as the internal array length.
     *
     * @return Whether or not the array is full.
     */
    private boolean isFull() {
        return numberOfEntries == bag.length;
    }
    
    /**
     * Helper method to check if a given capacity exceeds {@link
     * #MAX_CAPACITY}.
     *
     * @param capacity The capacity to check.
     * @throws IllegalStateException If capacity exceeds MAX_CAPACITY.
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
    }
    
    /**
     * Helper method to double internal array capacity.
     */
    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }
    
    @Override
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    }
    
    @Override
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
    
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        // Could replace with System.arraycopy() (faster)
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }
    
    @Override
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
    
    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        T[] secondBag = otherBag.toArray();
        BagInterface<T> firstBag = new ResizeableArrayBag<>(bag.length);
        
        for (int i = 0; i < numberOfEntries; i++) {
            firstBag.add(bag[i]);
        }
        
        for (T t : secondBag) {
            if (firstBag.contains(t)) {
                firstBag.remove(t);
            }
        }
        
        return firstBag;
    }
    
    @Override
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

