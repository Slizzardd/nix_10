package ua.com.alevel.controller;

import ua.com.alevel.set.ArrayUniqueNumbers;

import static ua.com.alevel.UtilityHelper.print;

public class Controller {
    public static void main(String[] args) {
        print("Показываю один раз, смотрите внимательно!!!!");
        ArrayUniqueNumbers<Integer> arrayUniqueNumbers1 = new ArrayUniqueNumbers<>();
        ArrayUniqueNumbers<Integer> arrayUniqueNumbers2 = new ArrayUniqueNumbers<>(5);
        Integer[] array = {2, 30, 42, 4, 2};
        ArrayUniqueNumbers<Integer> arrayUniqueNumbers3 = new ArrayUniqueNumbers<>(array);

        print("add: ");
        arrayUniqueNumbers1.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        arrayUniqueNumbers2.add(array);
        arrayUniqueNumbers3.add(6, 2, 1, 9, 3);
        print("array1: " + arrayUniqueNumbers1);
        print("array2: " + arrayUniqueNumbers2);
        print("array3: " + arrayUniqueNumbers3);

        print("join: ");
        arrayUniqueNumbers1.join(arrayUniqueNumbers2);
        arrayUniqueNumbers3.join(arrayUniqueNumbers1, arrayUniqueNumbers2);
        print("array3(join 2 && 1): " + arrayUniqueNumbers3);
        print("array1(join 2): " + arrayUniqueNumbers1);
        arrayUniqueNumbers1.clear();
        arrayUniqueNumbers2.clear();
        arrayUniqueNumbers3.clear();

        arrayUniqueNumbers1.add(1, 20, 3, 40, 5);
        arrayUniqueNumbers2.add(1, 12, 4, 40, 5);
        print("array 1: " + arrayUniqueNumbers1);
        print("array 2: " + arrayUniqueNumbers2);
        arrayUniqueNumbers1.intersection(arrayUniqueNumbers2);
        print("intersection array 1, array2: " + arrayUniqueNumbers1);
        arrayUniqueNumbers1.clear();
        arrayUniqueNumbers2.clear();
        arrayUniqueNumbers3.clear();

        arrayUniqueNumbers1 = new ArrayUniqueNumbers<>();
        arrayUniqueNumbers1.add(10, 20, 3, 40, 5);
        arrayUniqueNumbers2.add(3, 40, 5, 60, 7);
        arrayUniqueNumbers3.add(5, 60, 7, 80, 9);
        print("sortDesc: ");
        arrayUniqueNumbers1.sortDesc();
        arrayUniqueNumbers2.sortDesc();
        arrayUniqueNumbers3.sortDesc();
        print("array1: " + arrayUniqueNumbers1);
        print("array2: " + arrayUniqueNumbers2);
        print("array3: " + arrayUniqueNumbers3);

        print("sortAsc: ");
        arrayUniqueNumbers1.sortAsc();
        arrayUniqueNumbers2.sortAsc();
        arrayUniqueNumbers3.sortAsc();
        print("array1: " + arrayUniqueNumbers1);
        print("array2: " + arrayUniqueNumbers2);
        print("array3: " + arrayUniqueNumbers3);

        print("get: ");
        print("get(3)array1: " + arrayUniqueNumbers1.get(3));
        print("get(4)array2: " + arrayUniqueNumbers1.get(4));
        print("get(3)array3: " + arrayUniqueNumbers1.get(3));
        print("Если попытаться обратиться к элементу которого нет - выбросить IndexOutOfBounds...");

        print("getMax: ");
        print("array1: " + arrayUniqueNumbers1.getMax());
        print("array2: " + arrayUniqueNumbers2.getMax());
        print("array3: " + arrayUniqueNumbers3.getMax());

        print("getMin: ");
        print("array1: " + arrayUniqueNumbers1.getMin());
        print("array2: " + arrayUniqueNumbers2.getMin());
        print("array3: " + arrayUniqueNumbers3.getMin());

        print("getAverage: ");
        print("array1: " + arrayUniqueNumbers1.getAverage());
        print("array2: " + arrayUniqueNumbers2.getAverage());
        print("array3: " + arrayUniqueNumbers3.getAverage());

        print("getMedian: ");
        print("array1: " + arrayUniqueNumbers1.getMedian());
        print("array2: " + arrayUniqueNumbers2.getMedian());
        print("array3: " + arrayUniqueNumbers3.getMedian());

        ArrayUniqueNumbers cut = arrayUniqueNumbers1.cut(0, 4);
        print("cut: "+ cut);
    }
}
