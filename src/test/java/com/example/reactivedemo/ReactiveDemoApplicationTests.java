package com.example.reactivedemo;

import org.junit.Before;
import org.junit.gen5.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

/*
https://blog.csdn.net/io_field/article/details/82801067

JUnit 5
https://www.codetd.com/article/4927518
 */
@SpringBootTest
class ReactiveDemoApplicationTests {

    private WebTestClient webTestClient;

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledOnOs({ OS.WINDOWS, OS.MAC })
    public void onlyOnJava8() {
        System.out.println("该测试在JDK 8中启用");
        System.out.println("该测试在Windows/MAC系统中启用");
    }

    @RepeatedTest(10) // Dynamic JavaScript expression.
    public void repeat10(){
        System.out.println("这条随机测试会重复测试10次");
    }

    @Disabled
    @Test
    void disableTest(){
        System.out.println(1);
    }

    @Test
    void contextLoads() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
        this.webTestClient.get()
                .uri("/events/42")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }

}
