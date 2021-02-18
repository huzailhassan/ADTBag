import javax.swing.plaf.basic.BasicTreeUI.TreeToggleAction;

public interface BagInterface<T>  {

    public void checkIntegrity();

    public int getCurrentSize();

    public boolean isEmpty();
    public boolean add(T newEntry);
    public T remove();
    public boolean remove(T anEntry);
    public void clear();
    public boolean contains(T anEntry);
    public T[] toArray();
    public boolean isFull();
    public int getIndexOf(T anEntry);
    public int getFrequencyOf(T anEntry);

    
    public BagInterface<T> union(BagInterface<T> otherBag);
    
    public BagInterface<T> difference(BagInterface<T> otherBag);
    
    public BagInterface<T> intersection(BagInterface<T> otherBag);
    
} 