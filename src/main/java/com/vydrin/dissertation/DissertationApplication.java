package com.vydrin.dissertation;

import com.vydrin.dissertation.model.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@SpringBootApplication
public class DissertationApplication {
    private static Logger log = LoggerFactory.getLogger(DissertationApplication.class);

    final String blocksForTime = "https://blockchain.info/blocks/%d?format=json";
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
            log.info(String.format(blocksForTime,new Date().getTime()));

            Block block = restTemplate.getForObject(String.format(blocksForTime,new Date().getTime()),Block.class);

            System.out.println(block);
        };
    }

}
