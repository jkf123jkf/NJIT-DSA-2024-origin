package oy.tol.tra;

/**
 * A generic and slow Key-Value linear array.
 */
public class KeyValueArray<K extends Comparable<K>, V> implements Dictionary<K,V> {

   private Pair<K, V>[] elements = null;
   private int size = 0;
   private int reallocationCount = 0;

   public KeyValueArray(int initialCapacity) {
      ensureCapacity(initialCapacity);
   }

   public KeyValueArray() {
      ensureCapacity(20);
   }

   @Override
   public Type getType() {
      return Type.SLOW;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void ensureCapacity(int capacity) throws OutOfMemoryError {
      if (capacity < 20) {
         capacity = 20;
      }
      elements = (Pair<K,V>[])new Pair[capacity];
      reallocationCount = 0;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public String getStatus() {
      String status = "KeyValueArray reallocated " + reallocationCount + " times, each time doubles the size\n";
      status += String.format("KeyValueArray fill rate is %.2f%%%n", (size / (double)elements.length) * 100.0);
      return status;
   }

   @Override
   public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
      if (null == key || value == null) throw new IllegalArgumentException("Key or value cannot be null");
      for (Pair<K, V> element : elements) {
         if (element != null && element.getKey().equals(key)) {
            element.setValue(value);
            return true;
         }
      }
      if (size >= elements.length) {
         reallocate(elements.length * 2);
      }
      if (size < elements.length) {
         elements[size++] = new Pair<>(key, value);
         return true;
      }
      return false;
   }

   @Override
   public V find(K key) throws IllegalArgumentException {
      if (null == key) throw new IllegalArgumentException("Key cannot be null");
      for (int i = 0; i < size; i++) {
         if (elements[i] != null && key.equals(elements[i].getKey())) {
            return elements[i].getValue();
         }
      }
      return null;
   }

   @Override
   @java.lang.SuppressWarnings({"unchecked"})
   public Pair<K,V>[] toSortedArray() {
      Pair<K, V>[] sortedArray = (Pair<K,V>[])new Pair[size];
      int newIndex = 0;
      for (int i = 0; i < size; i++) {
         if (elements[i] != null) {
            sortedArray[newIndex++] = new Pair<>(elements[i].getKey(), elements[i].getValue());
         }
      }
      Algorithms.fastSort(sortedArray);
      return sortedArray;
   }

   @Override
   public void compress() throws OutOfMemoryError {
      int firstNullIndex = Algorithms.partitionByRule(elements, size, element -> element == null);
      reallocate(firstNullIndex);
   }

   @java.lang.SuppressWarnings("unchecked")
   private void reallocate(int newSize) throws OutOfMemoryError {
      reallocationCount++;
      Pair<K, V>[] newElements = (Pair<K,V>[])new Pair[newSize];
      for (int i = 0; i < size; i++) {
         newElements[i] = elements[i];
      }
      elements = newElements;
   }
}
