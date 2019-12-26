package com.vydrin.dissertation.statistic;


import com.vydrin.dissertation.model.Block;
import com.vydrin.dissertation.model.Transaction;
import com.vydrin.dissertation.services.BlockChainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BitcoinStats {
    private static Logger log = LoggerFactory.getLogger(BitcoinStats.class);

    @Autowired
    private BlockChainInfoService service;

    private long count;
    private long inputs = 0;
    private long outs = 0;


    public TreeSet<String> averageInputAndOut(List<Block> list){
        TreeMap<String,Long> hm = new TreeMap<>();
        TreeSet<String> result = new TreeSet<>((a,b) -> {
            String aVal = a.split(":")[1];
            String bVal = b.split(":")[1];

            return Integer.compare(Integer.parseInt(bVal),Integer.parseInt(aVal));
        });

        for(Block block : list){
            Transaction[] txs = block.getTx();
            for(Transaction tx : txs){
                count++;
                inputs += tx.getVin_sz();
                outs += tx.getVout_sz();

                String key = tx.getVin_sz() + "-" + tx.getVout_sz();

                if(!hm.containsKey(key)){
                    hm.put(key, 1L);
                } else {
                    hm.put(key, hm.get(key) + 1);
                }
            }
        }



        log.info("ALL BLOCKS:" + list.size());
        log.info("ALL TX:" + count);
        log.info("ALL INPUTS:" + inputs);
        log.info("ALL OUTPUTS:" + outs);

        for(Map.Entry<String,Long> e : hm.entrySet()){
            result.add(e.getKey() + ":" + e.getValue());
        }

/*        for(Map.Entry<String,Long> e : hm.entrySet()){
            if(e.getValue() > 10)
                log.info(e.getKey() + ":" + e.getValue());
        }*/

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
