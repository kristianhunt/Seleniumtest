import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class kandideeri {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://evalimised13.appspot.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testKandideeri() throws Exception {
    driver.get(baseUrl + "/index_login.html");
    driver.findElement(By.linkText("Kandideeri")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("registerName"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("registerPhone"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("registerName")).clear();
    driver.findElement(By.id("registerName")).sendKeys("Test");
    try {
      assertEquals("Test", driver.findElement(By.id("registerName")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("registerID")).clear();
    driver.findElement(By.id("registerID")).sendKeys("1234");
    try {
      assertEquals("1234", driver.findElement(By.id("registerID")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("registerBirthplace")).clear();
    driver.findElement(By.id("registerBirthplace")).sendKeys("Tartu");
    try {
      assertEquals("Tartu", driver.findElement(By.id("registerBirthplace")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("registerPhone")).clear();
    driver.findElement(By.id("registerPhone")).sendKeys("1234");
    try {
      assertEquals("1234", driver.findElement(By.id("registerPhone")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.id("registerParty"))).selectByVisibleText("Erilised");
    try {
      assertEquals("Erilised", driver.findElement(By.id("registerParty")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.id("registerRegion"))).selectByVisibleText("Saaremaa");
    try {
      assertEquals("Saaremaa", driver.findElement(By.id("registerRegion")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("vButton")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Teie kandidatuur on salvestatud![\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
