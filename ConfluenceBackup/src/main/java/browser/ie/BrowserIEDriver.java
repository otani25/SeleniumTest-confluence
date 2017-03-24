/**
*
* クラス名
*   BrowserIEDriver.java
*
* 概要
*   IE用テストクラスの基底クラス
*/
package browser.ie;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import browser.BrowserTestBase;

public abstract class BrowserIEDriver extends BrowserTestBase {

    @Override
    protected void initDriver() {
        capabilities = DesiredCapabilities.internetExplorer();
        setupProfile();

        // 作成したプロファイルでIE(のドライバー)を起動する
        String driverPath = getBrowserInfo().getProperty( "ieDriver" );
        // SeleniumGridでブラウザ遠隔起動
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
        // ローカル環境のブラウザ起動
        else {
            File file = new File( driverPath );
            System.setProperty( "webdriver.ie.driver", file.getAbsolutePath() );
            driver = new InternetExplorerDriver( capabilities );
        }
    }

    /**
     * ブラウザ初期化オプション
     */
    abstract protected void setupProfile();

}
