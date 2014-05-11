package jp.co.sample.springjdbc;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring JDBCの動作確認です。
 */
public class SpringJdbcTest {

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
    public void testNamedParamDao() {
        PersonNamedParamQueryDao queryDao = appContext
                .getBean(PersonNamedParamQueryDao.class);
        PersonNamedParamUpdateDao updateDao = appContext
                .getBean(PersonNamedParamUpdateDao.class);

        // SELECT
        print("count_MapParam=" + queryDao.count_MapParam("山田 太郎"));

        // INSERT
        print("insert_MapParam=" + updateDao.insert_MapParam("山田 太郎", 20));

        // SELECT
        print("count_MapParam=" + queryDao.count_MapParam("山田 太郎"));
        Person person1 = queryDao.findSingle_MapParam_BeanMapper("山田 太郎");
        print("findSingle_MapParam_BeanMapper=" + person1.toString());

        // UPDATE
        person1.setAge(21);
        print("update_BeanParam=" + updateDao.update_BeanParam(person1));

        // SELECT
        person1 = queryDao.findSingle_MapParam_BeanMapper("山田 太郎");
        print("findSingle_MapParam_BeanMapper=" + person1.toString());

        // DELETE
        print("delete_BeanParam=" + updateDao.delete_BeanParam(person1));

        // SELECT
        print("count_MapParam=" + queryDao.count_MapParam("山田 太郎"));
    }

    @Test
    public void testNamelessParamDao() {
        PersonNamelessParamQueryDao queryDao = appContext
                .getBean(PersonNamelessParamQueryDao.class);
        PersonNamelessParamUpdateDao updateDao = appContext
                .getBean(PersonNamelessParamUpdateDao.class);

        // SELECT
        print("count=" + queryDao.count("佐藤 次郎"));

        // INSERT
        print("insert_RawParam=" + updateDao.insert_RawParam("佐藤 次郎", 20));

        // SELECT
        print("count=" + queryDao.count("佐藤 次郎"));
        Person person1 = queryDao.findSingle_BeanMapper("佐藤 次郎");
        print("findSingle_BeanMapper=" + person1.toString());

        // UPDATE
        print("update_BeanParam="
                + updateDao.update_RawParam(person1.getId(), person1.getName(),
                        21));

        // SELECT
        person1 = queryDao.findSingle_BeanMapper("佐藤 次郎");
        print("findSingle_BeanMapper=" + person1.toString());

        // DELETE
        print("delete_RawParam=" + updateDao.delete_RawParam(person1.getId()));

        // SELECT
        print("count=" + queryDao.count("佐藤 次郎"));
    }

    private void print(String msg) {
        System.out.println(msg);
    }

}
