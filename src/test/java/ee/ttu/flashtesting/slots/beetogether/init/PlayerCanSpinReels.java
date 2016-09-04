package ee.ttu.flashtesting.slots.beetogether.init;

import org.junit.Test;

import ee.ttu.flashtesting.slots.beetogether.AbstractBeeTogetherCase;

public class PlayerCanSpinReels extends AbstractBeeTogetherCase {

  @Test
  public void spinReels() throws Exception {
    sleepFor(10);

    sendClick(392, 370);
    sleepFor(3);

    sendClick(560, 553);
    sleepFor(5);

    sendClick(381, 505);
    sleepFor(5);
  }
}
