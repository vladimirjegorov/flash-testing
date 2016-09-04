package ee.ttu.flashtesting.casino.roulette.classic;

import ee.ttu.flashtesting.AbstractFlashTest;

public abstract class AbstractClassicRouletteTest extends AbstractFlashTest {

  @Override
  public String getGameId() {
    return "ROULETTE_CLASSIC";
  }

  // --- Misc button clicks
  protected void chooseTalbe() {
    getLogger().info("Click the 'Choose table' button");
    sendClick(96.0, 9.0);
  }

  protected void selectTable1() {
    getLogger().info("Select table 1");
    sendClick(156.0, 255.0);
  }

  protected void selectTable2() {
    getLogger().info("Select table 2");
    sendClick(273.0, 264.0);
  }

  protected void selectTable5() {
    getLogger().info("Select table 5");
    sendClick(652.0, 258.0);
  }

  protected void selectChip1() {
    getLogger().info("Select chip 1");
    sendClick(630.0, 520.0);
  }

  protected void selectChip2() {
    getLogger().info("Select chip 2");
    sendClick(667.0, 525.0);
  }

  protected void selectChip3() {
    getLogger().info("Select chip 3");
    sendClick(710.0, 520.0);
  }

  protected void selectChip4() {
    getLogger().info("Select chip 4");
    sendClick(748.0, 522.0);
  }

  // --- Action buttons
  protected void spin() {
    getLogger().info("Spin the roulette");
    sendClick(414.0, 516.0);
  }

  protected void undo() {
    getLogger().info("Undo stake placement");
    sendClick(364.0, 495.0);
  }

  // --- Stake placement
  protected void placeStakeTo_0() {
    getLogger().info("Place stake to 0");
    placeStakeTo(293.0, 193.0);
  }

  protected void placeStakeTo_1() {
    getLogger().info("Place stake to 1");
    placeStakeTo(304.0, 233.0);
  }

  protected void placeStakeTo_red() {
    getLogger().info("Place stake to red");
    placeStakeTo(428.0, 357.0);
  }

  protected void placeStakeTo_black() {
    getLogger().info("Place stake to black");
    placeStakeTo(503.0, 382.0);
  }

  private void placeStakeTo(double x, double y) {
    sendMouseEvent("mouseDown", x, y);
  }

  // --- Assertions
  protected void assertBalance(String expected) {
    getLogger().info("Assert that balance is: " + expected);
    assertTextEquals(485.0, 106.0, expected);
  }

  protected void assertBet(String expected) {
    getLogger().info("Assert that bet is: " + expected);
    assertTextEquals(485.0, 46.0, expected);
  }

  protected void assertWin(String expected) {
    getLogger().info("Assert that win is: " + expected);
    assertTextEquals(480.0, 75.0, expected);
  }

  protected void assertMin(String expected) {
    getLogger().info("Assert that min bet is: " + expected);
    assertTextEquals(185.0, 268.0, expected);
  }

  protected void assertMax(String expected) {
    getLogger().info("Assert that max bet is: " + expected);
    assertTextEquals(193.0, 285.0, expected);
  }
}
