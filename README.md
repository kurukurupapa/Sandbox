java-template
=============
かなり個人的なJava開発用テンプレートプロジェクト群です。

javaappp-template
-----------------
Javaアプリケーション開発用のテンプレートプロジェクトです。
EclipseでMavenプロジェクトとして作成してあります。

使用ライブラリ
--------------
### Apache Commons ###
便利です。
* [Apache Commons](http://commons.apache.org/)

### Apache Derby ###
組み込みデータベースです。
Java SE 6以降のJDKに、Java DBとして同梱されていたりします。
非常に手軽に使えるRDBMSとして、当テンプレートプロジェクトに含めています。

* [Apache Derby](http://db.apache.org/derby/)

### Apache Log4j 1.x ###
1.x系を使っています。

* [Apache Log4j](http://logging.apache.org/log4j/)
* [Apache Log4j 1.2](http://logging.apache.org/log4j/1.2/)

### AspectJ ###
Spring AOPから呼ばれています。

### JUnit ###
4.x系を使っています。4.x系はアノテーションを使うので、JavaのJ2SE 5.0以上が必要となります。

### Spring Framework ###
主に、DI, AOP, JDBC, OXMを使用しています。

* [Spring](http://spring.io/)
* [Spring Framework Reference Documentation 3.2.8.RELEASE](http://docs.spring.io/spring/docs/3.2.8.RELEASE/spring-framework-reference/htmlsingle/)
* [Spring Framework 3.2.8.RELEASE API](http://docs.spring.io/spring/docs/3.2.8.RELEASE/javadoc-api/)
