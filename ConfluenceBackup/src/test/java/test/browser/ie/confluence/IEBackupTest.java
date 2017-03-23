package test.browser.ie.confluence;

import org.junit.Test;

import test.browser.ie.BrowserIEDriver;
import test.common.confluence.CommonBackup;

public class IEBackupTest extends BrowserIEDriver {

    private CommonBackup backup;

    @Override
    protected void setupProfile() {
    }

    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup("IE", getDriver(), "testInfo/backup.properties");
    }


//    @Test
//    public void supportBackupHTML() {
//        backup.supportSpaceBackup( "html" );
//    }

}
