package components;

/**
 * Heap provides the basic blueprint for the functioning of a maximum heap, i.e.
 * any heap implementation needs to implement at least the basic heap methods.
 *
 * @author Mika Äärilä
 */
public interface Heap {

    /**
     * Insert a new key into the heap.
     *
     * @param newKey the value of the new key to be inserted.
     */
    void insert(int newKey);

    /**
     * Return the maximum value of the heap.
     *
     * @return the maximum value as type integer.
     */
    int returnMax();

    /**
     * Return the maximum value of the heap and delete it from the heap.
     *
     * @return the maximum value as type integer.
     */
    int deleteMax();
}
