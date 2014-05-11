package jp.co.sample.springoxm;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

@Component
public class PersonXmlServiceImpl implements PersonXmlService {

    private static final String outDir = "tmp";

    @Autowired
    private Marshaller marshaller;

    @Autowired
    private Unmarshaller unmarshaller;

    private String path;

    public PersonXmlServiceImpl() {
        path = outDir + File.separator + getClass().getName() + ".xml";
    }

    @Override
    public void marshal() {
        PersonElement person = new PersonElement();
        person.setName("山田 太郎");
        person.setAge(20);

        try {
            marshaller.marshal(person, new StreamResult(new File(path)));
        } catch (IOException e) {
            throw new RuntimeException("XMLの書き込みに失敗しました。", e);
        }
    }

    @Override
    public void unmarshal() {
        try {
            PersonElement person = (PersonElement) unmarshaller.unmarshal(new StreamSource(
                    new File(path)));
            print("person=" + person);
        } catch (IOException e) {
            throw new RuntimeException("XMLの読み込みに失敗しました。", e);
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }

}
