package dominion.utils;

import java.util.stream.Stream;

public class CardTypes {
    // Variables
    public final static String treasure = "錢幣卡";
    public final static String victory = "分數卡";
    public final static String action = "行動卡";
    public final static String curse = "詛咒卡";
    public final static String attack = "攻擊卡";
    public final static String reaction = "應對卡";

    // Functions
    public static String compose(String type1, String type2) {
        return type1 + " - " + type2;
    }
}
