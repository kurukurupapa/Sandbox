package jp.co.sample.springjdbc;

/**
 * Personテーブル更新のDAOインターフェースです。
 */
public interface PersonNamelessParamUpdateDao {

    int insert_RawParam(String name, int age);

    int update_RawParam(long id, String name, int age);

    int delete_RawParam(long id);

}
