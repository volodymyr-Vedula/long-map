package de.comparus.opensource.longmap;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class LongMapImpl<V> implements LongMap<V> {
    private int size = 0;
    private int capacity = 1 << 4;
    private Node<Long, V>[] backeds;

    public LongMapImpl() {
        backeds = (Node<Long, V>[]) new Node[capacity];
    }

    public LongMapImpl(int capacity) {
        this.capacity = capacity;
        backeds = (Node<Long, V>[]) new Node[this.capacity];
    }

    public V put(long key, V value) {
        Node<Long, V> node = new Node<>(key, value, null);
        int backed = (int) (key % capacity);
        if (backeds[backed] != null) {
            Node<Long, V> existNode = backeds[backed];
            while (existNode != null) {
                if (node.equals(existNode)) {
                    return existNode.setValue(node.getValue());
                }
                existNode = existNode.getNext();
            }
            node.setNext(backeds[backed]);
        }
        backeds[backed] = node;
        size++;
        return value;
    }

    public V get(long key) {
        int backed = (int) (key % capacity);
        Node<Long, V> node = backeds[backed];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    public V remove(long key) {
        int backed = (int) (key % capacity);
        Node<Long, V> node = backeds[backed];
        Node<Long, V> previousNode = null;
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (previousNode == null)
                    backeds[backed] = node.getNext();
                else
                    previousNode.setNext(node.getNext());
                size--;
                return node.getValue();
            }
            previousNode = node;
            node = node.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (Node<Long, V> nodes : backeds) {
            Node<Long, V> node = nodes;
            while (node != null) {
                if (value == null) {
                    if (node.getValue() == null)
                        return true;
                } else if (value.equals(node.getValue())) {
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    public long[] keys() {
        long[] result = new long[size];
        int index = 0;
        for (Node<Long, V> nodes : backeds) {
            Node<Long, V> node = nodes;
            while (node != null) {
                result[index++] = node.getKey();
                node = node.getNext();
            }
        }
        return result;
    }

    public V[] values() {
        V[] result = null;
        int index = 0;
        for (Node<Long, V> nodes : backeds) {
            Node<Long, V> node = nodes;
            while (node != null) {
                if (result == null)
                    result = (V[]) Array.newInstance(node.getValue().getClass(), size);
                result[index++] = node.getValue();
                node = node.getNext();
            }
        }
        return result;
    }

    public long size() {
        return size;
    }

    public void clear() {
        backeds = (Node<Long, V>[]) new Node[capacity];
        size = 0;
    }
}
