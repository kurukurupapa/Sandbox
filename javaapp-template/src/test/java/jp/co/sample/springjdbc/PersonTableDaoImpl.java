package jp.co.sample.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Personテーブルの作成/削除を行うDAO実装クラスです。
 */
@Repository
public class PersonTableDaoImpl implements PersonTableDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Override
    public boolean isExist() {
        int count = this.npJdbcTemplate
                .queryForObject(
                        "select count(*) from SYS.SYSTABLES where TABLENAME=:tablename",
                        new MapSqlParameterSource().addValue("tablename",
                                "PERSON"), Integer.class);
        return count > 0;
    }

    @Override
    public void create() {
        this.jdbcTemplate.update("create table APP.PERSON ("
                + "ID bigint generated always as identity," //
                + "NAME varchar(100) not null," //
                + "AGE int not null," //
                + "TIMESTAMP timestamp not null," //
                + "constraint PK_PERSON primary key ( ID )" //
                + ")");
    }

    @Override
    public void drop() {
        this.jdbcTemplate.update("drop table APP.PERSON");
    }

}
