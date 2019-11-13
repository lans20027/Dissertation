package com.vydrin.dissertation;

import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Blocks;
import com.vydrin.dissertation.model.Transaction;
import com.vydrin.dissertation.services.BlockChainInfoService;
import com.vydrin.dissertation.statistic.BitcoinStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@SpringBootApplication
public class DissertationApplication {
    private static Logger log = LoggerFactory.getLogger(DissertationApplication.class);

    final String blocksForTime = "https://blockchain.info/blocks/%d?format=json";

    @Autowired
    BitcoinStats stats;

    public static void main(String[] args) {
        SpringApplication.run(DissertationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }


    @Bean
    public CommandLineRunner run(BlockChainInfoService service){
        return args -> {
            List<Block> blocks = service.getLastBlocks(10);



            log.info(stats.averageInputAndOut(blocks));
        };
    }

}
