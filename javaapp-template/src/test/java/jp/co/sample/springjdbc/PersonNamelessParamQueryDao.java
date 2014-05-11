package jp.co.sample.springjdbc;

import java.util.List;

/**
 * Personテーブル検索のDAOインターフェースです。
 */
public interface PersonNamelessParamQueryDao {

    public abstract int count(String name);

    public abstract Person findSingle_RawMapper(String name);

    public abstract Person findSingle_BeanMapper(String name);

    public abstract List<Person> findList_RawMapper(String name);

    public abstract List<Person> findList_BeanMapper(String name);

}