package ua.com.alevel.level2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class StringValidation {

    private String enterTheString(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите вашу строку:");
        return scanner.next();
    }

    private boolean isBalanced(String line){
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(']', '[');
        brackets.put('}', '{');
        brackets.put(')', '(');
        for(var c : line.toCharArray()){
            if(brackets.containsValue(c)){
                stack.push(c);
            }else{
                if (brackets.containsKey(c)) {
                    if(stack.isEmpty() || !brackets.get(c).equals(stack.pop())){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public void outputConsole() {
        var line = enterTheString();
        if(isBalanced(line)){
            System.out.println("Ваша строка сбалансирована");
        }else{
            System.out.println("Ваша строка не сбалансирована:( ");
        }
    }

}
