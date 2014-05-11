package jp.co.sample.springjdbc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring JDBC トランザクションの動作確認です。
 */
public class SpringTxTest {

    private static ApplicationContext appContext;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        appContext = new ClassPathXmlApplicationContext(
                "/jp.co.sample.springjdbc.applicationContext.xml");
        PersonTableDao tableDao = appContext.getBean(PersonTableDao.class);

        // // 既にテーブルが存在していたら削除
        // try {
        // tableDao.drop();
        // } catch (DataAccessException e) {
        // // テーブルが存在してなかったのでOK
        // }

        // テーブル作成
        if (!tableDao.isExist()) {
            tableDao.create();
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        PersonService service = appContext.getBean(PersonService.class);

        // INSERT（例外発生）
        try {
            service.runUpdateTransactionForInsertThrowException();
        } catch (RuntimeException e) {
            print(e.toString());
        }
        service.runQueryTransactionForCount();
        assertThat(service.getCount(), is(0));

        // INSERT（成功）
        service.runUpdateTransactionForInsert();
        service.runQueryTransactionForCount();
        assertThat(service.getCount(), is(1));

        // DELETE
        service.runUpdateTransactionForDelete();
        service.runQueryTransactionForCount();
        assertThat(service.getCount(), is(0));

    }

    private void print(String msg) {
        System.out.println(msg);
    }

}
