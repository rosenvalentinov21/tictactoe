import com.google.inject.Guice;
import com.google.inject.Injector;
import config.BindingModule;
import controller.GameController;

public class Application {

  public static void main(String[] args) {

    final Injector injector = Guice.createInjector(new BindingModule());
    final GameController gameController = injector.getInstance(GameController.class);

    gameController.InitializeGame();
  }
}
