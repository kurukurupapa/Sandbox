package jp.co.sample.springjdbc;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Personテーブル更新のDAO実装クラスです。
 * <p>
 * JDBC操作に、NamedParameterJdbcTemplateクラスを使用しています。
 * </p>
 */
@Repository
public class PersonNamedParamUpdateDaoImpl implements PersonNamedParamUpdateDao {

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * レコードを登録します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * </p>
     *
     * @param name
     *            名前
     * @param age
     *            年齢
     * @return 登録件数
     */
    @Override
    public int insert_MapParam(String name, int age) {
        return this.npJdbcTemplate
                .update("insert into APP.PERSON (NAME, AGE, TIMESTAMP) values (:name, :age, :timestamp)",
                        new MapSqlParameterSource()
                                .addValue("name", name)
                                .addValue("age", age)
                                .addValue(
                                        "timestamp",
                                        new Timestamp(System
                                                .currentTimeMillis())));
    }

    /**
     * レコードを登録します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * </p>
     *
     * @param Person
     *            登録対象
     * @return 登録件数
     */
    @Override
    public int insert_BeanParam(Person Person) {
        return this.npJdbcTemplate
                .update("insert into APP.PERSON (NAME, AGE, TIMESTAMP) values (:name, :age, :timestamp)",
                        new BeanPropertySqlParameterSource(Person));
    }

    /**
     * レコードを登録します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * </p>
     *
     * @param name
     *            名前
     * @param age
     *            年齢
     * @return 登録レコードのID
     */
    @Override
    public long insertAndReturnKey_MapParam(String name, int age) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.npJdbcTemplate
                .update("insert into APP.PERSON (NAME, AGE, TIMESTAMP) values (:name, :age, :timestamp)",
                        new MapSqlParameterSource()
                                .addValue("name", name)
                                .addValue("age", age)
                                .addValue(
                                        "timestamp",
                                        new Timestamp(System
                                                .currentTimeMillis())),
                        keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * レコードを登録します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * </p>
     *
     * @param Person
     *            登録対象
     * @return 登録レコードのID
     */
    @Override
    public long insertAndReturnKey_BeanParam(Person Person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.npJdbcTemplate
                .update("insert into APP.PERSON (NAME, AGE, TIMESTAMP) values (:name, :age, :timestamp)",
                        new BeanPropertySqlParameterSource(Person), keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * レコードを更新します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * </p>
     *
     * @param id
     *            ID
     * @param name
     *            名前
     * @param age
     *            年齢
     * @return 更新結果
     */
    @Override
    public int update_MapParam(long id, String name, int age) {
        return this.npJdbcTemplate.update(
                "update APP.PERSON set NAME=:name, AGE=:age where ID=:id",
                new MapSqlParameterSource().addValue("id", id)
                        .addValue("name", name).addValue("age", age));
    }

    /**
     * レコードを更新します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * </p>
     *
     * @param Person
     *            更新対象
     * @return 更新結果
     */
    @Override
    public int update_BeanParam(Person Person) {
        return this.npJdbcTemplate.update(
                "update APP.PERSON set NAME=:name, AGE=:age where ID=:id",
                new BeanPropertySqlParameterSource(Person));
    }

    /**
     * レコードを削除します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * </p>
     *
     * @param id
     *            削除対象のID
     * @return 削除結果
     */
    @Override
    public int delete_MapParam(long id) {
        return this.npJdbcTemplate.update(
                "delete from APP.PERSON where ID=:id",
                new MapSqlParameterSource().addValue("id", id));
    }

    /**
     * レコードを削除します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * </p>
     *
     * @param person
     *            削除対象
     * @return 削除結果
     */
    @Override
    public int delete_BeanParam(Person person) {
        return this.npJdbcTemplate.update(
                "delete from APP.PERSON where ID=:id",
                new BeanPropertySqlParameterSource(person));
    }

}
