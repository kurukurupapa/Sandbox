package jp.co.sample.springjdbc;

import java.util.List;

/**
 * Personテーブル検索のDAOインターフェースです。
 */
public interface PersonNamedParamQueryDao {

    int count_MapParam(String name);

    Person findSingle_MapParam_BeanMapper(String name);

    Person findSingle_MapParam_RawMapper(String name);

    Person findSingle_BeanParam_BeanMapper(Person person);

    Person findSingle_BeanParam_RawMapper(Person person);

    List<Person> findList_MapParam_BeanMapper(String name);

}