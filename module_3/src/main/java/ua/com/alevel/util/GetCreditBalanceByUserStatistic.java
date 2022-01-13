package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import static ua.com.alevel.constant_module.CardCreditBalanceConstant.*;

public class GetCreditBalanceByUserStatistic {

    private static final int SIZE_FOR_GIVE_1000_CREDIT_BALANCE = 25000;

    private GetCreditBalanceByUserStatistic() {
        throw new IllegalStateException("Utility class.");
    }


    public static Double getCreditBalanceByHistoryUsers(User user, Account account) {
        double resultBalance = 0;
        if (user.getAccounts().size() == 0) {
            switch (account.getCardType()) {
                case UNIVERSAL -> resultBalance = UNIVERSAL_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //2000
                case UNIVERSAL_GOLD -> resultBalance = UNIVERSAL_GOLD_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //3000
                case VISA_INFINITE -> resultBalance = VISA_INFINITE_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //20000
                case PLATINUM -> resultBalance = PLATINUM_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //4000
                case JUNIOR -> resultBalance = JUNIOR_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //0
                case GOLD -> resultBalance = GOLD_CARD_MAX_CREDIT_BALANCE / SIZE_FOR_GIVE_1000_CREDIT_BALANCE; //2000
            }
        } else {
            //If the user has more than 1 card
            resultBalance = 0;
        }
        return resultBalance;
    }
}

