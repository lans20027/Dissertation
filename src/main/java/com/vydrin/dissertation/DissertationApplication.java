package com.vydrin.dissertation;

import com.vydrin.dissertation.model.Block;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class DissertationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate){
        return args -> {
            String url = "https://blockchain.info/rawblock/0000000000000bae09a7a393a8acded75aa67e46cb81f7acaa5ad94f9eacd103";
            Block block = restTemplate.getForObject(url,Block.class);

            System.out.println(block);
        };
    }

}
