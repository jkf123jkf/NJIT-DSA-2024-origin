package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {



    private TreeNode<K, V> rootNode;
    private int elementCount = 0;
    private int maxDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return elementCount;
    }


    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something
     * about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter
     * value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        String statusMessage = "Tree has max depth of " + maxDepth + ".\n";
        statusMessage += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        rootNode.accept(visitor);
        statusMessage += "Min path height to bottom: " + visitor.minHeight + "\n";
        statusMessage += "Max path height to bottom: " + visitor.maxHeight + "\n";
        statusMessage += "Ideal height if balanced: " + Math.ceil(Math.log(elementCount)) + "\n";
        return statusMessage;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Neither key nor value can be null.");
        }
        if (rootNode == null) {
            rootNode = new TreeNode<>(key, value);
            elementCount++;
            maxDepth = 1;
            return true;
        } else {
//            int depthBefore = TreeNode.currentAddTreeDepth;
            int added = rootNode.insert(key, value, key.hashCode());
            int depthAfter = TreeNode.currentAddTreeDepth;
            TreeNode.currentAddTreeDepth = 0;
            if (added > 0) {
                elementCount++;
            }
            if (depthAfter > maxDepth) {
                maxDepth = depthAfter;
            }
            return added > 0;
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        return (rootNode != null) ? rootNode.find(key, key.hashCode()) : null;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {

    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(elementCount);
        rootNode.accept(visitor);
        Pair<K, V>[] sortedArray = visitor.getArray();
        Algorithms.fastSort(sortedArray);
        return sortedArray;
    }

    @Override
    public void compress() throws OutOfMemoryError {

    }













}
