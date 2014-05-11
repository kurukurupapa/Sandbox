package jp.co.sample.commons;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Apache Commons CLI の動作確認です。
 */
public class CommonsCliTest {

    private static Options options;

    private boolean help;
    private CommandLine cl;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        options = new Options();
        options.addOption("h", "help", false, "ヘルプを表示します。");
        options.addOption("a", "option-without-arg", false, "引数なしオプションです。");
        options.addOption("b", "option-with-arg", true, "引数ありオプションです。");
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUsage() {
        // 準備
        HelpFormatter formatter = new HelpFormatter();

        // 実行
        formatter.printHelp("java " + getClass().getName()
                + " [オプション] <入力ファイル>", options);

        // 検証
        // 例外発生しなければOK
    }

    @Test
    public void testParse_引数なしオプション() throws ParseException {
        // 準備
        BasicParser parser = new BasicParser();

        // 実行
        // 検証
        CommandLine cl;

        cl = parser.parse(options, new String[] { "-a" });
        assertThat(cl.hasOption("a"), is(true));
        assertThat(cl.hasOption("option-without-arg"), is(true));

        cl = parser.parse(options, new String[] { "--option-without-arg" });
        assertThat(cl.hasOption("a"), is(true));
        assertThat(cl.hasOption("option-without-arg"), is(true));
    }

    @Test
    public void testParse_引数ありオプション_OK() throws ParseException {
        // 準備
        BasicParser parser = new BasicParser();

        // 実行
        // 検証
        CommandLine cl;

        cl = parser.parse(options, new String[] { "-b", "b-arg" });
        assertThat(cl.getOptionValue("b"), is("b-arg"));
        assertThat(cl.getOptionValue("option-with-arg"), is("b-arg"));

        cl = parser.parse(options,
                new String[] { "--option-with-arg", "b-arg" });
        assertThat(cl.getOptionValue("b"), is("b-arg"));
        assertThat(cl.getOptionValue("option-with-arg"), is("b-arg"));
    }

    @Test(expected = MissingArgumentException.class)
    public void testParse_引数ありオプション_NG() throws ParseException {
        // 準備
        BasicParser parser = new BasicParser();

        // 実行
        // 検証
        parser.parse(options, new String[] { "--option-with-arg" });
    }

    @Test
    public void testParse_必須引数() throws ParseException {
        // 準備
        BasicParser parser = new BasicParser();

        // テスト実行
        CommandLine cl = parser.parse(options, new String[] { "infile" });

        // 検証
        assertThat(cl.getArgs().length, is(1));
        assertThat(cl.getArgs()[0], is("infile"));
    }

}
