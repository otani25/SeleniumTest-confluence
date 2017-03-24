/**
*
* クラス名
*   CommonManager.java
*
* 概要
*   ブラウザ共通クラスの基底クラス
*/

package test.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class CommonManager {

    private static Logger LOG = Logger.getLogger( CommonManager.class.getName() );

    protected String browserName;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties testInfo;
    protected String baseURL;

    /**
     * コンストラクタ
     * 
     * @param browserName ログ等で使用するブラウザ名
     * @param driver 実行対象ブラウザのWebDriverインスタンス
     * @param testInfoPath テスト用の設定ファイルパス
     */
    public CommonManager( String browserName, WebDriver driver, String testInfoPath ) {
        this.browserName = browserName;
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 30 );

        // テスト情報読み込み
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

    /**
     * スリープ付汎用待機処理
     * 
     * @param conditions 待機条件
     * @param sleepTime 待機時間
     */
    public void waitUntil( ExpectedCondition< ? > conditions, int sleepTime ) {
        try {
            Thread.sleep( sleepTime );
        }
        catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        wait.until( conditions );
    }

    /**
     * スリープ付汎用待機処理(1000ミリ固定待機)
     * 
     * @param conditions 待機条件
     */
    public void waitUntil( ExpectedCondition< ? > conditions ) {
        waitUntil( conditions, 1000 );
    }

    /**
     * ログ出力用ヘッダ情報
     * 
     * @return ヘッダ文字列
     */
    protected String getLogHeader() {
        return "【" + this.browserName + "】 ";
    }
}
