package ee.ttu.flashtesting.casino.roulette.classic.choosetable;

import org.junit.Test;

import ee.ttu.flashtesting.casino.roulette.classic.AbstractClassicRouletteTest;

public class PlayerCanChooseTable extends AbstractClassicRouletteTest {

  /**
   * Player opens the game and selects different table
   */
  @Test
  public void playerCanChooseTable() throws Exception {

    // Select Table 1
    selectTable1();

    // Verify that balance is 1000 Euro
    assertBalance("1000.00 €");

    // Verify table limits
    assertMin("0.20");
    assertMax("2.00");

    // Go to table selection screen
    chooseTalbe();

    // Select Table 5
    selectTable5();
    // Verify that balance is 1000 Euro
    assertBalance("1000.00 €");
    // Verify table limits
    assertMin("10");
    assertMax("100");
  }
}
