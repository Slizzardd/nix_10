package ua.com.alevel.level2;


import java.util.Scanner;

public class BinaryTree {
    static final int COUNT = 10;
    static Scanner scanner = new Scanner(System.in);

    public static class Node {
        private final long value;
        private Node left;
        private Node right;

        Node(long x) {
            this.value = x;
            left = null;
            right = null;
        }
    }

    private int maxDepthTree(Node tree) {
        if (tree == null) {
            return 0;
        }
        var nLeft = maxDepthTree(tree.left);
        var nRight = maxDepthTree(tree.right);
        return (nLeft > nRight) ? (nLeft + 1) : (nRight + 1);
    }

    private void addBinaryTree(Node node, long val) {
        if (val < node.value) {
            if (node.left == null) {
                node.left = new Node(val);
                System.out.println("Добавено " + val + " к левой ветке " + node.value);
            } else {
                addBinaryTree(node.left, val);
            }
        } else if (val > node.value) {
            if (node.right == null) {
                node.right = new Node(val);
                System.out.println("Добавлено " + val + " к правой ветке " + node.value);
            } else {
                addBinaryTree(node.right, val);
            }
        } else {
            System.out.println("Вы пытаетесь добавить значение которое уже есть в дереве");
        }
    }

    public void outputConsole() {
        Node node;
        var line = 0;
        while (true) {
            try {
                System.out.println("Пожалуйста, введите значение для корня дерева: ");
                node = new Node(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели недопустимое значение, попробуйте ещё раз:( ");
            }
        }
        run(line, node);
    }

    private void run(int line, Node node) {
        do {
            System.out.println("Добавить значение(1): \nНайти максимальную глубину(2): \nРаспечатать дерево(3): \nВыйти в меню(4): ");
            do {
                try {
                    var operation = scanner.nextLine();
                    line = Integer.parseInt(operation);
                } catch (NumberFormatException a) {
                    System.out.println("Вы ввели недействительное число, попробуйте ещё раз:( ");
                }
            } while (line < 0 || line > 4);
            switch (line) {
                case 1 -> {
                    String lined;
                    long num = 0L;
                    do {
                        try {
                            System.out.println("Пожалуйста, введите значение(0 для выхода в меню): ");
                            lined = scanner.nextLine();
                            num = Long.parseLong(lined);
                            if (num == 0) {
                                break;
                            }
                            addBinaryTree(node, num);
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели недействительное число, попробуйте ещё раз:( ");
                        }
                    } while (num != 0);
                }
                case 2 -> System.out.println("Максимальная глубина: " + maxDepthTree(node));
                case 3 -> print2dTree(node);
            }
        } while (line != 4);
    }
    //Stack overflow рулит!!!(Да, украл, вывод матрицы от туда,
    // но мы же должны уметь гуглить:)
    // я бы сделал красивее вывод, но у меня не
    // получилось преобразовать этот класс в класс<A>:(
    //https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
    private void print2dTree(Node root){
        printTree(root, 0);
    }
    private void printTree(Node root, int space){
        if (root == null)
            return;
        space += COUNT;
        printTree(root.right, space);
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.value + "\n");
        printTree(root.left, space);
    }
}