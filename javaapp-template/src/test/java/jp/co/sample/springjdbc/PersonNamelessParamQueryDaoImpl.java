package jp.co.sample.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Personテーブル検索のDAO実装クラスです。
 * <p>
 * JDBC操作に、JdbcTemplateクラスを使用しています。
 * JdbcTemplateクラスでは、SQL文内のプレースフォルダーの順番と、配列で渡すパラメータの順番を合わせる必要があります。
 * 仕様変更やリファクタリングで、意図せず順番がズレてしまう可能性があるため、当実装はあまり使わないほうが良いと思います。
 * </p>
 *
 * @deprecated
 */
@Repository
public class PersonNamelessParamQueryDaoImpl implements
        PersonNamelessParamQueryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * レコード件数を取得します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * </p>
     *
     * @param name
     *            検索対象の名前
     * @return 件数
     */
    @Override
    public int count(String name) {
        return this.jdbcTemplate.queryForObject(
                "select count(*) from APP.Person where NAME=?",
                new Object[] { name }, Integer.class);
    }

    /**
     * 1レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * レコード/インスタンス変換：RowMapperクラス<br/>
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_RawMapper(String name) {
        return this.jdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=?", new String[] { name },
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
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンス
     */
    @Override
    public Person findSingle_BeanMapper(String name) {
        return this.jdbcTemplate.queryForObject(
                "select * from APP.Person where NAME=?", new Object[] { name },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * 複数レコードを検索します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * レコード/インスタンス変換：RowMapperクラス<br/>
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンスのリスト
     */
    @Override
    public List<Person> findList_RawMapper(String name) {
        return this.jdbcTemplate.query(
                "select * from APP.Person where NAME=? order by NAME",
                new Object[] { name }, new RowMapper<Person>() {
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
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * レコード/インスタンス変換：BeanPropertyRowMapperクラス<br/>
     * </p>
     *
     * @param name
     *            検索する名前
     * @return Personインスタンスのリスト
     */
    @Override
    public List<Person> findList_BeanMapper(String name) {
        return this.jdbcTemplate.query(
                "select * from APP.Person where NAME=? order by NAME",
                new Object[] { name }, new BeanPropertyRowMapper<Person>(
                        Person.class));
    }

}
