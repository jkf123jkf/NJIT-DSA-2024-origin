package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

    private Object[] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_STACK_SIZE = 10;

    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity <= 2) {
            throw new StackAllocationException("Cannot allocate room for the internal array");
        }
        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
        } catch (OutOfMemoryError e) {
            throw new StackAllocationException("Fail to allocate more room for the stack");
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("The element to push cannot be null");
        }
        if (currentIndex >= capacity - 1) {
            try {
                int newCapacity = 2 * capacity;
                Object[] newArray = new Object[newCapacity];
                System.arraycopy(itemArray, 0, newArray, 0, capacity);
                itemArray = newArray;
                capacity = newCapacity;
            } catch (OutOfMemoryError e) {
                throw new StackAllocationException("Fail to allocate more room for the stack");
            }
        }
        itemArray[currentIndex + 1] = element;
        currentIndex++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("The stack cannot be empty");
        }
        Object popElement = itemArray[currentIndex];
        currentIndex--;
        return (E) popElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (currentIndex == -1) {
            throw new StackIsEmptyException("The stack cannot be empty");
        }
        Object peekElement = itemArray[currentIndex];
        return (E) peekElement;
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index].toString());
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}