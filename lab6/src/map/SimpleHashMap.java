package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private static final double loadFactor = 0.75;
	private Entry<K, V>[] map;
	private int capacity;
	private int size;
	
	public static void main(String [] args) {
    	
		SimpleHashMap<Integer, Integer> test = new SimpleHashMap<Integer, Integer>();

		for (int i = 0; i < 10; i++) {
			test.put(i, i);
		}
		
		test.put(17, 2);
		test.put(33, 3);
		test.put(49, 4);

		System.out.println(test.show());
		
	}
	
	private static class Entry<K, V> implements Map.Entry<K, V> {
		
		private K key;
		private V value;
		public Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}

	}

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default load factor (0.75).
	 */
	public SimpleHashMap() {
		this(16);
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		map = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
	}
	
	public String show() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < map.length; i++) {
			
				sb.append(i + "\t");
				
				if (map[i] != null) {
				Entry<K, V> e = map[i];
				
				while (e != null) {
				sb.append(e.toString() + " ");
				e = e.next;		
				}
				
			} else {
				sb.append("empty");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@Override
	public V get(Object o) {
		
		K key = (K) o;
		Entry<K, V> e = find(index(key), key);

		if (e != null) {
			return e.getValue();
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		
		int index = index(key);
		Entry<K, V> e = find(index, key);

		//om key redan finns
		if (e != null) {
			return e.setValue(value);
		}
		
		//annars sätt in ny.
		e = new Entry<K, V>(key, value);
		
		if (map[index] != null) {
			e.next = map[index];
		}

		map[index] = e;
		size++;

		if ((double) size / map.length > 0.75) {
			rehash();
		}

		return null;
	}
	
	private void rehash() {
		
		Entry<K, V>[] old = map;
		map = (Entry<K, V>[]) new Entry[old.length * 2];
		size = 0;

		for (int i = 0; i < old.length; i++) {
			Entry<K, V> e = old[i];

			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;

			}
		}
		
	}

	@Override
	public V remove(Object o) {
		
		//listan är null
		if (!isEmpty()) {
			
			@SuppressWarnings("unchecked")
			K key = (K) o;
			int index = index(key);
			Entry<K, V> toRemove = find(index, key);
			
			if (toRemove != null) {
				Entry<K, V> e = map[index];
				
				//key finns i det första elementet i listan.
				if (e.key.equals(key)) {
					map[index] = e.next;
					size--;
					return e.value;
				}
			
				//key finns senare i listan.
				while (e.next != null) {
					if (e.next.key.equals(key)) {
						Entry<K, V> temp = e.next;
						e.next = e.next.next;
						size--;
						return temp.value;
					}
					e = e.next;
				}
			}
		}
		//key finns inte i listan
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		
		int index = Math.abs(key.hashCode() % capacity);
		return index;
		
	}
	
	/**
	 * @return det Entry-par som har nyckeln key i listan som finns
		på position index i tabellen
	 */
	private Entry<K,V> find(int index, K key){
		
		Entry<K,V> e = map[index];
		
		while(e != null) {
			
			if(e.getKey().equals(key)) {
				return e;
			}
			
			e = e.next;
		}
		return null;
	}
	
}
