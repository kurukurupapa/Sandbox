package jp.co.sample.springoxm;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * サンプルドメイン
 */
@XmlRootElement
public class PersonElement {

    /**
     * 名前
     */
    private String name;

    /**
     * 年齢
     */
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
