/**
*
* クラス名
*   CHBackupTest.java
*
* 概要
*   confluenceのバックアップをfirefoxブラウザで実行するクラス
*/

package test.browser.firefox.confluence;

import org.junit.Test;

import test.browser.firefox.BrowserFirefoxDriver;
import test.common.confluence.CommonBackup;

public class FFBackupTest extends BrowserFirefoxDriver {

    private CommonBackup backup;

    /**
     * ブラウザ設定
     */
    @Override
    protected void setupProfile() {
        profile.setEnableNativeEvents( true );
    }

    /**
     * IEブラウザで動作するテストインスタンス生成
     */
    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup( "Firefox", getDriver(), "testInfo/backup.properties" );
    }

    // @Test
    // public void supportBackupHTML() {
    // backup.supportSpaceBackup( "html" );
    // }

}
