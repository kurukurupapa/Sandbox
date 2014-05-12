package jp.co.sample.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Personテーブル検索のDAO実装クラスです。
 * <p>
 * JDBC操作に、NamedParameterJdbcTemplateクラスを使用しています。
 * </p>
 */
@Repository
public class PersonNamedParamQueryDaoImpl implements PersonNamedParamQueryDao {

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    /**
     * レコード件数を取得します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSource渡し<br/>
     * </p>
     * <p>
     * 割愛しますが、SQLパラメータ指定には、BeanPropertySqlParameterSourceクラスを使用することも可能です。
     * レコード/インスタンス変換には、RowMapperクラスを使用することも可能です。
     * </p>
     *
     * @param name
     *            検索対象の名前
     * @return 件数
     */
    @Override
    public int count_MapParam(String name) {
        return this.npJdbcTemplate.queryForObject(
                "select count(*) from APP.Person where NAME=:name",
                new MapSqlParameterSource().addValue("name", name), Integer.class);
    }

    /**
     * 1レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     * <p>
     * 特定の条件で検索する場合に、使用する実装方法だと思います。たぶん使用頻度が高いと思います。
     * 検索したレコードの名前と、Beanのプロパティ名は、対応付け可能である必要があります。
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_MapParam_BeanMapper(String name) {
        return this.npJdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=:name",
                new MapSqlParameterSource().addValue("name", name),
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * 1レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     * <p>
     * 特定の条件で検索する場合に、使用する実装方法だと思います。
     * 検索したレコードの名前と、Beanのプロパティ名が、自動で対応付けられない場合に使用することになりそうです。
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_MapParam_RawMapper(String name) {
        return this.npJdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=:name",
                new MapSqlParameterSource().addValue("name", name),
                new RowMapper<Person>() {
                    @Override
                    public Person mapRow(ResultSet rs, int num)
                            throws SQLException {
                        Person Person = new Person();
                        Person.setId(rs.getLong("ID"));
                        Person.setName(rs.getString("NAME"));
                        Person.setAge(rs.getInt("AGE"));
                        Person.setTimestamp(rs.getTimestamp("TIMESTAMP"));
                        return Person;
                    }
                });
    }

    /**
     * 1レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     * <p>
     * 既に検索済み/保持しているPersonインスタンスが存在する場合に、使用する実装方法だと思います。
     * メソッド引数では、検索条件が分かりにくいので、メソッド名の付け方を工夫する必要がありそうです。
     * 検索したレコードの名前と、Beanのプロパティ名は、対応付け可能である必要があります。
     * </p>
     *
     * @param person
     *            検索条件を保持するPersonインスタンス
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_BeanParam_BeanMapper(Person person) {
        return this.npJdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=:name",
                new BeanPropertySqlParameterSource(person),
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * 1レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：BeanPropertySqlParameterSourceクラス<br/>
     * レコード/インスタンス変換：RowMapperクラス<br/>
     * </p>
     * <p>
     * 既に検索済み/保持しているPersonインスタンスが存在する場合に、使用する実装方法だと思います。
     * メソッド引数では、検索条件が分かりにくいので、メソッド名の付け方を工夫する必要がありそうです。
     * 検索したレコードの名前と、Beanのプロパティ名が、自動で対応付けられない場合に使用することになりそうです。
     * </p>
     *
     * @param person
     *            検索条件を保持するPersonインスタンス
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_BeanParam_RawMapper(Person person) {
        return this.npJdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=:name",
                new BeanPropertySqlParameterSource(person),
                new RowMapper<Person>() {
                    @Override
                    public Person mapRow(ResultSet rs, int num)
                            throws SQLException {
                        Person Person = new Person();
                        Person.setId(rs.getLong("ID"));
                        Person.setName(rs.getString("NAME"));
                        Person.setAge(rs.getInt("AGE"));
                        Person.setTimestamp(rs.getTimestamp("TIMESTAMP"));
                        return Person;
                    }
                });
    }

    /**
     * 複数レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：NamedParameterJdbcTemplateクラス<br/>
     * SQLパラメータ指定：MapSqlParameterSourceクラス<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     * <p>
     * 割愛しますが、SQLパラメータ指定には、BeanPropertySqlParameterSourceクラスを使用することも可能です。
     * レコード/インスタンス変換には、RowMapperクラスを使用することも可能です。
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンスのリスト
     */
    @Override
    public List<Person> findList_MapParam_BeanMapper(String name) {
        return this.npJdbcTemplate.query(
                "select * from APP.Person where NAME:name order by NAME",
                new MapSqlParameterSource().addValue("name", name),
                new BeanPropertyRowMapper<Person>(Person.class));
    }

}
