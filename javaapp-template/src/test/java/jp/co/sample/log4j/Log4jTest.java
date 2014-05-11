package jp.co.sample.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Apache Log4jの動作確認です。
 */
public class Log4jTest {

    @Test
    public void test() {
        // 準備
        Logger logger = Logger.getLogger(Log4jTest.class);

        // 実行
        logger.trace("TRACEレベルのログメッセージです。");
        logger.debug("DEBUGレベルのログメッセージです。");
        logger.info("INFOレベルのログメッセージです。");
        logger.warn("WARNレベルのログメッセージです。");
        logger.error("ERRORレベルのログメッセージです。");
        logger.fatal("FATALレベルのログメッセージです。");

        // 検証
        // 例外が発生していなければOK
    }

}
