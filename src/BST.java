import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size = 0;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // Adds an element to the tree
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, val);
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, val);
                    size++;
                    return;
                }
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }
    }

    // Gets the value by key
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }

    // Deletes an item by key
    public void delete(K key) {
        Node parent = null;
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                if (current.left == null || current.right == null) {
                    Node child = (current.left != null) ? current.left : current.right;
                    if (parent == null) {
                        root = child;
                    } else if (parent.left == current) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                } else {
                    Node successorParent = current;
                    Node successor = current.right;
                    while (successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }

                    current.key = successor.key;
                    current.val = successor.val;

                    if (successorParent.left == successor) {
                        successorParent.left = successor.right;
                    } else {
                        successorParent.right = successor.right;
                    }
                }
                size--;
                return;
            }
        }
    }

    // Returns the number of elements
    public int size() {
        return size;
    }

    // Iterator: traverses the in-order tree without recursion
    public Iterable<Entry<K, V>> iterator() {
        return new Iterable<Entry<K, V>>() {
            public Iterator<Entry<K, V>> iterator() {
                return new BSTIterator();
            }
        };
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            Node current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Entry<K, V> next() {
            Node node = stack.pop();
            Entry<K, V> entry = new Entry<>(node.key, node.val);

            if (node.right != null) {
                Node current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            return entry;
        }
    }
}
