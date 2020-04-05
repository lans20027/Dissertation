package com.vydrin.dissertation;

import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Transaction;
import com.vydrin.dissertation.services.BlockChainInfoService;
import com.vydrin.dissertation.services.DataToExcelService;
import com.vydrin.dissertation.statistic.BitcoinStats;
import com.vydrin.dissertation.util.TransactionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@SpringBootApplication
public class DissertationApplication {
    private static Logger log = LoggerFactory.getLogger(DissertationApplication.class);

    @Autowired
    private BitcoinStats stats;

    @Autowired
    private DataToExcelService dataToExcelService;

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
/*
            List<Block> blocks = service.getLastBlocks(1);
            List<Transaction> allTx = service.getAllTransactions(blocks);
            System.out.println(allTx.size());

            Map<Double, List<Transaction>> map = TransactionsUtils.getDistribution(allTx);

            map.forEach((aDouble, transactions) -> {
                System.out.println(aDouble + " - " + transactions.size());
            });
*/
        };
    }



}
