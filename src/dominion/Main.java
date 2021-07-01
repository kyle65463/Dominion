package dominion;

import dominion.models.cards.CardFactory;
import dominion.models.cards.actions.*;
import dominion.models.cards.curses.Curse;
import dominion.models.cards.treasures.Copper;
import dominion.models.cards.treasures.Gold;
import dominion.models.cards.treasures.Silver;
import dominion.models.cards.victories.Duchy;
import dominion.models.cards.victories.Duke;
import dominion.models.cards.victories.Estate;
import dominion.models.cards.victories.Province;
import dominion.models.expansions.*;
import dominion.params.MainSceneParams;
import dominion.utils.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialization procedure
        initializeCardFactory();

        // Create stage
        Stage stage = new Stage();
        stage.setTitle("Dominion");
        stage.setResizable(false);
        MainSceneParams params = new MainSceneParams("kyle15989", "localhost", "9999");
        Navigator.to(stage, "resources/scenes/main.fxml", params);
    }

    private static void initializeCardFactory() {
        CardFactory.initializeExpansions(Arrays.asList(
                new Pair<>("基本", Basic.class),
                new Pair<>("皇輿爭霸", Dominion.class),
//                new Pair<>("暗潮洶湧", Intrigue.class),
                new Pair<>("海國圖誌", SeaSide.class)
        ));

        CardFactory.initializeDefaultExpansion(Dominion.class);

        CardFactory.initializeAllCards(Arrays.asList(
                // Basic
                new Province(),
                new Gold(),
                new Duchy(),
                new Silver(),
                new Estate(),
                new Copper(),
                new Curse(),

                // Dominion
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
//                new ThroneRoom(),
                new Village(),
                new Witch(),
                new Artisan(),
                new Vassal(),
                new Merchant(),
                new Poacher(),
                new Mine(),

                // Intrigue
                new CountryYard(),
                new Duke(),
                new IronWorks(),
                new Replace(),

                // Seaside
                new WareHouse(),
                new TreasureMap(),
                new CutPurse(),
                new Bazaar(),
                new SeaHag(),
                new Salvager()
        ));
    }
}
