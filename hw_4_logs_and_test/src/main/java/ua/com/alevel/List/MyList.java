package ua.com.alevel.List;

import java.util.Arrays;

public class MyList<T> {
    private int sizeList = 0;

    Object[] array = new Object[1];
    public int length = array.length;

    public void add(T num) {
        if (sizeList == array.length) {
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

    public int getSizeList(){
        return sizeList;
    }
    public int getLength() {
        return array.length;
    }
}
