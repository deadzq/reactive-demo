package com.example.reactivedemo;

import org.junit.Before;
import org.junit.gen5.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

/*
https://blog.csdn.net/io_field/article/details/82801067
JUnit 5
 */
@SpringBootTest
class ReactiveDemoApplicationTests {

    private WebTestClient webTestClient;

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    public void onlyOnJava8() {
        System.out.println("该测试在JDK 8中启用");
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
