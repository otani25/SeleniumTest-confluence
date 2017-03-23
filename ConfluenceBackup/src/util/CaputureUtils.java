package util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CaputureUtils {

    private static Logger LOG = Logger.getLogger( CaputureUtils.class.getName() );

    public static void getScreenshot( TakesScreenshot driver, String... params ) {
        String filePath = getFilePath( params );
        getScreenshot( driver, filePath );
    }

    public static void getScreenshot( TakesScreenshot driver, String filePath ) {
        File scrFile = driver.getScreenshotAs( OutputType.FILE );
        try {
            FileUtils.copyFile( scrFile, new File( filePath ) );
            LOG.log( Level.FINE, "【CAPUTURE】" + filePath );
        }
        catch ( IOException e ) {
            LOG.log( Level.WARNING, "【CAPUTURE】IOException!!", e );
        }
    }

    public static String getFilePath( String... params ) {
        StringBuilder builder = new StringBuilder();
        builder.append( "./screenshot/" );
        builder.append( getYYYYMMDD() );
        for ( String param : params ) {
            builder.append( "_" );
            builder.append( param );
        }
        builder.append( ".png" );
        return builder.toString();
    }

    private static String getYYYYMMDD() {
        StringBuilder builder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        builder.append( calendar.get( Calendar.YEAR ) );
        int month = calendar.get( Calendar.MONTH ) + 1;
        if ( month < 10 ) {
            builder.append( "0" );
        }
        builder.append( month );

        int dayOfMonth = calendar.get( Calendar.DAY_OF_MONTH );
        if ( dayOfMonth < 10 ) {
            builder.append( "0" );
        }
        builder.append( dayOfMonth );

        return builder.toString();
    }
}
