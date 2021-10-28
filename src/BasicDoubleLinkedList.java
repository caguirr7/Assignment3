


import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Christian Aguirre
 */

public class BasicDoubleLinkedList<T> implements Iterable<T>{

    protected int sizeOfNode;
    protected Node head;
    protected Node tail;

    /**
     * This generic double-linked list relies on a head
     * (reference to first element of the list)
     * and tail (reference to the last element of the list).
     * Both are set to null when the list is empty.
     * Both point to the same element when there is only one element in the list.
     */
    public BasicDoubleLinkedList() {

        this.head = null;
        this.tail = null;
        this.sizeOfNode = 0;

    }

    /**
     * A node structure has only three fields: data and the prev and next references.
     * The class must only define the following entities: an inner class Node
     */
    public class Node {
        protected T data;
        protected Node prev;
        protected Node next;

        public Node() {
            prev = null;
            next = null;
            data = null;
        }


    }

    /**
     * Notice you must not traverse the list to compute the size.
     * This method just returns the value of the instance variable
     * you used to keep track of size.
     * @return the size of the linked list
     */
    public int getSize() {

        return sizeOfNode;
    }

    /**
     * Adds an element to the end of the list. Do
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToEnd(T data) {
        Node newTail = new Node();
        newTail.data = data;

        if(head == null)
            head = newTail;
        else
            tail.next = newTail;

        newTail.prev = tail;
        tail = newTail;
        sizeOfNode++;
        return this;
    }

    /**
     * Adds element to the front of the list.
     * Do not use iterators to implement this method.
     * @param data the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToFront(T data) {
        Node newHead = new Node();
        newHead.data = data;

        if (head == null)
            tail = newHead;
        else
        {
            newHead.next = head;
            head.prev = newHead;
        }
        head = newHead;
        sizeOfNode++;
        return this;
    }
    /**
     Returns but does not remove the first element from the list.
     If there are no elements the method returns null.
     Do not implement this method using iterators
     * @return The data element or null
     */
    public T getFirst() {
        return head.data;
    }

    /**
     *Returns but does not remove the last element from the list.
     * If there are no elements the method returns null.
     * Do not implement this method using iterators.
     * @return the data element or null
     */
    public T getLast() {

        return tail.data;
    }

    /**
     * This method must be implemented using an inner class that
     * implements ListIterator and defines the methods of
     * hasNext(), next(), hasPrevious() and previous().
     * Remember that we should be able to call the hasNext()
     * method as many times as we want without changing what is
     * considered the next element.
     * @throws UnsupportedOperationException  Your next() method should throw
     * NoSuchElementException if there are no more elements
     * (at the end of the list and calling next()
     * or at the beginning of the list and calling previous()).
     * @throws NoSuchElementException You don't need to implement the
     * ListIterator's remove(), add(), nextIndex() and previousIndex()
     * and set() methods, throw UnsupportedOperationException if called.
     */
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException
    {

        return new IteratorList();
    }

    /**
     * An inner class that implements ListIterator (for the iterator method),
     * head and tail references and an integer representing the list size.
     * However only the hasNext(), next(), hasPrevious() and previous()
     * methods of ListIterator need to be implemented,
     * all other methods can throw the UnsupportedOperationException:
     */
    public class IteratorList  implements ListIterator<T>{

        protected Node present;
        protected Node last;
        //protected int index;

        public IteratorList() {

            present = head;
            last = tail;
            //index = 0;
            //present.data = getFirst();
            //last.data = getLast();
        }


        public boolean hasNext() {
            if(present == null)
                throw new NoSuchElementException();
            else
                return true;
        }


        public T next() {

            if(hasNext())
            {
                T dataElement = present.data;
                present = present.next;
                return dataElement;
            }
            return null;
        }


        public boolean hasPrevious() {
            if(last == null)
            {
                throw new NoSuchElementException();
            }
            else
                return true;
        }

        @Override
        public T previous() {

            if(!hasPrevious()){
               throw new NoSuchElementException();

            }
            //T dataElement = last.data;
           // last = last.prev;
            if(last.equals(present)) {


                T dataElement = retrieveLastElement();
                last = last.prev;
                dataElement = retrieveLastElement();
                last = last.prev;
                //present = present.prev;
                return dataElement;
            }

            T dataElement = retrieveLastElement();
            last = last.prev;
            return dataElement;

        }


        @Override
        public int nextIndex() {

            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {

            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException();
        }
        @Override
        public void set(T element) {

            throw new UnsupportedOperationException();

        }
        @Override
        public void add(T e) {

            throw new UnsupportedOperationException();

        }

    }

    /**
     * Removes the first instance of the targetData from the list.
     * Notice that you must remove the elements by performing a
     * single traversal over the list.
     * You may not use any of the other retrieval methods associated
     * with the class in order to complete the removal process.
     * You must use the provided comparator (do not use equals)
     * to find those elements that match the target.
     * Do not implement this method using iterators.
     * @param targetData the data element to be removed
     * @param comparator the comparator to determine equality of data elements
     * @return data element or null
     */
    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator)
    {
        Node present = head;
        if (present != null) {
            do {
                if (comparator.compare(present.data, targetData) == 0) {
                    if (present == head) {
                        head = head.next;
                        head.prev = null;

                    } else if (present == tail) {
                        tail = tail.prev;
                        tail.next = null;

                    } else {
                        present.prev.next = present.next;
                        present.next.prev = present.prev;

                    }
                    sizeOfNode--;
                }
                present = present.next;

            } while (present != null);
        }
        return this;
    }

    /**
     * Removes and returns the first element from the list.
     * If there are no elements the method returns null.
     * Do not implement this method using iterators
     * @return data element or null
     */
    public T retrieveFirstElement() {

        Node temp;
        temp = head;
        head = head.next;
        sizeOfNode--;
        return temp.data;
    }

    /**
     * Removes and returns the last element from the list.
     * If there are no elements the method returns null.
     * Do not implement this method using iterators.
     * @return data element or null
     */
    public T retrieveLastElement() {

        Node temp;
        temp = tail;
        tail = tail.prev;
        sizeOfNode--;
        return temp.data;
    }

    /**
     * Returns an arraylist of the items in the list
     * from head of list to tail of list
     * @return an arraylist of the items in the list
     */
    public ArrayList<T> toArrayList() {

        ArrayList<T> arrList = new ArrayList<>();
        Node presentNode = head;

        while(presentNode != null)
        {
            arrList.add(presentNode.data);
            presentNode = presentNode.next;
        }
        return arrList;
    }





}