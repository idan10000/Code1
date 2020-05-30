package il.ac.tau.cs.sw1.ex7.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T> {

    private HashMap<T, Integer> map;

    public HashMapHistogram() {
        map = new HashMap<>();
    }

    @Override
    public Iterator<T> iterator() {
        List<Map.Entry<T, Integer>> mapAsList = new ArrayList<>(map.entrySet());
        return new HashMapHistogramIterator<>(mapAsList);
    }

    @Override
    public void addItem(T item) {
        try {
            addItemKTimes(item, 1);
        } catch (IllegalKValueException ignored) {
        }
    }

    @Override
    public void removeItem(T item) throws IllegalItemException {
        Integer curVal = map.remove(item);
        if (curVal == null)
            throw new IllegalItemException();
    }

    @Override
    public void addItemKTimes(T item, int k) throws IllegalKValueException {
        if (k <= 0)
            throw new IllegalKValueException(k);
        Integer curVal = map.putIfAbsent(item, k);
        if (curVal != null)
            map.replace(item, map.get(item) + k);

    }

    @Override
    public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
        Integer curVal = map.get(item);
        if (curVal == null)
            throw new IllegalItemException();
        if (curVal - k < 0 || k <= 0)
            throw new IllegalKValueException(k);
        if (curVal - k == 0)
            removeItem(item);
        else
            map.replace(item, curVal - k);
    }

    @Override
    public int getCountForItem(T item) {
        return map.getOrDefault(item, 0);
    }

    @Override
    public void addAll(Collection<T> items) {
        for (T item : items) {
            addItem(item);
        }

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<T> getItemsSet() {
        return map.keySet();
    }

    @Override
    public void merge(IHistogram<T> anotherHistogram) {
        for (T item : anotherHistogram.getItemsSet()) {
            try {
                addItemKTimes(item, anotherHistogram.getCountForItem(item));
            } catch (IllegalKValueException ignored) {}
        }
    }


}
