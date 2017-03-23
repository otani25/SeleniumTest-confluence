package test.browser.chrome.confluence;

import org.junit.Test;

import test.browser.chrome.BrowserChromeDriver;
import test.common.confluence.CommonBackup;

public class CHBackupTest extends BrowserChromeDriver {

    private CommonBackup backup;

    @Override
    protected void setupProfile() {
    }

    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup("Chrome", getDriver(), "testInfo/backup.properties");
    }

    @Test
    public void supportBackupHTML() {
        backup.supportSpaceBackup( "html" );
    }
    @Test
    public void supportBackupXML() {
        backup.supportSpaceBackup( "xml" );
    }
    @Test
    public void workBackupHTML() {
        backup.workSpaceBackup( "html" );
    }
    @Test
    public void workBackupXML() {
        backup.workSpaceBackup( "xml" );
    }
    @Test
    public void productBackupHTML() {
        backup.productSpaceBackup( "html" );
    }
    @Test
    public void productBackupXML() {
        backup.productSpaceBackup( "xml" );
    }


}
