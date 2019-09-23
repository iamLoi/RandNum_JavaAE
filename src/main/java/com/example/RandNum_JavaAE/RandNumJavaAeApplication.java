package com.example.RandNum_JavaAE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

	@GetMapping("/timing")
	public static String[] get() throws IOException {

		String[] urls = { "https://random-number-generator-251217.appspot.com/", "http://35.239.94.178:8080/",
				"https://melodic-lantern-252322.appspot.com/", "https://python-compute-252322.appspot.com/" };

		long startTime = System.nanoTime();

		String[] randNums = new String[5];

		HttpClient client = new DefaultHttpClient();
		HttpGet request;
		int i = 0;
		for (String url : urls) {
			request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", "Mozilla/5.0");

			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			randNums[i] = result.toString();
			i++;

		}

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;

		randNums[i++] = "Took " + totalTime + " nanoseconds";

		return randNums;
	}

}
