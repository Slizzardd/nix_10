package ua.com.alevel.set;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

/*
MathSet не валидное название для такого класса,
ибо в сете нельзя оперировать по индексам, та и сет реализуется на ХэшМапе,
а у нас здесь просто массив уникальных чисел:)
Только не бейте:(
 */

/**
 * кто тебе сказал, что сет не может оперировать индексами???
 * LinkedList в java и лист и очередь,
 * или ты и про LinkedList будешь говорить, что он не оперирует индексами и это не валидное название?))
 * и кто тебе сказал, что сет реализован на мапе???
 * только потому, что так в Java)))
 * */

@SuppressWarnings("unchecked")
public class ArrayUniqueNumbers<E extends Number> {

    private final static int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Number[] arrayUniqueNumbers;

    public ArrayUniqueNumbers() {
        arrayUniqueNumbers = new Number[DEFAULT_CAPACITY];
    }

    public ArrayUniqueNumbers(int capacity) {
        arrayUniqueNumbers = new Number[capacity];
    }

    public ArrayUniqueNumbers(E[] aun) {
        arrayUniqueNumbers = new Number[aun.length];
        addArrayToUnicArra(aun);
    }

    public ArrayUniqueNumbers(E[]... aun) {
        for (E[] e : aun) {
            addArrayToUnicArra(e);
        }
    }

    public ArrayUniqueNumbers(ArrayUniqueNumbers<E> aun) {
        addArrayToUnicArra(aun.toArray());
    }

    public ArrayUniqueNumbers(ArrayUniqueNumbers... aun) {
        for (ArrayUniqueNumbers e : aun) {
            addArrayToUnicArra((E[]) e.toArray());
        }
    }

    public void add(E num) {
        if (isNotContainsDuplicate(num)) {
            if (arrayUniqueNumbers.length == size) {
                increaseArray();
            }
            arrayUniqueNumbers[size] = num;
            size++;
        }
    }

    public void add(E... num) {
        if (arrayUniqueNumbers.length < (size + num.length)) {
            rebuildArray(size + num.length);
        }
        addArrayToUnicArra(num);
    }

    public void join(ArrayUniqueNumbers<E> aun) {
        add(aun.toArray());
    }

    public void join(ArrayUniqueNumbers... aun) {
        for (ArrayUniqueNumbers s : aun) {
            this.add((E[]) s.toArray());
        }
    }

    public void intersection(ArrayUniqueNumbers<E> aun) {
        int mathSetLength = Math.max(arrayUniqueNumbers.length, aun.size());
        Number[] arrayNew = new Number[mathSetLength];
        Number[] arrayIntersection = aun.toArray();
        int mathSetNewIndex = 0;
        for (Number arrayUniqueNumber : arrayUniqueNumbers) {
            for (Number number : arrayIntersection) {
                if (arrayUniqueNumber == null) continue;
                if (compare((E) arrayUniqueNumber, (E) number) == 0) {
                    arrayNew[mathSetNewIndex] = arrayUniqueNumber;
                    mathSetNewIndex++;
                }
            }
        }
        this.arrayUniqueNumbers = arrayNew;
    }

    public void intersection(ArrayUniqueNumbers... aun) {
        for (ArrayUniqueNumbers m : aun) {
            intersection(m);
        }
    }

