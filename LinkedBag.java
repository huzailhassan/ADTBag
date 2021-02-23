/**
 * {@inheritDoc}
 * Implementation of {@link BagInterface} that uses a linked data-structure internally to store data.
 */
public class LinkedBag<T> implements BagInterface<T> {
    
    private Node<T> firstNode;
    private int numberOfEntries;
    
    /**
     * Instantiated the LinkedBag.
     */
    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }
    
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    @Override
    public boolean add(T newEntry) {
        // Add to beginning of chain:
        Node<T> newNode = new Node<>(newEntry);
        newNode.setNext(firstNode); // Make new node reference rest of chain
        firstNode = newNode; // New node is at beginning of chain
        numberOfEntries++;
        return true;
    }
    
    @Override
    public T remove() {
        T result = null;
        if (firstNode != null) {
            result = firstNode.getData();
            firstNode = firstNode.getNext(); // Remove first node from chain
            numberOfEntries--;
        }
        
        return result;
    }
    
    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node<T> nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            nodeN.setData(firstNode.getData()); // Replace located entry with entry in first node
            firstNode = firstNode.getNext(); // Remove first node
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    }
    
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }
    
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return found;
    }
    
    /**
     * Helper method to
     * @param anEntry
     * @return
     */
    private Node<T> getReferenceTo(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return currentNode;     // null if anEntry is not found
    }
    
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.getData())) {
                frequency++;
            }
            currentNode = currentNode.getNext();
        }
        return frequency;
    }
    
    @Override
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
        int index = 0;
        Node<T> currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNext();
        } // end while
        return result;
    }
    
    @Override
    public BagInterface<T> union(BagInterface<T> otherBag) {
        BagInterface<T> newBag = new LinkedBag<>();
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            newBag.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        for (T elem : otherBag.toArray()) {
            newBag.add(elem);
        }
        return newBag;
    }
    
    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag) {
        T[] secondBag = otherBag.toArray();
        BagInterface<T> firstBag = new LinkedBag<>();
        
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            firstBag.add(currentNode.getData());
            currentNode = currentNode.getNext();
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
        BagInterface<T> newBag = new LinkedBag<>();
    
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            T elem = currentNode.getData();
            int freq = getFrequencyOf(elem);
            int otherFreq = otherBag.getFrequencyOf(elem);
            int bagFreq = newBag.getFrequencyOf(elem);
            if (Math.min(freq, otherFreq) - bagFreq > 0) {
                newBag.add(elem);
            }
            
            currentNode = currentNode.getNext();
        }

        return newBag;
    }
    
    // Credit to https://www.geeksforgeeks.org/overriding-equals-method-in-java/
    @Override
    public boolean equals(Object o) {
        
        // If the object is compared with itself then return true   
        if (o == this) {
            return true;
        }
  
        // Check if o is an instance of BagInterface or not
        // "null instanceof [type]" also returns false, so this also works as
        // a null check
        if (!(o instanceof BagInterface)) {
            return false;
        }
        
        // typecast o to BagInterface so that we can compare data members
        BagInterface<T> bag2 = (BagInterface<T>) o;
        
        if(bag2.getCurrentSize() != getCurrentSize()){
            return false;
        }
        
        return difference(bag2).getCurrentSize() == 0;
    }

}
