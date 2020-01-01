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
            List<Block> blocks = service.getLastBlocks(1);


            List<Transaction> allTx = service.getAllTransactions(blocks);

            NavigableMap<Long, List<Transaction>> map = new TreeMap<>();
            long initial = 0;
            long h = 500_000;

            for(int i =0; i < 100; i++){
                map.put(initial, new ArrayList<>());
                initial += h;
            }

            for(int i =0; i < 10; i++){
                initial += 50_000_000;
                map.put(initial, new ArrayList<>());
            }


            int count = 0;
            for(Transaction x : allTx){
                Long l = map.ceilingKey(x.allOuts());
                if(l == null){
                    count++;
//                    System.out.println(x.allOuts()/100_000_000.0);
                }else {
                    List<Transaction> list = map.get(l);
                    list.add(x);
                }
            }

            SortedSet<Map.Entry<Long,List<Transaction>>> sortedSet
                    = new TreeSet<>(Comparator.comparingInt(o -> o.getKey().intValue()));

            sortedSet.addAll(map.entrySet());

            for(Map.Entry<Long,List<Transaction>> entry : sortedSet){
                System.out.println(entry.getKey() + " - " + entry.getValue().size());
            }

//            dataToExcelService.txDistributionToFile("distribution.xls", sortedSet, allTx.size());
            List<Transaction> listTx = map.get(100_000_000L);
            if(listTx != null){
                for (Transaction x : listTx){
                    System.out.println(x.getHash() + " " + x.allInputs() + " " + x.allOuts()/100_000_000.0 );
                }
            }

        };
    }



}
