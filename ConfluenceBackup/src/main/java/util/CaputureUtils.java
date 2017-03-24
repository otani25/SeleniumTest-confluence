/**
*
* クラス名
*   CaptureUtils.java
*
* 概要
*   キャプチャ用クラス
*/

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

    /**
     * キャプチャ取得
     * 
     * @param driver キャプチャ取得対象ブラウザ
     * @param params ファイル名要素(複数指定で_繋ぎ)
     */
    public static void getScreenshot( TakesScreenshot driver, String... params ) {
        String filePath = getFilePath( params );
        getScreenshot( driver, filePath );
    }

    /**
     * キャプチャ取得
     * 
     * @param driver キャプチャ取得対象ブラウザ
     * @param filePath 出力ファイルパス
     */
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

    /**
     * 整形済ファイルパス取得
     * 
     * @param params ファイルパス要素
     * @return String ファイルパス名
     */
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

    /**
     * 年月日取得
     * 
     * @return 年月日文字列
     */
    public static String getYYYYMMDD() {
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
