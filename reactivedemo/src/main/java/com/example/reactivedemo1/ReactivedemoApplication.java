package com.example.reactivedemo1;

import com.example.reactivedemo.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactivedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivedemoApplication.class, args);
	}

	@Bean
	WebClient client(){
	    return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner demo(WebClient client){
	    return args -> {
	        client.get()
			        .uri("/events")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(cr->cr.bodyToFlux(Event.class))
			        .subscribe(System.out::println);
        };
    }

}
