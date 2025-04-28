public class HashTable<K, V> {
    // A node for storing a key-value pair and links to the next element of the chain
    public class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int chains;
    private int size;

    public HashTable() {
        this.chains = 11;
        this.chainArray = new HashNode[chains];
        this.size = 0;
    }

    public HashTable(int chains) {
        this.chains = chains;
        this.chainArray = (HashNode<K, V>[]) new HashNode[chains];
        this.size = 0;
    }

    // Hashing function: returns the index of the chain
    private int toHash(K key) {
        return Math.abs(key.hashCode()) % chains;
    }

    // Adds a new key-value pair to the table
    public void put(K key, V value) {
        int index = toHash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    // Returns the value by key
    public V get(K key) {
        int index = toHash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    // Deletes an element by key and returns its value
    public V remove(K key) {
        int index = toHash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {

                if (prev != null) {
                    prev.next = head.next;
                } else {
                    chainArray[index] = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    // Checks whether the value exists in the table
    public boolean contains(V value) {
        for (int i = 0; i < chains; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    // Returns the key by value
    public K getKey(V value) {
        for (int i = 0; i < chains; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    // Returns the number of elements
    public int size() {
        return size;
    }

    // Checks if the table is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Prints all the elements of the table
    public void print() {
        for (int i = 0; i < chains; i++) {
            System.out.print("Bucket " + i + ": ");
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                System.out.print(head + " -> ");
                head = head.next;
            }
            System.out.println();
        }
    }

    public int[] getBucketSizes() {
        int[] sizes = new int[chains];
        for (int i = 0; i < chains; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                sizes[i]++;
                head = head.next;
            }
        }
        return sizes;
    }


}
