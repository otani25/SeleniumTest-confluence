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

import browser.chrome.BrowserChromeDriver;
import test.common.confluence.CommonBackup;

public class CHBackupTest extends BrowserChromeDriver {

    private CommonBackup backup;

    /**
     * ブラウザオプション設定
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
        backup.spaceBackup( "supportSpace", "html" );
    }

    /**
     * サポートスペースのXMLバックアップ実行
     */
    @Test
    public void supportBackupXML() {
        backup.spaceBackup( "supportSpace", "xml" );
    }

    /**
     * 業務スペースのHTMLバックアップ実行
     */
    @Test
    public void workBackupHTML() {
        backup.spaceBackup( "workSpace", "html" );
    }

    /**
     * 業務スペースのXMLバックアップ実行
     */
    @Test
    public void workBackupXML() {
        backup.spaceBackup( "workSpace", "xml" );
    }

    /**
     * プロダクトスペースのHTMLバックアップ実行
     */
    @Test
    public void productBackupHTML() {
        backup.spaceBackup( "productSpace", "html" );
    }

    /**
     * プロダクトスペースのXMLバックアップ実行
     */
    @Test
    public void productBackupXML() {
        backup.spaceBackup( "productSpace", "xml" );
    }

}
