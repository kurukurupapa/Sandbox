package jp.co.sample.springoxm;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringOxmTest {

    @Test
    public void test() throws IOException {
        // 準備
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "/jp.co.sample.springoxm.applicationContext.xml");
        PersonXmlService service = appContext.getBean(PersonXmlService.class);

        // 実行
        service.marshal();
        service.unmarshal();

        // 検証
        // 例外が発生しなければOK
    }

}
