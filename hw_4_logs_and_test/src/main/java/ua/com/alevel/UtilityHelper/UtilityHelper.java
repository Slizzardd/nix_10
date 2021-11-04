package ua.com.alevel.UtilityHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtilityHelper {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void print(String message) {
        System.out.println(message);
    }

    public static String getString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        try {
            str = reader.readLine();
        } catch (IOException e) {
            LOGGER_ERROR.error(e.getMessage() + " getString");
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static int getInt() {
        int num = 0;
        try {
            num = Integer.parseInt(getString().trim());
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error(e.getMessage() + "getInt");
            System.out.println("Enter number:");
            getInt();
        }
        return num;
    }
}
