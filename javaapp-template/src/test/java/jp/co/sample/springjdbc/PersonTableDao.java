package jp.co.sample.springjdbc;

/**
 * Personテーブルの作成/削除を行うDAOインターフェースです。
 */
public interface PersonTableDao {

    boolean isExist();

    void create();

    void drop();

}