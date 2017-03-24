/**
*
* クラス名
*   BrowserChromeDriver.java
*
* 概要
*   Chrome用テストクラスの基底クラス
*/

package browser.chrome;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import browser.BrowserTestBase;

public abstract class BrowserChromeDriver extends BrowserTestBase {

    /**
     * ブラウザ初期化
     */
    @Override
    protected void initDriver() {
        capabilities = DesiredCapabilities.chrome();
        setupProfile();

        String driverPath = getBrowserInfo().getProperty( "chromeDriver" );
        capabilities = DesiredCapabilities.chrome();
        // SeleniumGridでブラウザ遠隔起動
        if ( driverPath.contains( "http" ) ) {
            capabilities.setPlatform( Platform.WINDOWS );
            capabilities.setBrowserName( "chrome" );
            try {
                driver = new RemoteWebDriver( new URL( driverPath ), capabilities );
            }
            catch ( MalformedURLException e ) {
                e.printStackTrace();
            }
        }
        // ローカル環境のブラウザ起動
        else {
            capabilities.setCapability( "chrome.binary", getBrowserInfo().getProperty( "chromeBinary" ) );
            System.setProperty( "webdriver.chrome.driver", driverPath );
            driver = new ChromeDriver( capabilities );
        }
    }

    /**
     * ブラウザ初期化オプション
     */
    abstract protected void setupProfile();

}
