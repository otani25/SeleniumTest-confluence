/**
*
* クラス名
*   BrowserTestBase.java
*
* 概要
*   ブラウザ用テストクラスの基底クラス
*/

package browser;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

public abstract class BrowserTestBase {

    protected static WebDriver driver;
    protected static Properties browserInfo;
    protected static DesiredCapabilities capabilities;

    /**
     * コンストラクタ
     */
    @BeforeClass
    public static void beforeClass() {
        // ブラウザ設定情報読み込み
        browserInfo = new Properties();
        try {
            browserInfo.load( new FileInputStream( "browserInfo.properties" ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
            return;
        }
        // ログ設定情報読み込み
        try {
            InputStream inStream = new FileInputStream( "logging.properties" );
            LogManager.getLogManager().readConfiguration( inStream );
        }
        catch ( IOException | SecurityException ex ) {
            ex.printStackTrace();
            return;
        }
    }

    /**
     * テスト実行前の準備
     */
    @Before
    public void preTest() {
        if ( driver != null ) {
            driver.quit();
            driver = null;
        }
        initDriver();
    }

    /**
     * テスト完了後の終了処理
     */
    @After
    public void postTest() {
        if ( driver != null ) {
            driver.quit();
        }
    }

    /**
     * WebDriver取得
     * 
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * ブラウザ情報取得
     * 
     * @return properties
     */
    public static Properties getBrowserInfo() {
        return browserInfo;
    }

    /**
     * ブラウザ初期化処理
     */
    abstract protected void initDriver();

}
