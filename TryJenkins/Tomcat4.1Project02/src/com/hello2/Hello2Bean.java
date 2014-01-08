package com.hello2;

public class Hello2Bean {
	private String envName;

	public Hello2Bean() {
		envName = Messages.getString("env_name");
	}

	public String getEnvName() {
		return envName;
	}

}
