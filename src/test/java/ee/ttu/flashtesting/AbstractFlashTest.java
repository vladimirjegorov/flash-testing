package ee.ttu.flashtesting;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Properties;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.Keys.*;

public abstract class AbstractFlashTest {

  private static final String TEST_PROPERTIES_FILE = "/test.properties";

  /**
   * Available since IE8, FF 3.5, Chrome 4
   */
  // Note! There appear to be some problems with jQuery and Firefox driver
  // http://stackoverflow.com/questions/10092190/selenium-webdriver-executescript-not-working
  private static final String GAME_CONTENT_OBJ_SELECTOR = "document.querySelector('embed#game_content_obj')";

  private RemoteWebDriver driver;

  private Properties properties;

  private Logger logger;

  @Before
  public void before() throws Exception {
    loadProperties();
    prepareFirefox();
    logIn();
    openGame();
    injectFlashTester();
  }

  private void loadProperties() throws Exception {
    // Load test configuration
//    FileInputStream propertiesFile = new FileInputStream(TEST_PROPERTIES_FILE);
    if (this.properties == null) {
      this.properties = new Properties();
    }
    this.properties.load(getClass().getResourceAsStream(TEST_PROPERTIES_FILE));
//    propertiesFile.close();
  }

  private void prepareFirefox() {
    // Set Firefox location for Firefox driver
    String firefoxBinaryPath = this.properties.getProperty("webdriver.firefox.bin");
    System.setProperty("webdriver.firefox.bin", firefoxBinaryPath);

    // Create Firefox driver
    getLogger().info("Initializing browser driver");
    this.driver = new FirefoxDriver();
    this.driver.manage().window().setSize(new Dimension(1024, 768)); // optional

    // Open new tab for miscellaneous actions (for example logging in)
    openNewTab();

    // Switch back to the first tab
    openFirstTab();
  }

  protected void logIn() throws Exception {
    this.openSecondTab();
    this.driver.get(this.properties.getProperty("site.url"));
    this.driver.findElement(By.id("username")).sendKeys("vladimirtest");
    this.driver.findElement(By.id("password")).sendKeys("vladimirtest");
    this.driver.findElement(By.id("login_button")).click();
    this.openFirstTab();
  }

  private void openGame() {
    String gameUrl = "";
    gameUrl += this.properties.getProperty("site.url");
    gameUrl += "/casino/paf/demogame.html?game=";
    gameUrl += getGameId();
    this.driver.get(gameUrl);
  }

  private void injectFlashTester() throws Exception {
    String srcSelector = GAME_CONTENT_OBJ_SELECTOR + ".attributes['src'].value";
    final String src = (String) this.driver.executeScript("return escape(" + srcSelector + ")");

    final String srcWithTester = "/gameclient_flash_testing/client/flash_tester.swf?path=" + src;

    this.driver.executeScript(srcSelector + "='" + srcWithTester + "'");

    // Wait for game to load
    this.sleepFor(15);
  }

  @After
  public void after() {
    this.getDriver().quit();
  }

  protected WebDriver getDriver() {
    return this.driver;
  }

  protected Logger getLogger() {
    if (this.logger == null) {
      this.logger = Logger.getLogger(this.getClass());
    }
    return this.logger;
  }

  protected void sleepFor(int seconds) throws Exception {
    getLogger().info("Sleeping for: " + seconds + " seconds");
    sleep(seconds * 1000);
  }

  protected void assertTextEquals(double x, double y, String expected) {
    String fullScript = "";
    fullScript += "return ";
    fullScript += GAME_CONTENT_OBJ_SELECTOR + ".getTextUnderPoint";
    fullScript += "(arguments[0], arguments[1])";
    String actualTextUnderPoint = (String) this.driver.executeScript(fullScript, x, y);

    assertEquals(expected, actualTextUnderPoint);
  }

  protected void sendClick(double x, double y) {
    sendMouseEvent("click", x, y);
  }

  protected void sendMouseEvent(String flashEventName, double x, double y) {
    String fullScript = "";
    fullScript += GAME_CONTENT_OBJ_SELECTOR + ".sendMouseEvent";
    fullScript += "(arguments[0], arguments[1], arguments[2])";

    this.driver.executeScript(fullScript, flashEventName, x, y);

    try {
      Thread.sleep(getIntervalBetweenActions()); // Not to bee too quick
    } catch (InterruptedException e) {
      // Do nothing
    }
  }

  private void openNewTab() {
    sendKeysToBody(CONTROL + "t");
  }

  private void openFirstTab() {
    sendKeysToBody(CONTROL + "" + NUMPAD1);
  }

  private void openSecondTab() {
    sendKeysToBody(CONTROL + "" + NUMPAD2);
  }

  private void sendKeysToBody(CharSequence... keysToSend) {
    this.driver.findElement(By.tagName("body")).sendKeys(keysToSend);
  }

  protected int getIntervalBetweenActions() {
    return 10;
  }

  protected abstract String getGameId();
}
