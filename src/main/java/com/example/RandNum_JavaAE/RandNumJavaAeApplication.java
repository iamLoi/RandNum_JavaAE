package com.example.RandNum_JavaAE;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RandNumJavaAeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandNumJavaAeApplication.class, args);
	}

	@GetMapping("/")
	public int hello() {
		return new Random().nextInt(1000001);
	}

}
