import javax.swing.plaf.basic.BasicTreeUI.TreeToggleAction;

public interface BagInterface<T>  {

    public void checkIntegrity();
    public void ArrayBag(int desiredCapacity);

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

    public T union(BagInterface<T> otherBag);
} 