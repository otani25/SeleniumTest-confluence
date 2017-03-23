package test.browser.ie;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import test.browser.BrowserTestBase;

public abstract class BrowserIEDriver extends BrowserTestBase {

    @Override
    protected void initDriver() {
        capabilities = DesiredCapabilities.internetExplorer();
        setupProfile();

        // 作成したプロファイルでIE(のドライバー)を起動する
        String driverPath = getBrowserInfo().getProperty( "ieDriver" );
        if ( driverPath.contains( "http" ) ) {
            capabilities.setPlatform( Platform.WINDOWS );
            capabilities.setBrowserName( "internet explorer" );
            try {
                driver = new RemoteWebDriver( new URL( driverPath ), capabilities );
            }
            catch ( MalformedURLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            File file = new File( driverPath );
            System.setProperty( "webdriver.ie.driver", file.getAbsolutePath() );
            driver = new InternetExplorerDriver( capabilities );
        }
    }

    abstract protected void setupProfile();

}
