/**
*
* クラス名
*   CommonBackup.java
*
* 概要
*   confluenceのバックアップを実行するブラウザ共通クラス
*/

package test.common.confluence;

import test.common.CommonManager;
import org.openqa.selenium.*;
import util.CaputureUtils;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import static org.junit.Assert.*;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonBackup extends CommonManager {
    private static Logger LOG = Logger.getLogger( CommonBackup.class.getName() );

    public CommonBackup( String browserName, WebDriver driver, String testInfoPath ) {
        super( browserName, driver, testInfoPath );
    }

    /**
     * 指定スペースのバックアップ実行
     * 
     * @param space 対象スペース名 (文字大小区別)
     * @param type 実行バックアップタイプ (html,xml)
     */
    public void spaceBackup( String space, String type ) {
        LOG.log( Level.INFO, "【" + space + "】  backupType:" + type + " Start" );

        // ログインまでのアクション実行
        loginAction();

        // エクスポート完了までのアクション実行
        backupAction( space, type );

        // エクスポートしたファイルのDL
        FileLoader( "here", testInfo.getProperty( "downloadDir" ), "Confluence-" + space + "-export-" + CaputureUtils.getYYYYMMDD() + type + ".zip" );
    }

    /**
     * ログイン操作実行
     */
    private void loginAction() {
        driver.get( baseURL + "/login" );
        driver.findElement( By.id( "username" ) ).clear();
        driver.findElement( By.id( "username" ) ).sendKeys( testInfo.getProperty( "user" ) );
        driver.findElement( By.id( "password" ) ).clear();
        driver.findElement( By.id( "password" ) ).sendKeys( testInfo.getProperty( "pass" ) );
        driver.findElement( By.id( "login" ) ).click();
        wait.until( titleContains( "Extic" ) );
    }

    /**
     * エクスポート完了までの操作実行
     * 
     * @param space 対象スペース
     * @param type 実行バックアップタイプ (html,xml)
     */
    private void backupAction( String space, String type ) {

        // スペースツール＞エクスポートタブ遷移＞バックアップタイプ指定で実行まで
        driver.get( baseURL + testInfo.getProperty( space ) );
        if ( type.contains( "html" ) ) {
            driver.findElement( By.id( "format-export-format-html" ) ).click();
        }
        else if ( type.contains( "xml" ) ) {
            driver.findElement( By.id( "format-export-format-xml" ) ).click();
        }
        driver.findElement( By.name( "confirm" ) ).click();
        wait.until( visibilityOf( driver.findElement( By.id( "contentOptionAll" ) ) ) );
        driver.findElement( By.id( "contentOptionVisible" ) ).click();
        driver.findElement( By.id( "contentOptionAll" ) ).click();
        driver.findElement( By.name( "confirm" ) ).click();
        wait.until( visibilityOf( driver.findElement( By.id( "percentComplete" ) ) ) );

        // エクスポート完了待ち
        String lastTime = "";
        int percent = 0;
        int timeout = Integer.parseInt( testInfo.getProperty( "timeout" ) );
        int retryCount = 0;
        for ( ; percent < 100; ) {
            try {
                WebElement element = driver.findElement( By.id( "percentComplete" ) );
                if ( element != null ) {
                    percent = Integer.parseInt( element.getText() );
                    // "taskElapsedTime"に前回と同じ秒数が出ているのはタイムアウト状態とし指定回数以上表示で強制タイムアウトとする
                    if ( lastTime.equals( driver.findElement( By.id( "taskElapsedTime" ) ).getText() ) ) {
                        if ( ++retryCount > timeout ) {
                            LOG.log( Level.WARNING, "【" + space + "】 【TIMEOUT】  backupType:" + type );
                            fail( "【FAILED】 TIMEOUT " + space + " backupType:" + type );
                        }
                    }
                    lastTime = driver.findElement( By.id( "taskElapsedTime" ) ).getText();
                    System.out.println( "percent:" + percent + " lastTime:" + lastTime );
                }
                Thread.sleep( 5000 );
            }
            catch ( Exception e ) {
                LOG.log( Level.WARNING, "【" + space + "】 downloading error", e );
                percent = 0;
            }
        }
    }

    /**
     * ファイルダウンロード処理
     * 
     * @param linkText クリック対象のリンク名
     * @param downloadDir DL先ディレクトリ
     * @param rename DL完了後のファイル名
     */
    private void FileLoader( String linkText, String downloadDir, String rename ) {
        // DLする為のリンククリック
        WebElement download = driver.findElement( By.linkText( linkText ) );
        download.click();

        // ファイルダウンロード待ち
        try {
            Thread.sleep( 500 );
            boolean downloading = true;
            int fileIndex = 0;
            String fileName = "none";
            File dir = new File( downloadDir );
            while ( downloading ) {
                downloading = false;
                File[] list = dir.listFiles();
                for ( int i = 0; i < list.length; i++ ) {
                    // DL中のファイル名がわからないのでchromeの一時ファイルが無くなるまではDL未完了とする
                    if ( list[ i ].getName().lastIndexOf( ".crdownload" ) > 0 ) {
                        downloading = true;
                        fileIndex = i;
                        fileName = list[ i ].getName().replaceFirst( ".crdownload", "" );
                        System.out.println( "downloading list[" + i + "]: " + list[ i ].getName() );
                        break;
                    }
                }
                // DL完了 リネーム実行
                if ( !downloading ) {
                    File dlFile = null;
                    if ( list.length >= fileIndex ) {
                        for ( int i = 0; i < list.length; i++ ) {
                            // DL中のファイル名がわからないのでchromeの一時ファイルが無くなるまではDL未完了とする
                            if ( list[ i ].getName().equals( fileName ) ) {
                                dlFile = list[ i ];
                            }
                        }
                    }
                    else {
                        dlFile = list[ fileIndex ];
                    }

                    if ( dlFile != null ) {
                        File renameFile = new File( downloadDir + "/" + rename );
                        dlFile.renameTo( renameFile );
                        LOG.log( Level.INFO, "downloadComplate " + renameFile.getPath() );
                    }
                    else {
                        LOG.log( Level.WARNING, "downloadComplate fileRename Failed" );
                    }
                }
                Thread.sleep( 5000 );
            }
        }
        catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
