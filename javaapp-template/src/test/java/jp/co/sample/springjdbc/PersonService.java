/**
 *
 */
package jp.co.sample.springjdbc;

/**
 * Personに関するサービスインターフェースです。
 */
public interface PersonService {

    void runQueryTransactionForCount();

    void runQueryTransactionForData();

    void runUpdateTransactionForInsert();

    void runUpdateTransactionForInsertThrowException();

    void runUpdateTransactionForDelete();

    int getCount();

}
