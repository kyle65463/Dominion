package dominion.models.cards;

import dominion.models.expansions.Expansion;
import dominion.models.expansions.Unknown;
import javafx.util.Pair;

import java.util.*;

public class CardFactory {
    // Variables
    private static Class<? extends Expansion> defaultExpansion;
    private static final List<Class<? extends Expansion>> expansions = new ArrayList<>();
    private static final Map<Class<? extends Expansion>, String> expansionNameMap = new HashMap<>();
    private static final Map<Class<? extends Expansion>, List<Card>> expansionCardMap = new HashMap<>();
    private static final Map<Integer, Card> cardIdMap = new HashMap<>();

    // Functions
    public static void initializeExpansions(List<Pair<String, Class<? extends Expansion>>> expansionPairs) {
        for (Pair<String, Class<? extends Expansion>> expansionPair : expansionPairs) {
            String name = expansionPair.getKey();
            Class<? extends Expansion> expansion = expansionPair.getValue();
            expansionCardMap.put(expansion, new ArrayList<>());
            expansionNameMap.put(expansion, name);
            expansions.add(expansion);
        }
    }

    public static void initializeDefaultExpansion(Class<? extends Expansion> expansion) {
        defaultExpansion = expansion;
    }

    public static void initializeAllCards(List<Card> cards) {
        int id = 0;
        for (Card card : cards) {
            Class<? extends Expansion> expansion = getExpansionOfCard(card);
            if (expansionCardMap.containsKey(expansion)) {
                List<Card> expansionCards = expansionCardMap.get(expansion);
                expansionCards.add(card);
            }
            cardIdMap.put(id, card);
            id++;
        }
    }

    private static Class<? extends Expansion> getExpansionOfCard(Card card) {
        for (Class<? extends Expansion> expansion : expansions) {
            if (expansion.isInstance(card)) {
                return expansion;
            }
        }
        return Unknown.class;
    }

    public static List<Card> getCardsOfExpansion(Class<? extends Expansion> expansion) {
        if (expansionCardMap.containsKey(expansion)) {
            List<Card> expansionCards = expansionCardMap.get(expansion);
            expansionCards.sort((a, b) -> b.getNumCost() - a.getNumCost());
            return new ArrayList<>(expansionCards);
        }
        return new ArrayList<>();
    }

    public static List<Card> getCardsByIds(List<Integer> ids) {
        List<Card> cards = new ArrayList<>();
        for (Integer id : ids) {
            cards.add(cardIdMap.get(id));
        }
        return cards;
    }

    public static int getCardId(Card card) {
        for (Map.Entry<Integer, Card> pair : cardIdMap.entrySet()) {
            if (pair.getValue().getName().equals(card.getName())) {
                return pair.getKey();
            }
        }
        return 0;
    }

    public static List<Integer> getCardIds(List<Card> cards) {
        List<Integer> ids = new ArrayList<>();
        for (Card card : cards) {
            ids.add(getCardId(card));
        }
        return ids;
    }

    public static Class<? extends Expansion> getDefaultExpansion() {
        return defaultExpansion;
    }

    public static List<Class<? extends Expansion>> getExpansions() {
        return new ArrayList<>(expansions);
    }

    public static String getNameOfExpansion(Class<? extends Expansion> expansion) {
        if (expansionNameMap.containsKey(expansion)) {
            return expansionNameMap.get(expansion);
        }
        return "";
    }
}