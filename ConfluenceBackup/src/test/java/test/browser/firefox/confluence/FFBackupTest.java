package test.browser.firefox.confluence;

import org.junit.Test;

import test.browser.firefox.BrowserFirefoxDriver;
import test.common.confluence.CommonBackup;

public class FFBackupTest extends BrowserFirefoxDriver {

    private CommonBackup backup;

    @Override
    protected void setupProfile() {
        profile.setEnableNativeEvents(true);
    }

    @Override
    public void preTest() {
        super.preTest();
        backup = new CommonBackup("Firefox", getDriver(), "testInfo/backup.properties");
    }

//    @Test
//    public void supportBackupHTML() {
//        backup.supportSpaceBackup( "html" );
//    }

}
