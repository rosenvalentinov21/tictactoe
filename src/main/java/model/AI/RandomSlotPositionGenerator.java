package model.AI;

import com.google.inject.Inject;
import java.util.Random;

public class RandomSlotPositionGenerator {

  private final Random random;

  @Inject
  public RandomSlotPositionGenerator(final Random random) {
    this.random = random;
  }

  public int generateRandomSlotPosition() {
    final int SLOT_LOWER_BOUND = 1;
    final int SLOT_UPPER_BOUND = 9;
    return random.nextInt(SLOT_LOWER_BOUND, SLOT_UPPER_BOUND);
  }
}
