package com.vydrin.dissertation.statistic;


import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Transaction;
import com.vydrin.dissertation.services.BlockChainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BitcoinStats {
    private static Logger log = LoggerFactory.getLogger(BitcoinStats.class);

    @Autowired
    private BlockChainInfoService service;

    private long count;
    private long inputs = 0;
    private long outs = 0;


    public long[][] averageInputAndOut(List<Block> list){
        long[][] result = new long[1000][1000];

        for(Block block : list){
            Transaction[] txs = block.getTx();
            log.info("block tx:" + block.getHash());
            for(Transaction tx : txs){
                count++;
                inputs += tx.getVin_sz();
                outs += tx.getVout_sz();
                result[(int) tx.getVin_sz()][(int) tx.getVout_sz()]++;
            }
        }

        return result;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void refresh(){
        count = inputs = outs = 0;
    }

    public BlockChainInfoService getService() {
        return service;
    }

    public void setService(BlockChainInfoService service) {
        this.service = service;
    }

    public long getInputs() {
        return inputs;
    }

    public void setInputs(long inputs) {
        this.inputs = inputs;
    }

    public long getOuts() {
        return outs;
    }

    public void setOuts(long outs) {
        this.outs = outs;
    }
}
