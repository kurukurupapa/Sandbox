package jp.co.sample.springjdbc;

/**
 * Personテーブル更新のDAOインターフェースです。
 */
public interface PersonNamedParamUpdateDao {

    int insert_MapParam(String name, int age);

    int insert_BeanParam(Person Person);

    long insertAndReturnKey_MapParam(String name, int age);

    long insertAndReturnKey_BeanParam(Person Person);

    int update_MapParam(long id, String name, int age);

    int update_BeanParam(Person Person);

    int delete_MapParam(long id);

    int delete_BeanParam(Person person);

}