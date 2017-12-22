
import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

	BinarySearchTree<K, V> treeMap;

	public BSTMap() {
		treeMap = new BinarySearchTree<K, V>();
	}

	public boolean containsKey(K key) {
		try {
			if (treeMap.find(key) != null)	{
				return true;
			}
		} 
		catch (KeyNotFoundException exception) {
		}
		return false;
	
		
	}

	public V get(K key) throws KeyNotFoundException {
		return treeMap.find(key);
	}

	public List<Entry<K, V>> entryList() {
		return treeMap.entryList();
	}

	public void put(K key, V value) {
		treeMap.insert(key, value);
	}

	public int size() {
		return treeMap.size();
	}

	public void clear() {
		treeMap.clear();
	}

	public int getGetLoopCount() {
		return treeMap.getFindLoopCount();
	}

	public int getPutLoopCount() {
		return treeMap.getInsertLoopCount();
	}

	public void resetGetLoops() {
		treeMap.resetFindLoops();
	}

	public void resetPutLoops() {
		treeMap.resetInsertLoops();
	}
}