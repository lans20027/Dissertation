package com.vydrin.dissertation.services;

import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Blocks;
import com.vydrin.dissertation.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for fetching data from blockchain.com
 * https://www.blockchain.com/api/blockchain_api
 * */
@Service
public class BlockChainInfoService {
    private final String singleBlockURL = "https://blockchain.info/rawblock/%s";
    private final String singleTxURL = "https://blockchain.info/rawtx/%s";
    private final String blocksForOneDayURL = "https://blockchain.info/blocks/%d?format=json";
    private final String latestBlockURL = "https://blockchain.info/latestblock";


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

    public Blocks getFullBlockInfo(Blocks block){
        Block[] blocks = block.getBlocks();

        for(int i =0; i <1; i++){
            blocks[i] = restTemplate.getForObject(String.format(singleBlockURL,blocks[i].getHash()),Block.class);
        }

        return block;
    }

    public Block getLatestBlock(){
        return restTemplate.getForObject(latestBlockURL, Block.class);
    }

    public List<Block> getLastBlocks(int count){
        Block initialBlock = getLatestBlock();

        ArrayList<Block> list = new ArrayList<>();


        String tempHash = initialBlock.getHash();
        int i = 0;
        while(i < count){
            Block prevBlock = restTemplate.getForObject(String.format(singleBlockURL, tempHash), Block.class);
            list.add(prevBlock);
            tempHash = prevBlock.getPrev_block();
            i++;
        }

        return list;

    }

}
