package test.browser.firefox;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import test.browser.BrowserTestBase;

public abstract class BrowserFirefoxDriver extends BrowserTestBase {

    protected static FirefoxProfile profile;

    @Override
    protected void initDriver() {
        profile = new FirefoxProfile();
        setupProfile();

        String driverPath = getBrowserInfo().getProperty( "firefoxDriver" );
        if ( driverPath.contains( "http" ) ) {
            capabilities = DesiredCapabilities.firefox();
            capabilities.setPlatform( Platform.WINDOWS );
            capabilities.setBrowserName( "firefox" );
            try {
                driver = new RemoteWebDriver( new URL( driverPath ), capabilities );
            }
            catch ( MalformedURLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            System.setProperty( "webdriver.gecko.driver", driverPath );
            // 作成したプロファイルでFirefox(のドライバー)を起動する
            driver = new FirefoxDriver( profile );
        }
    }

    abstract protected void setupProfile();
}
