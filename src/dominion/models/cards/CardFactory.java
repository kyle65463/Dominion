package dominion.models.cards;

import dominion.models.cards.actions.*;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.treasures.Gold;
import dominion.models.cards.treasures.Silver;
import dominion.models.cards.victories.Duchy;
import dominion.models.cards.victories.Duke;
import dominion.models.cards.victories.Estate;
import dominion.models.cards.victories.Province;

import java.util.*;

public class CardFactory {
    // Variables
    private static Map<Integer, Card> cardsMap = new HashMap<>();
    private static List<Card> basicCards = new ArrayList<>();
    private static List<Card> dominionCards = new ArrayList<>();
    private static List<Card> intrigueCards = new ArrayList<>();
    private static List<Card> seaSideCards = new ArrayList<>();
    private static int id = 0;

    // Functions
    public static void initialize() {
        basicCards = new ArrayList<>(Arrays.asList(
                new Province(),
                new Gold(),
                new Duchy(),
                new Silver(),
                new Estate(),
                new Copper(),
                new Curse()
        ));
        setCardIds(basicCards);

        dominionCards = new ArrayList<>(Arrays.asList(
                new Cellar(),
                new Chapel(),
                new CouncilRoom(),
                new Festival(),
                new Laboratory(),
                new Market(),
                new Militia(),
                new Moat(),
                new MoneyLender(),
                new Smithy(),
                new ThroneRoom(),
                new Village(),
                new Witch(),
                new Artisan(),
                new Vassal()
        ));
        setCardIds(dominionCards);

        intrigueCards = new ArrayList<>(Arrays.asList(
                new CountryYard(),
                new Duke(),
                new IronWorks(),
                new Replace()
        ));
        setCardIds(intrigueCards);

        seaSideCards = new ArrayList<>(Arrays.asList(
                new WareHouse(),
                new TreasureMap(),
                new CutPurse(),
                new Bazaar(),
                new SeaHag(),
                new Salvager()
        ));
        setCardIds(seaSideCards);
    }

    public static List<Card> getBasicCardList() {
        return new ArrayList<>(basicCards);
    }

    public static List<Card> getDominionCardList() {
        return new ArrayList<>(dominionCards);
    }

    public static List<Card> getSeaSideCardList() {
        return new ArrayList<>(seaSideCards);
    }

    public static List<Card> getIntrigueCardList() {
        return new ArrayList<>(intrigueCards);
    }

    public static List<Card> getCardsById(List<Integer> ids) {
        List<Card> cards = new ArrayList<>();
        for (Integer id : ids) {
            cards.add(cardsMap.get(id));
        }
        return cards;
    }

    public static int getCardId(Card card) {
        for (Map.Entry<Integer, Card> pair : cardsMap.entrySet()) {
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

    private static void setCardIds(List<Card> cards) {
        for (Card card : cards) {
            cardsMap.put(id, card);
            id++;
        }
    }
}