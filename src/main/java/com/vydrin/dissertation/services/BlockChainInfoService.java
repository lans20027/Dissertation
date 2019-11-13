package com.vydrin.dissertation.services;

import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Blocks;
import com.vydrin.dissertation.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for fetching data from blockchain.com
 * https://www.blockchain.com/api/blockchain_api
 * */
@Service
public class BlockChainInfoService {
    private final String singleBlockURL = "https://blockchain.info/rawblock/%s";
    private final String singleTxURL = "https://blockchain.info/rawtx/%s";
    private final String blocksForOneDayURL = "https://blockchain.info/blocks/%d?format=json";


    @Autowired
    RestTemplate restTemplate;



    public Block getSingleBlock(String blockHash){
        return restTemplate.getForObject(String.format(singleBlockURL,blockHash),Block.class);
    }


    public Transaction getSingleTx(String txHash){
        return restTemplate.getForObject(String.format(singleTxURL,txHash),Transaction.class);
    }

    public Blocks getBlocksForOneDay(long timeInMillis){
        return restTemplate.getForObject(String.format(blocksForOneDayURL,timeInMillis),Blocks.class);
    }
}
