package ua.com.alevel;

public final class StringUtility {
    private StringUtility() {
    }

    private static String reverser(String text) {
        var textToChar = text.toCharArray();
        String reverseString = "";

        for (var i = textToChar.length - 1; i >= 0; i--) {
            reverseString = reverseString + textToChar[i];
        }
        return reverseString;
    }


    public static String reverseString(String text) {
        return reverser(text);
    }


    public static String reverseString(String text, String substring) {
        var reverseSubstringToChar = reverser(substring).toCharArray();
        var textToChar = text.toCharArray();
        String reverseString = "";
        var l = 0;

        for (var i = 0; i < textToChar.length; i++) {
            if (i >= text.indexOf(substring) && i <= text.indexOf(substring) + substring.length()) {
                reverseString = reverseString + reverseSubstringToChar[l];
                l++;
            } else {
                reverseString = reverseString + textToChar[i];
            }
        }
        return reverseString;
    }
}
