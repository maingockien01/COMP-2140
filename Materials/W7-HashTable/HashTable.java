public interface HashTable<E, K> {
    void insert (K key, E value);
    void delete (K key);
    boolean search (K key);
    HashTable<E, K> create ();
    boolean isEmpty ();
    boolean isFull ();
    String print();

}
