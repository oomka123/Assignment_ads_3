Overview
This assignment consists of two main parts:

Implementing and testing a custom Hash Table (MyHashTable).

Implementing a Binary Search Tree (BST) without using recursion.

Part 1 — Hash Table
1.1. Implement MyHashTable
Create a generic hash table class MyHashTable<K, V>.

Use chaining to handle collisions (linked lists in each bucket).

Implement standard operations:

put(K key, V value)

get(K key)

remove(K key)

contains(V value)

getKey(V value)

size()

isEmpty()

print() (printing elements in all buckets)

1.2. Testing
Create a custom key class MyTestingClass.

Implement your own hashCode() method manually (do not use Objects.hash() or other utilities).

Insert 10,000 random elements into the hashtable.

After insertion, print the number of elements in each bucket (chain).

Tune your hashCode() method to achieve a uniform distribution of keys across the buckets.

Part 2 — Binary Search Tree (BST)
2.1. Implement BST
Implement a generic Binary Search Tree class BST<K extends Comparable<K>, V>.

Recursion is not allowed — all methods must be iterative.

Implement basic operations:

put(K key, V value)

get(K key)

delete(K key)

2.2. Extensions
Add a size field to track the number of nodes in the tree.

Implement in-order traversal in the iterator() method using no recursion.

Modify the iterator so that both key and value are accessible during iteration.
