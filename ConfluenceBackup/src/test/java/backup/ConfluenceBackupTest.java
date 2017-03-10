package test.java.backup;


import java.util.regex.Pattern;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by takehiro_otani on 2017/03/09.
 */
public class ConfluenceBackupTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private String baseUrl;
  private Properties prop;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	// chrome
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability("chrome.binary", "/opt/google/chrome/chrome");
	System.setProperty("webdriver.chrome.driver", "/usr/local/selenium/webDriver/chromedriver");
	driver = new ChromeDriver(capabilities);
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    // load properties
    prop = new Properties();
    try {
	    prop.load(new FileInputStream("confluence.properties"));
	  } catch (IOException e) {
	    e.printStackTrace();
	    return;
	  }
    baseUrl = prop.getProperty("baseUrl");
  }

  @Test
  public void testConfluenceSupportBackup() throws Exception {
    System.out.println("SupportSpaceBackup");
    loginAction();
    
    driver.get(baseUrl + prop.getProperty("supportSpace"));
    driver.findElement(By.id("format-export-format-html")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("contentOptionAll"))));
    driver.findElement(By.id("contentOptionVisible")).click();
    driver.findElement(By.id("contentOptionAll")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("percentComplete"))));

    String lastTime;
    int percent = 0;
    for(;percent < 100;){
    	try{
	    	WebElement element = driver.findElement(By.id("percentComplete"));
	    	if(element != null){
		    	percent = Integer.parseInt(element.getText());
		    	lastTime = driver.findElement(By.id("taskElapsedTime")).getText();
		    	System.out.println("percent:"+percent+" lastTime:"+lastTime);
	    	}
    	}catch(Exception e){
    		System.out.println("readError");
    		percent = 0;
    	}
    	Thread.sleep(5000);
    }

    FileLoader("here",prop.getProperty("downloadDir"));
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile, new File("./supportbackup.png"));
  }

  @Test
  public void testConfluenceWorkBackup() throws Exception {
    System.out.println("WorkSpaceBackup");
    loginAction();
    
    driver.get(baseUrl + prop.getProperty("workSpace"));
    driver.findElement(By.id("format-export-format-html")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("contentOptionAll"))));
    driver.findElement(By.id("contentOptionVisible")).click();
    driver.findElement(By.id("contentOptionAll")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("percentComplete"))));

    String lastTime;
    int percent = 0;
    for(;percent < 100;){
    	try{
	    	WebElement element = driver.findElement(By.id("percentComplete"));
	    	if(element != null){
		    	percent = Integer.parseInt(element.getText());
		    	lastTime = driver.findElement(By.id("taskElapsedTime")).getText();
		    	System.out.println("percent:"+percent+" lastTime:"+lastTime);
	    	}
    	}catch(Exception e){
    		System.out.println("readError");
    		percent = 0;
    	}
    	Thread.sleep(5000);
    }

    FileLoader("here",prop.getProperty("downloadDir"));
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile, new File("./workbackup.png"));
  }
  
  @Test
  public void testConfluenceProductBackup() throws Exception {
    System.out.println("ProductSpaceBackup");
    loginAction();
    
    driver.get(baseUrl + prop.getProperty("productSpace"));
    driver.findElement(By.id("format-export-format-html")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("contentOptionAll"))));
    driver.findElement(By.id("contentOptionVisible")).click();
    driver.findElement(By.id("contentOptionAll")).click();
    driver.findElement(By.name("confirm")).click();
    wait.until(visibilityOf(driver.findElement(By.id("percentComplete"))));

    String lastTime;
    int percent = 0;
    for(;percent < 100;){
    	try{
	    	WebElement element = driver.findElement(By.id("percentComplete"));
	    	if(element != null){
		    	percent = Integer.parseInt(element.getText());
		    	lastTime = driver.findElement(By.id("taskElapsedTime")).getText();
		    	System.out.println("percent:"+percent+" lastTime:"+lastTime);
	    	}
    	}catch(Exception e){
    		System.out.println("readError");
    		percent = 0;
    	}
    	Thread.sleep(5000);
    }

    FileLoader("here",prop.getProperty("downloadDir"));
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile, new File("./productbackup.png"));
  }

  /**
   * loginAction 
   */
  private void loginAction(){
	    driver.get(baseUrl + "/login");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(prop.getProperty("user"));
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(prop.getProperty("pass"));
	    driver.findElement(By.id("login")).click();
	    wait.until(titleContains("Extic"));
  }
  
  /**
   * FileLoader
   * @param String linkText
   * Click the link to execute the download
   */
  public void FileLoader(String linkText,String downloadDir) throws InterruptedException {
      WebElement download = driver.findElement(By.linkText(linkText));
      download.click();
      Thread.sleep(10000);

      // downloading
      boolean downloading = true;
      File dir = new File(downloadDir);
      while(downloading){
        downloading = false;
        File[] list = dir.listFiles();
        for(int i = 0; i < list.length; i++){
          if(list[i].getName().lastIndexOf(".crdownload") > 0){
            downloading = true;
            System.out.println("downloading : " + list[i].getName());
            break;
          }
        }
        Thread.sleep(10000);
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