    public void sortDesc() {
        arrayUniqueNumbers = sortArray((E[]) Arrays.copyOfRange(arrayUniqueNumbers, 0, size), false);
        increaseArray();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            sortedPartOfArray(firstIndex, lastIndex, false);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void sortDesc(E value) {
        int index = getIndexByValue(value);
        sortedPartOfArray(index, size, false);
    }

    public void sortAsc() {
        arrayUniqueNumbers = sortArray((E[]) Arrays.copyOfRange(arrayUniqueNumbers, 0, size), true);
        increaseArray();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            sortedPartOfArray(firstIndex, lastIndex, true);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void sortAsc(E value) {
        int index = getIndexByValue(value);
        sortedPartOfArray(index, size, true);
    }

    public E get(int index) throws InputMismatchException {
        if (indexInBounds(index)) {
            return (E) arrayUniqueNumbers[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E getMax() {
        return (E) sortArray((E[]) Arrays.copyOf(arrayUniqueNumbers, size), false)[0];
    }

    public E getMin() {
        return (E) sortArray((E[]) Arrays.copyOf(arrayUniqueNumbers, size), true)[0];
    }

    public E getAverage() {
        if (size > 0) {

            Number sum = 0;
            Number result;
            for (int i = 0; i < size; i++) {
                sum = sum.floatValue() + arrayUniqueNumbers[i].floatValue();
            }
            result = (sum.floatValue() / size);
            return (E) result;
        } else
            throw new IndexOutOfBoundsException();
    }

    public E getMedian() {
        Number median = 0;
        if (size > 0) {
            Number[] numbersArr = sortArray((E[]) Arrays.copyOf(arrayUniqueNumbers, size), true);
            if (size > 0 && size % 2 == 0) {
                median = (numbersArr[(size / 2) - 1].floatValue() + numbersArr[size / 2].floatValue()) / 2;
            }
            if (size > 0 && size % 2 == 1) {
                median = numbersArr[size / 2];
            }
        }
        return (E) median;
    }

    public E[] toArray() {
        return (E[]) Arrays.copyOfRange(arrayUniqueNumbers, 0, size);
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            return (E[]) Arrays.copyOfRange(arrayUniqueNumbers, firstIndex, lastIndex + 1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public ArrayUniqueNumbers cut(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[size];
        if (indexInBounds(firstIndex, lastIndex)) {
            int indexNumber = 0;
            for (int i = 0; i < size+1; i++) {
                if ((firstIndex <= i && lastIndex+1 > i)) {
                    numbers[indexNumber] = arrayUniqueNumbers[i];
                    indexNumber++;
                }
            }
        }
        return new ArrayUniqueNumbers(numbers);
    }

    public void clear() {
        arrayUniqueNumbers = new Number[arrayUniqueNumbers.length];
        size = 0;
    }

    public void clear(E[] aun) {
        Number[] numbers = new Number[size];
        int indexNumber = 0;
        for (int i = 0; i < size; i++) {
            for (E e : aun) {
                if (compare((E) arrayUniqueNumbers[i], e) != 0) {
                    numbers[indexNumber] = arrayUniqueNumbers[i];
                    indexNumber++;
                }
            }
        }
        arrayUniqueNumbers = numbers;
    }

    private int getIndexByValue(E value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (compare(value, (E) arrayUniqueNumbers[i]) == 0) {
                index = i;
            }
        }
        return index;
    }

    private void sortedPartOfArray(int firstIndex, int lastIndex, boolean b) {
        Number[] sortedPartOfArray = sortArray((E[]) Arrays.copyOfRange(arrayUniqueNumbers, firstIndex, lastIndex + 1), true);
        for (Number number : sortedPartOfArray) {
            arrayUniqueNumbers[firstIndex] = number;
            firstIndex++;
        }
    }

    private Number[] sortArray(E[] array, boolean sortAsc) {
        Number[] result;
        Number[] startFirstParOfArray;
        Number[] endFirstParOfArray;
        Number[] startSecondParOfArray;
        Number[] endSecondParOfArray;

        if (array.length > 1) {
            startFirstParOfArray = Arrays.copyOfRange(array, 0, (array.length / 2));
            endFirstParOfArray = Arrays.copyOfRange(array, (array.length / 2), array.length);
            startSecondParOfArray = sortArray((E[]) startFirstParOfArray, sortAsc);
            endSecondParOfArray = sortArray((E[]) endFirstParOfArray, sortAsc);
            result = getMergedList((E[]) startSecondParOfArray, (E[]) endSecondParOfArray, sortAsc);
        } else {
            result = array;
        }
        return result;
    }

    private E[] getMergedList(E[] array1, E[] array2, boolean sortAsc) {
        Number[] result = new Number[array2.length + array1.length];
        int e1Length = array1.length;
        int e2Length = array2.length;
        int e1Index = 0;
        int e2Index = 0;
        int indexResult = 0;
        int sort;
        if (sortAsc) sort = 1;
        else sort = -1;

        while (e1Index != e1Length || e2Index != e2Length) {
            if (e1Index != e1Length && e2Index != e2Length) {
                if (compare(array1[e1Index], array2[e2Index]) * sort < 0) {
                    result[indexResult] = array1[e1Index];
                    indexResult++;
                    e1Index++;
                } else {
                    result[indexResult] = array2[e2Index];
                    indexResult++;
                    e2Index++;
                }
            } else if (e1Index == e1Length) {
                for (int i = e2Index; i < array2.length; i++) {
                    result[indexResult] = array2[i];
                    indexResult++;
                    e2Index++;
                }
            } else {
                for (int i = e1Index; i < array1.length; i++) {
                    result[indexResult] = array1[i];
                    indexResult++;
                    e1Index++;
                }
            }
        }
        return (E[]) result;
    }

    private int compare(E e1, E e2) {
        return new BigDecimal(e1.toString()).compareTo(new BigDecimal(e2.toString()));
    }

    private int size() {
        return size;
    }

    private boolean indexInBounds(int index) {
        return index >= 0 && index <= size;
    }

    private boolean indexInBounds(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex > size) {
            return false;
        }
        if (lastIndex < 0 || lastIndex > size) {
            return false;
        }
        return firstIndex <= lastIndex;
    }

    private void addArrayToUnicArra(E[] num) {
        for (E number : num) {
            add(number);
        }
    }

    private void increaseArray() {
        int newLength = arrayUniqueNumbers.length + (arrayUniqueNumbers.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        Number[] newMathSet = new Number[newLength];
        int indexCount = 0;
        for (Number number : arrayUniqueNumbers) {
            if (number != null) {
                newMathSet[indexCount] = number;
                indexCount++;
            }
        }
        arrayUniqueNumbers = newMathSet;
        size = indexCount;
    }

    private boolean isNotContainsDuplicate(E number) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arrayUniqueNumbers[i], number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArrayUniqueNumbers{" + Arrays.toString(Arrays.copyOfRange(arrayUniqueNumbers, 0, size)) + "}";
    }
}
