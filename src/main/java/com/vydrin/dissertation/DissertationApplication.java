package com.vydrin.dissertation;

import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Blocks;
import com.vydrin.dissertation.model.Transaction;
import com.vydrin.dissertation.services.BlockChainInfoService;
import com.vydrin.dissertation.services.DataToExcelService;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


@SpringBootApplication
public class DissertationApplication {
    private static Logger log = LoggerFactory.getLogger(DissertationApplication.class);

    final String blocksForTime = "https://blockchain.info/blocks/%d?format=json";

    @Autowired
    BitcoinStats stats;

    @Autowired
    DataToExcelService dataToExcelService;

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
            /*List<Block> blocks = service.getLastBlocks(1);


            List<Transaction> allTx = service.getAllTransactions(blocks);

            for(int i =0; i < allTx.size(); i++) {
                Transaction tx = allTx.get(i);

                System.out.println(tx.allInputs() + " " + tx.allOuts() + " " + tx.getTransactionFee());
            }*/

            NavigableSet<Long> set = new TreeSet<Long>();

            set.addAll(Arrays.asList(1L,4L,7L,9L,12L,20L));
            System.out.println(set);

            System.out.println("ceiling(10):" + set.ceiling(10L));
            System.out.println("floor(15):" + set.floor(15L));
        };
    }



}
