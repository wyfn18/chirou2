package com.xiaopang.chirou2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Chirou2Application {

	public static void main(String[] args) {
		SpringApplication.run(Chirou2Application.class, args);
	}
}
