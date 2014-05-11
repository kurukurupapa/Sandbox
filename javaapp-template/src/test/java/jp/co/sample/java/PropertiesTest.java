package jp.co.sample.java;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

    private static final String outDir = "tmp";

    /**
     * クラスパスの通った場所にあるプロパティファイルを読み込みます。
     *
     * @throws IOException
     */
    @Test
    public void testLoad() throws IOException {
        // 準備
        String filepath = "/" + this.getClass().getName() + ".properties";

        // 実行
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream(filepath));
        String actual = prop.getProperty("item1");

        // 検証
        assertThat(actual, is("値1"));
    }

    /**
     * プロパティファイルを書き込みます。
     *
     * @throws IOException
     */
    @Test
    public void testStore() throws IOException {
        // 準備
        String filepath = outDir + File.separator + this.getClass().getName()
                + ".properties";

        // 実行
        Properties prop = new Properties();
        prop.setProperty("item1", "値1");
        prop.setProperty("item2", "値2");
        prop.setProperty("item3", "値3");

        OutputStream os = new BufferedOutputStream(new FileOutputStream(
                filepath));
        prop.store(os, "コメントです。");
        os.close();

        // 検証
        // 例外が発生しなければOK
    }

}
