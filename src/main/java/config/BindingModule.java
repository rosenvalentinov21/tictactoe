package config;

import com.google.inject.AbstractModule;
import communication.MessageProvider;
import communication.Messaging;
import communication.Readable;
import communication.Reader;
import controller.GameController;
import java.util.Random;
import java.util.Scanner;
import model.AI.Opponent;
import model.AI.RandomSlotPositionGenerator;
import model.Player;
import model.grid.GridManager;
import model.grid.GridVisualizer;
import model.grid.Judge;
import model.leaderboard.HallOfFame;
import model.view.GameDialog;
import payment.CoinReceiverDialog;
import persistence.MySqlConnection;

public class BindingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Scanner.class).toInstance(new Scanner(System.in));
    bind(Readable.class).to(Reader.class).asEagerSingleton();
    bind(CoinReceiverDialog.class).asEagerSingleton();
    bind(Random.class).asEagerSingleton();
    bind(RandomSlotPositionGenerator.class).asEagerSingleton();
    bind(Opponent.class).asEagerSingleton();
    bind(Player.class).asEagerSingleton();
    bind(GridManager.class).asEagerSingleton();
    bind(GameDialog.class).asEagerSingleton();
    bind(Messaging.class).to(MessageProvider.class);
    bind(GridVisualizer.class).asEagerSingleton();
    bind(Judge.class).asEagerSingleton();
    bind(MySqlConnection.class).asEagerSingleton();
    bind(HallOfFame.class).asEagerSingleton();
    bind(GameController.class).asEagerSingleton();
  }
}
