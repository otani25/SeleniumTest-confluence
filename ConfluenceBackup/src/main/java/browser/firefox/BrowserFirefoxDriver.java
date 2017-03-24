/**
*
* クラス名
*   BrowserFirefoxDriver.java
*
* 概要
*   FireFox用テストクラスの基底クラス
*/

package browser.firefox;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import browser.BrowserTestBase;

public abstract class BrowserFirefoxDriver extends BrowserTestBase {

    protected static FirefoxProfile profile;

    /**
     * ブラウザ初期化
     */
    @Override
    protected void initDriver() {
        profile = new FirefoxProfile();
        setupProfile();

        String driverPath = getBrowserInfo().getProperty( "firefoxDriver" );
        // SeleniumGridでブラウザ遠隔起動
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
        // ローカル環境のブラウザ起動
        else {
            System.setProperty( "webdriver.gecko.driver", driverPath );
            // 作成したプロファイルでFirefox(のドライバー)を起動する
            driver = new FirefoxDriver( profile );
        }
    }

    /**
     * ブラウザ初期化オプション
     */
    abstract protected void setupProfile();
}
