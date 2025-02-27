package intermediate2.arraylist;

import java.util.Arrays;

public class CustomArrayListV1 {

	private static final int DEFAULT_CAPACITY_VALUE = 5;

	private Object[] elementData;
	private int size = 0;

	public CustomArrayListV1() {
		elementData = new Object[DEFAULT_CAPACITY_VALUE];
	}

	public CustomArrayListV1(int capacity) {
		elementData = new Object[capacity];
	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object set(int index, Object element) {
		Object o = get(index);
		elementData[index] = element;
		return o;
	}

	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (elementData[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public void add(Object e) {
		elementData[size] = e;
		size++;
	}

	public int size() {
		return size;
	}

	public String toString() {
		return Arrays.toString(elementData);
	}
}
