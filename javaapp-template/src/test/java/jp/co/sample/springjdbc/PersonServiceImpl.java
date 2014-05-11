package jp.co.sample.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Personに関するサービス実装クラスです。
 */
@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonNamedParamQueryDao queryDao;

    @Autowired
    private PersonNamedParamUpdateDao updateDao;

    private long id;

    private int count;

    @Override
    public void runQueryTransactionForCount() {
        count = queryDao.count_MapParam("山田 花子");
        print("count=" + count);
    }

    @Override
    public void runQueryTransactionForData() {
        print("findSingle_MapParam_BeanMapper="
                + queryDao.findSingle_MapParam_BeanMapper("山田 花子"));
    }

    @Override
    public void runUpdateTransactionForInsert() {
        id = updateDao.insertAndReturnKey_MapParam("山田 花子", 40);
        print("insertAndReturnKey_MapParam=" + id);
    }

    @Override
    public void runUpdateTransactionForInsertThrowException() {
        print("insert前のcount=" + queryDao.count_MapParam("山田 花子"));
        print("insert=" + updateDao.insert_MapParam("山田 花子", 41));
        print("insert後のcount=" + queryDao.count_MapParam("山田 花子"));
        throw new RuntimeException("例外送信");
    }

    @Override
    public void runUpdateTransactionForDelete() {
        print("delete=" + updateDao.delete_MapParam(id));
    }

    @Override
    public int getCount() {
        return count;
    }

    private void print(String msg) {
        System.out.println(msg);
    }

}
