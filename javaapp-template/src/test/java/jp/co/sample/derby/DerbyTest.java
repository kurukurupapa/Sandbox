package jp.co.sample.derby;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Apache Derbyの動作確認です。
 */
public class DerbyTest {

    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:";
    private static final String path = "tmp/derbydb";

    private Connection conn;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // ドライバロード
        Class.forName(driver).newInstance();
    }

    @Test
    public void test() {
        // 準備

        // 実行
        try {
            // DB接続
            connect();

            // テーブル作成
            createTable();

            // テーブル操作
            insert();
            select();

            // テーブル削除
            dropTable();

            // コミット
            commit();

        } catch (SQLException e) {
            throw new RuntimeException("DBアクセスに失敗しました。", e);

        } finally {
            rollback();
            close();
        }
        print("Apache Derby の操作に成功しました。");

        // 検証
        // 例外が発生しなければOK
    }

    private void connect() throws SQLException {
        conn = DriverManager.getConnection(protocol + path + ";create=true");
        conn.setAutoCommit(false);
    }

    private void commit() throws SQLException {
        conn.commit();
    }

    private void rollback() {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("ロールバックに失敗しました。" + e);
            }
        }
    }

    private void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("DBクローズに失敗しました。" + e);
            }
        }
    }

    private void createTable() throws SQLException {
        // すでにテーブルがあったら削除
        try {
            dropTable();
        } catch (SQLException e) {
            // 不要なテーブルがなかった。
        }

        PreparedStatement ps = conn
                .prepareStatement("create table APP.PERSON ( NAME varchar(100) primary key, AGE int not null )");
        int result = ps.executeUpdate();
        ps.close();

        assertThat(result, is(0));
    }

    private void dropTable() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("drop table APP.PERSON");
        int result = ps.executeUpdate();
        ps.close();

        assertThat(result, is(0));
    }

    private void insert() throws SQLException {
        PreparedStatement ps = conn
                .prepareStatement("insert into APP.PERSON (NAME, AGE) values ('山田 太郎', 20)");
        int result = ps.executeUpdate();
        ps.close();

        assertThat(result, is(1));
    }

    private void select() throws SQLException {
        PreparedStatement ps = conn
                .prepareStatement("select * from APP.PERSON where NAME='山田 太郎'");
        ResultSet rs = ps.executeQuery();
        assertThat(rs.next(), is(true));
        assertThat(rs.getString(1), is("山田 太郎"));
        assertThat(rs.getInt(2), is(20));
        ps.close();
    }

    private static void print(String msg) {
        System.out.println(msg);
    }

}
