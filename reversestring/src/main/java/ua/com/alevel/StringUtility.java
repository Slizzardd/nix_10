package ua.com.alevel;

public final class StringUtility {
    private StringUtility() {
    }

    private static String reverser(String text, String out) {
        String line;
        if (text.isEmpty()) {
            line = out;
        } else if (text.charAt(0) == ' ') {
            line = out + " " + reverser(text.substring(1), "");
        } else {
            line = text.substring(1);
            char chars = text.charAt(0);
            line = reverser(line, chars + out);
        }

        return line;
    }


    public static String reverseString(String text) {
        return reverser(text, "");
    }


    public static String reverseString(String text, String substring) {
        return text.replaceAll(substring, reverser(substring, ""));
    }

    public static String reverseString(String text, int firstIndex, int lastIndex) {
        String subString = text.substring(firstIndex, lastIndex + 1);
        String subStringReversed = reverser(subString, "");
        return text.replaceAll(subString, subStringReversed);
    }
}
