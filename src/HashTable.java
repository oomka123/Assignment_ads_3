public class HashTable<K, V> {

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

    private int toHash(K key) {
        return Math.abs(key.hashCode()) % chains;
    }

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

}
