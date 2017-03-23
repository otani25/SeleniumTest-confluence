package test.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import util.CaputureUtils;

public abstract class CommonManager {

    private static Logger LOG = Logger.getLogger( CommonManager.class.getName() );

    protected String browserName;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties testInfo;
    protected String baseURL;

    public CommonManager( String browserName, WebDriver driver, String testInfoPath ) {
        this.browserName = browserName;
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 30 );

        // load browser properties
        this.testInfo = new Properties();
        try {
            this.testInfo.load( new FileInputStream( testInfoPath ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
            return;
        }
        this.baseURL = testInfo.getProperty( "baseURL" );
    }

    public void beforeTestClass() {
    }

    public void afterTestClass() {
        if ( driver != null ) {
            driver.quit();
        }
    }

    /*
     * waitUntil
     * 
     * @param ExpectedCondition arg0
     * 
     * @param sleepTime
     */
    public void waitUntil( ExpectedCondition< ? > arg0, int sleepTime ) {
        try {
            Thread.sleep( sleepTime );
        }
        catch ( InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wait.until( arg0 );
    }

    public void waitUntil( ExpectedCondition< ? > arg0 ) {
        waitUntil( arg0, 1000 );
    }

    /**
     * getLogHeader
     * 
     * @return
     */
    protected String getLogHeader() {
        return "【" + this.browserName + "】 ";
    }
}
