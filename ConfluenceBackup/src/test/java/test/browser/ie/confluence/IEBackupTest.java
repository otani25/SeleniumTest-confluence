/**
*
* クラス名
*   IEBackupTest.java
*
* 概要
*   confluenceのバックアップをIEブラウザで実行するクラス
*/

package test.browser.ie.confluence;

import org.junit.Test;

import test.browser.ie.BrowserIEDriver;
import test.common.confluence.CommonBackup;

public class IEBackupTest extends BrowserIEDriver {

    private CommonBackup backup;

    /**
     * ブラウザ設定
     */
    @Override
    protected void setupProfile() {
    }

    /**
     * IEブラウザで動作するテストインスタンス生成
     */
    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup( "IE", getDriver(), "testInfo/backup.properties" );
    }

    // @Test
    // public void supportBackupHTML() {
    // backup.supportSpaceBackup( "html" );
    // }

}
