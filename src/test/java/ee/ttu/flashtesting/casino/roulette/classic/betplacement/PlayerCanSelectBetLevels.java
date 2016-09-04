package ee.ttu.flashtesting.casino.roulette.classic.betplacement;

import ee.ttu.flashtesting.casino.roulette.classic.AbstractClassicRouletteTest;
import org.junit.Test;

public class PlayerCanSelectBetLevels extends AbstractClassicRouletteTest {

  /**
   * Player opens the game, selects 2nd table and places different bets
   */
  @Test
  public void selectBetLevels() throws Exception {
    // Select first table
    selectTable2();

    // Select 1st chip
    selectChip1();
    // Place a bet to the position "0"
    placeStakeTo_0();
    // Assert that the bet is 0.50
    assertBet("0.50 €");
    // Undo stake placement
    undo();

    // Select 2nd chip
    selectChip2();
    // Place a bet to position "1"
    placeStakeTo_1();
    // Assert that the bet is 1.00
    assertBet("1.00 €");
    // Undo stake placement
    undo();

    // Select 3rd chip
    selectChip3();
    // Place a bet to position "Black"
    placeStakeTo_black();
    // Assert that the bet is 2.00
    assertBet("2.00 €");
    // Undo stake placement
    undo();

    // Select 4th chip
    selectChip4();
    // Place a bet to position "Red"
    placeStakeTo_red();
    // Assert that the bet is 5.00
    assertBet("5.00 €");
    // Undo stake placement
    undo();
  }

  @Override
  protected int getIntervalBetweenActions() {
    return 1000; // ms
  }
}
