package log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class TestLogFilter implements Filter {

    @Override
    // ロガー名が"test" "CaptureUtils" を含む場合のみログ出力する
    public boolean isLoggable( LogRecord record ) {
        // String message = record.getMessage();
        String logger = record.getLoggerName();
        if ( logger.contains( "test" ) || logger.contains( "CaputureUtils" ) ) {
            return true;
        }
        return false;
    }

}
