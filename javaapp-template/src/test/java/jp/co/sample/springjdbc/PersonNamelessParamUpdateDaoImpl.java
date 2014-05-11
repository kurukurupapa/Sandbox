package jp.co.sample.springjdbc;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Personテーブル更新のDAO実装クラスです。
 * <p>
 * JDBC操作に、JdbcTemplateクラスを使用しています。
 * JdbcTemplateクラスでは、SQL文内のプレースフォルダーの順番と、配列で渡すパラメータの順番を合わせる必要があります。
 * 仕様変更やリファクタリングで、意図せず順番がズレてしまう可能性があるため、当実装はあまり使わないほうが良いと思います。
 * </p>
 *
 * @deprecated
 */
@Repository
public class PersonNamelessParamUpdateDaoImpl implements
        PersonNamelessParamUpdateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * レコードを登録します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * </p>
     *
     * @param name
     *            名前
     * @param age
     *            年齢
     * @return 登録結果
     */
    public int insert_RawParam(String name, int age) {
        return this.jdbcTemplate
                .update("insert into APP.PERSON (NAME, AGE, TIMESTAMP) values (?, ?, ?)",
                        name, age, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * レコードを更新します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * </p>
     *
     * @param id
     *            更新対象のID
     * @param name
     *            名前
     * @param age
     *            年齢
     * @return 更新結果
     */
    public int update_RawParam(long id, String name, int age) {
        return this.jdbcTemplate
                .update("update APP.PERSON set NAME=?, AGE=? where ID=?", name,
                        age, id);
    }

    /**
     * レコードを削除します。
     * <p>
     * 当メソッドでの実装方法<br/>
     * JDBC操作：JdbcTemplateクラス<br/>
     * SQLパラメータ指定：引数渡し<br/>
     * </p>
     *
     * @param id
     *            削除対象のID
     * @return 削除結果
     */
    public int delete_RawParam(long id) {
        return this.jdbcTemplate
                .update("delete from APP.PERSON where ID=?", id);
    }

}
