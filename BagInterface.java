/**
 * Interface for Bag object. Can store objects and return them via {@link
 * #toArray()}. Bag does not guarantee the ordering of objects within it.
 *
 * @param <T> The type of object to store in the Bag
 * @see ResizeableArrayBag
 * @see LinkedBag
 */
public interface BagInterface<T> {
    
    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in the bag.
     */
    public int getCurrentSize();
    
    /**
     * Sees whether this bag is empty.
     *
     * @return True if the bag is empty, or false if not.
     */
    public boolean isEmpty();
    
    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    public boolean add(T newEntry);
    
    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or
     *         null.
     */
    public T remove();
    
    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry);
    
    /**
     * Removes all entries from this bag.
     */
    public void clear();
    
    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, or false if not.
     */
    public boolean contains(T anEntry);
    
    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    public int getFrequencyOf(T anEntry);
    
    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in the bag. Note: If
     *         the bag is empty, the returned array is empty.
     */
    public T[] toArray();
    
    /**
     * Takes the union of this Bag and an input {@link BagInterface}.
     * <p>The result may contain duplicate items. For example, if this bag
     * contains Strings "a", "b", "c", and the input bag contains "c", "d", "c",
     * then the result will be a bag containing "a", "b", "c", "c", "d", "c"
     * (note that as with all bag objects <strong>order is not
     * guaranteed</strong>).</p>
     * <p>This method does not affect the contents of either bag.</p>
     *
     * @param otherBag The second bag to be used in the union.
     * @return A new {@link BagInterface} containing all of the elements of both
     *         bags.
     */
    public BagInterface<T> union(BagInterface<T> otherBag);
    
    /**
     * Takes the difference of this Bag and an input {@link BagInterface}. The
     * difference is calculated by looking for elements contained in this bag
     * that the otherBag does not contain.
     * <p>The difference of two bags may contain duplicate items. For example,
     * if this bag contains Strings "a", "c", "c", "c" and the input bag
     * contains "c", "d", then the result will be a bag containing "a", "c", "c"
     * (note that as with all bag objects <strong>order is not
     * guaranteed</strong>). In other words, the difference is not only
     * dependent on which elements are present in each bag, but also the
     * quantity that each is present.</p>
     * <p>This method does not affect the contents of either bag.</p>
     *
     * @param otherBag The bag whose elements are subtracted from this bag (ie
     *                 the second bag in the subtraction operation).
     * @return A new {@link BagInterface} containing all elements in this bag
     *         that the other bag does not contain.
     */
    public BagInterface<T> difference(BagInterface<T> otherBag);
    
    /**
     * Takes the intersection of this Bag and an input {@link BagInterface}. The
     * intersection is elements that are present in both bags.
     * <p>The intersection may contain duplicate items. For example, if this
     * bag contains Strings "a", "c", "c", "d" and the input bag contains "c",
     * "c", "d", the output bag will contain "c", "c", "d". (note that as with
     * all bag objects <strong>order is not guaranteed</strong>). In other
     * words, the difference is not only dependent on which elements are present
     * in each bag, but also the quantity that each is present.</p>
     * <p>This method does not affect the contents of either bag.</p>
     *
     * @param otherBag The second bag to be used in the intersection operation.
     * @return A new {@link BagInterface} with the result of the intersection.
     */
    public BagInterface<T> intersection(BagInterface<T> otherBag);
    
}
