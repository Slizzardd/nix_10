package ua.com.alevel.util;

import java.security.SecureRandom;

public final class GenerateCardNumber {

    //Prefix bank for card
    private static final int BANK_IDENTIFY_NUMBER = 5168;

    private GenerateCardNumber(){
        throw new IllegalStateException("Utility class.");
    }

    private static String getRandomFourNumber(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(plus());
        }
        return stringBuilder.toString();
    }

    private static int plus() {
        return new SecureRandom().nextInt(10);
    }

    public static String createCardNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(BANK_IDENTIFY_NUMBER).append(" ")
                .append(getRandomFourNumber()).append(" ")
                .append(getRandomFourNumber()).append(" ")
                .append(getRandomFourNumber());
        return sb.toString();
    }
}
