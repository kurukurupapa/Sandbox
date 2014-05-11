package jp.co.sample.springjdbc;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * サンプルドメイン
 */
public class Person {

    /**
     * ID
     */
    private long id;

    /**
     * 名前
     */
    private String name;

    /**
     * 年齢
     */
    private int age;

    /**
     * タイムスタンプ
     */
    private Timestamp timestamp;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
