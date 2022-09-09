package model.AI;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class RandomSlotPositionGeneratorTest {

  private final RandomSlotPositionGenerator randomSlotPositionGenerator = new RandomSlotPositionGenerator(
      new Random());

  @Test
  void generateRandomSlotPosition_ShouldMatchOneOfThePositions() {
    final int SLOT_LOWER_BOUND = 1;
    final int SLOT_UPPER_BOUND = 9;
    final int position = randomSlotPositionGenerator.generateRandomSlotPosition();

    boolean match = false;
    for (int i = SLOT_LOWER_BOUND; i <= SLOT_UPPER_BOUND; i++) {
      if (i == position) {
        match = true;
        break;
      }
    }

    assertTrue(match);
  }
}