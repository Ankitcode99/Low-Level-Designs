package org.ankitcode99.CustomHashMap;

public class CustomHashMap <K,V> {

    private int INITIAL_SIZE = 1<<4;

    private int MAX_SIZE = 1<<30;

    private float LOAD_FACTOR = 0.75f;

    private int size=0, threshold;

    Entry[] hashTable;

    class Entry<K,V> {
        public K key;
        public V value;
        public Entry next;

        public Entry(K _key, V _value){
            this.key = _key;
            this.value = _value;
        }
    }

    public CustomHashMap() {
        hashTable = new Entry[INITIAL_SIZE];
        threshold = computeThreshold(INITIAL_SIZE);
    }

    private int computeThreshold(int tableCapacity) {
        return (int) Math.ceil(LOAD_FACTOR * tableCapacity);
    }

    private int computeNextPowerOf2(int num) {
        int n = num - 1;
        n |= n>>>1;
        n |= n>>>2;
        n |= n>>>4;
        n |= n>>>8;
        n |= n>>>16;

        return (n<0) ? 1 : (n>=MAX_SIZE) ? MAX_SIZE : n+1;
    }

    public void put(K key, V value){
        if(key == null) {
            handleNullKeyInsertion(key, value);
            return;
        }
        int bucketNumber = key.hashCode() % hashTable.length;

        Entry node = hashTable[bucketNumber];
        if(node == null){
            Entry newNode = new Entry(key, value);
            hashTable[bucketNumber] = newNode;
        }else{
            Entry previousNode = node;
            while(node != null){
                /**
                 * if a key value pair is already present update the value of that key
                 * */
                if(node.key == key){
                    node.value = value;
                    return;
                }
                previousNode = node;
                node = node.next;
            }

            previousNode.next = new Entry(key, value);
        }

        if(++size > threshold){
            resizeTable();
        }
    }

    private void resizeTable() {
        int newCapacity = computeNextPowerOf2(size);
        Entry[] newTable = new Entry[newCapacity];

        for(Entry node: hashTable){
            if(node.key == null) {
                newTable[0] = node;
            }else if(node.key != null) {
                K key = (K) node.key;
                int bucketNumber = key.hashCode() % newCapacity;
                Entry nodeList = newTable[bucketNumber];
                if(nodeList == null) {
                    newTable[bucketNumber] = node;
                }else{
                    Entry previousNode = nodeList;
                    while(nodeList != null) {
                        previousNode = nodeList;
                        nodeList = nodeList.next;
                    }
                    previousNode.next = node;
                }
            }
        }

        hashTable = newTable;
        threshold = computeThreshold(newCapacity);
    }

    private void handleNullKeyInsertion(K key, V value) {
        Entry node = hashTable[0];
        while(node != null){
            if(node.key==null){
                node.value = value;
                return;
            }
        }
        Entry nullEntry = new Entry(key, value);
        nullEntry.next = hashTable[0];
        hashTable[0] = nullEntry;
    }

    public V get(K key){
        int bucketNumber = key.hashCode() % hashTable.length;
        Entry node = hashTable[bucketNumber];

        while(node != null){
            if(node.key.equals(key)){
                return (V) node.value;
            }
            node = node.next;
        }
        return null;
    }

}
