<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="hello2Bean" scope="request" class="com.hello2.Hello2Bean" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
</head>
<body>
	<img src="./img/jenkins_logo.png"/>
	<img src="./img/env_logo.png"/>
	<h1>Hello World!</h1>
	<h1>こんにちは！</h1>
	<p><%= new java.util.Date() %></p>
	<p>環境：<jsp:getProperty name="hello2Bean" property="envName" /></p>
</body>
</html>