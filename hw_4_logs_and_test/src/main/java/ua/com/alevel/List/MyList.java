package ua.com.alevel.List;

import java.util.Arrays;

public class MyList<T> {
    private int sizeList = 0;

    Object[] array = new Object[2];
    public int length = array.length;

    public void add(T num) {
        if (sizeList == array.length - 1) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[sizeList] = num;
        sizeList++;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int getLength() {
        return array.length;
    }
}
