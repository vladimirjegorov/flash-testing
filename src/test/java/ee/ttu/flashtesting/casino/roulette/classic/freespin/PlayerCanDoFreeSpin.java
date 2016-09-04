package ee.ttu.flashtesting.casino.roulette.classic.freespin;

import org.junit.Test;

import ee.ttu.flashtesting.casino.roulette.classic.AbstractClassicRouletteTest;

public class PlayerCanDoFreeSpin extends AbstractClassicRouletteTest {

  /**
   * Player will do free spin
   */
  @Test
  public void freeSpin() throws Exception {

    // Select Table 1
    selectTable1();
    // Verify balance
    assertBalance("1000.00 €");

    // (Free) spin
    spin();

    // Wait for 15 seconds until roulette spinning ends
    sleepFor(15);

    // Verify bet is 0
    assertBet("0.00 €");
    // Verify win is 0
    assertWin("0.00 €");
    // Verify balance is 1000
    assertBalance("1000.00 €");
  }
}
