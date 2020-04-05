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
    private final String SINGLE_BLOCK_URL = "https://blockchain.info/rawblock/%s";
    private final String SINGLE_TX_URL = "https://blockchain.info/rawtx/%s";
    private final String BLOCKS_FOR_ONE_DAY_URL = "https://blockchain.info/blocks/%d?format=json";
    private final String LATEST_BLOCK_URL = "https://blockchain.info/latestblock";


    @Autowired
    RestTemplate restTemplate;


    public Block getSingleBlock(String blockHash){
        return restTemplate.getForObject(String.format(SINGLE_BLOCK_URL,blockHash),Block.class);
    }


    public Transaction getSingleTx(String txHash){
        return restTemplate.getForObject(String.format(SINGLE_TX_URL,txHash),Transaction.class);
    }

    public Blocks getBlocksForOneDay(long timeInMillis){
        return restTemplate.getForObject(String.format(BLOCKS_FOR_ONE_DAY_URL,timeInMillis),Blocks.class);
    }

    public Blocks getFullBlockInfo(Blocks block){
        Block[] blocks = block.getBlocks();

        for(int i =0; i <1; i++){
            blocks[i] = restTemplate.getForObject(String.format(SINGLE_BLOCK_URL,blocks[i].getHash()),Block.class);
        }

        return block;
    }

    public Block getLatestBlock(){
        return restTemplate.getForObject(LATEST_BLOCK_URL, Block.class);
    }

    public List<Block> getLastBlocks(int count){
        Block initialBlock = getLatestBlock();

        ArrayList<Block> list = new ArrayList<>();


        String tempHash = initialBlock.getHash();
        int i = 0;
        while(i < count){
            Block prevBlock = restTemplate.getForObject(String.format(SINGLE_BLOCK_URL, tempHash), Block.class);
            list.add(prevBlock);
            tempHash = prevBlock.getPrev_block();
            i++;
        }
        return list;
    }


    public List<Transaction> getAllTransactions(List<Block> blocks){
        ArrayList<Transaction> result = new ArrayList<>();

        for(Block block : blocks){
            result.addAll(List.of(block.getTx()));
        }

        return result;
    }

}
