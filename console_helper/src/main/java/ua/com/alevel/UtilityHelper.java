package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtilityHelper {
    public static void print(String message) {
        System.out.println(message);
    }

    public static String getString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        try {
            str = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static int getInt() {
        int num = 0;
        try {
            num = Integer.parseInt(getString().trim());
        } catch (NumberFormatException e) {
            System.out.println("Enter number:");
            getInt();
        }
        return num;
    }
}
