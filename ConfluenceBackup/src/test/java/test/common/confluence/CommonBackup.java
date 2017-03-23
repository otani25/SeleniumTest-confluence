package test.common.confluence;

import test.common.CommonManager;
import org.openqa.selenium.*;
import util.CaputureUtils;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.io.File;

public class CommonBackup extends CommonManager {

    public CommonBackup( String browserName, WebDriver driver, String testInfoPath ) {
        super( browserName, driver, testInfoPath );
        // TODO Auto-generated constructor stub
    }

    public void supportSpaceBackup( String type ) {
        loginAction();

        driver.get( baseURL + testInfo.getProperty( "supportSpace" ) );
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

        String lastTime;
        int percent = 0;
        for ( ; percent < 100; ) {
            try {
                WebElement element = driver.findElement( By.id( "percentComplete" ) );
                if ( element != null ) {
                    percent = Integer.parseInt( element.getText() );
                    lastTime = driver.findElement( By.id( "taskElapsedTime" ) ).getText();
                    System.out.println( "percent:" + percent + " lastTime:" + lastTime );
                }
                Thread.sleep( 5000 );
            }
            catch ( Exception e ) {
                System.out.println( "readError" );
                percent = 0;
            }
        }

        try {
            FileLoader( "here", testInfo.getProperty( "downloadDir" ) );
        }
        catch ( InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String filePath = CaputureUtils.getFilePath( getClass().getName(), browserName, "supportbackup" );
        CaputureUtils.getScreenshot( ( TakesScreenshot ) driver, filePath );
    }

    public void workSpaceBackup( String type ) {
        loginAction();

        driver.get( baseURL + testInfo.getProperty( "workSpace" ) );
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

        String lastTime;
        int percent = 0;
        for ( ; percent < 100; ) {
            try {
                WebElement element = driver.findElement( By.id( "percentComplete" ) );
                if ( element != null ) {
                    percent = Integer.parseInt( element.getText() );
                    lastTime = driver.findElement( By.id( "taskElapsedTime" ) ).getText();
                    System.out.println( "percent:" + percent + " lastTime:" + lastTime );
                }
                Thread.sleep( 5000 );
            }
            catch ( Exception e ) {
                System.out.println( "readError" );
                percent = 0;
            }
        }

        try {
            FileLoader( "here", testInfo.getProperty( "downloadDir" ) );
        }
        catch ( InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String filePath = CaputureUtils.getFilePath( getClass().getName(), browserName, "worktbackup" );
        CaputureUtils.getScreenshot( ( TakesScreenshot ) driver, filePath );
    }

    public void productSpaceBackup( String type ) {
        loginAction();

        driver.get( baseURL + testInfo.getProperty( "productSpace" ) );
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

        String lastTime;
        int percent = 0;
        for ( ; percent < 100; ) {
            try {
                WebElement element = driver.findElement( By.id( "percentComplete" ) );
                if ( element != null ) {
                    percent = Integer.parseInt( element.getText() );
                    lastTime = driver.findElement( By.id( "taskElapsedTime" ) ).getText();
                    System.out.println( "percent:" + percent + " lastTime:" + lastTime );
                }
                Thread.sleep( 5000 );
            }
            catch ( Exception e ) {
                System.out.println( "readError" );
                percent = 0;
            }
        }

        try {
            FileLoader( "here", testInfo.getProperty( "downloadDir" ) );
        }
        catch ( InterruptedException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String filePath = CaputureUtils.getFilePath( getClass().getName(), browserName, "worktbackup" );
        CaputureUtils.getScreenshot( ( TakesScreenshot ) driver, filePath );
    }

    /**
     * loginAction
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
     * FileLoader Click the link to execute the download
     * 
     * @param String linkText
     */
    public void FileLoader( String linkText, String downloadDir ) throws InterruptedException {
        WebElement download = driver.findElement( By.linkText( linkText ) );
        download.click();
        Thread.sleep( 10000 );

        // downloading
        boolean downloading = true;
        File dir = new File( downloadDir );
        while ( downloading ) {
            downloading = false;
            File[] list = dir.listFiles();
            for ( int i = 0; i < list.length; i++ ) {
                if ( list[ i ].getName().lastIndexOf( ".crdownload" ) > 0 ) {
                    downloading = true;
                    System.out.println( "downloading : " + list[ i ].getName() );
                    break;
                }
            }
            Thread.sleep( 10000 );
        }
    }
}
