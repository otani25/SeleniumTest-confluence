/**
*
* クラス名
*   CHBackupTest.java
*
* 概要
*   confluenceのバックアップをchromeブラウザで実行するクラス
*/

package test.browser.chrome.confluence;

import org.junit.Test;

import test.browser.chrome.BrowserChromeDriver;
import test.common.confluence.CommonBackup;

public class CHBackupTest extends BrowserChromeDriver {

    private CommonBackup backup;

    /**
     * ブラウザ設定
     */
    @Override
    protected void setupProfile() {
    }

    /**
     * Chromeブラウザで動作するテストインスタンス生成
     */
    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup( "Chrome", getDriver(), "testInfo/backup.properties" );
    }

    /**
     * サポートスペースのHTMLバックアップ実行
     */
    @Test
    public void supportBackupHTML() {
        backup.supportSpaceBackup( "html" );
    }

    /**
     * サポートスペースのXMLバックアップ実行
     */
    @Test
    public void supportBackupXML() {
        backup.supportSpaceBackup( "xml" );
    }

    /**
     * 業務スペースのHTMLバックアップ実行
     */
    @Test
    public void workBackupHTML() {
        backup.workSpaceBackup( "html" );
    }

    /**
     * 業務スペースのXMLバックアップ実行
     */
    @Test
    public void workBackupXML() {
        backup.workSpaceBackup( "xml" );
    }

    /**
     * プロダクトスペースのHTMLバックアップ実行
     */
    @Test
    public void productBackupHTML() {
        backup.productSpaceBackup( "html" );
    }

    /**
     * プロダクトスペースのXMLバックアップ実行
     */
    @Test
    public void productBackupXML() {
        backup.productSpaceBackup( "xml" );
    }

}
