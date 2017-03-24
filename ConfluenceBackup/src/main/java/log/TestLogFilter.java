/**
*
* クラス名
*   TestLogFilter.java
*
* 概要
*   テスト実行時のログのみ出力するログフィルタ
*/

package log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class TestLogFilter implements Filter {

    /**
     * ロガー名が"test" "CaptureUtils" を含む場合のみログ出力する
     */
    @Override
    public boolean isLoggable( LogRecord record ) {
        String logger = record.getLoggerName();
        if ( logger.contains( "test" ) || logger.contains( "CaputureUtils" ) ) {
            return true;
        }
        return false;
    }

}
