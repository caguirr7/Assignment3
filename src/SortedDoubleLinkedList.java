
import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

    /**
     * Implements a generic sorted double list using a provided Comparator.
     * It extends BasicDoubleLinkedList class.
     */
    Comparator<T> comparator;


    /**
     * Creates an empty list that is associated with the specified comparator.
     * @param comparator2 Comparator to compare data elements
     */
    public SortedDoubleLinkedList(Comparator<T> comparator2) {
        super();
        this.comparator = comparator2;
    }

    /**
     * Inserts the specified element at the correct position in the sorted list.
     * Notice we can insert the same element several times.
     * Your implementation must traverse the list only once in order to
     * perform the insertion. Do not implement this method using iterators.
     * Notice that you don't need to call any of the super class methods in
     * order to implement this method.
     * @param data the data to be added to the list
     * @return a reference to the current object
     */
    public SortedDoubleLinkedList<T> add(T data) {
        if (data == null)
            return this;

        Node temp = new Node();
        temp.data = data;
        if(head == null)
        {
            head = tail = temp;
            sizeOfNode++;
            return this;
        }

        if(comparator.compare(head.data, data) > 0)
        {
            temp.next = head;
            head.prev = temp;
            head = temp;
            sizeOfNode++;
            return this;
        }
        if(comparator.compare(tail.data, data) < 0)
        {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            sizeOfNode++;
            return this;
        }
        Node current = head.next;
        if (current != null) {
            do {
                if (comparator.compare(current.data, data) > 0) {
                    temp.next = current;
                    current.prev.next = temp;
                    temp.prev = current.prev;
                    current.prev = temp;
                    sizeOfNode++;
                    return this;
                } else {
                    current = current.next;
                }
            } while (current != null);
        }
        sizeOfNode++;
        return this;
    }


    /**
     *This operation is invalid for a sorted list.
     * An UnsupportedOperationException will be generated using the message
     * "Invalid operation for sorted list."
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     * @throws UnsupportedOperationException if method is called
     */

    public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Invalid operation for sorted list");

    }

    /**
     *This operation is invalid for a sorted list.
     * An UnsupportedOperationException will be generated using the message
     * "Invalid operation for sorted list."
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     * @throws UnsupportedOperationException if method is called
     */
    public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();


    }

    /**
     * Implements the iterator by calling the super class iterator method.
     * @return an iterator positioned at the head of the list
     */
    @Override
    public ListIterator<T> iterator()
    {
        return super.iterator();
    }


    /**
     * Implements the remove operation by calling the super class remove method.
     * @param data the data element to be removed
     * @param comparator the comparator to determine equality of data elements
     * @return data element or null
     */
    public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
        super.remove(data, comparator);
        return this;

    }

}