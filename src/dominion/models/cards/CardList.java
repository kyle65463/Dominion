package dominion.models.cards;

import dominion.models.cards.actions.*;
import dominion.models.cards.victories.Province;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardList {
    // Variables
    private static List<Card> cardList = new ArrayList<>();

    // Functions
    public static List<Card> getDominionCardList() {
        cardList = new ArrayList<>(Arrays.asList(
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
                new Witch()
        ));
        return new ArrayList<>(cardList);
    }
}