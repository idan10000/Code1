package il.ac.tau.cs.sw1.ex7.histogram;


import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T> {
	
	private List<Map.Entry<T,Integer>> entries;
	private int curIndex;

	public HashMapHistogramIterator(List<Map.Entry<T, Integer>> entries) {
		this.entries = entries;
		entries.sort(new mapEntryComparator());
		curIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return curIndex != entries.size();
	}

	@Override
	public T next() {
		return entries.get(curIndex++).getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}

	private class mapEntryComparator implements Comparator<Map.Entry<T, Integer>>
	{
		@Override
		public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
			int valComp = -1 * o1.getValue().compareTo(o2.getValue());
			return valComp != 0 ? valComp : o1.getKey().compareTo(o2.getKey());
		}
	}
}