package com.vydrin.dissertation.util;

import com.vydrin.dissertation.model.Transaction;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TransactionsUtils {
    private static final Log LOG = LogFactory.getLog(TransactionsUtils.class);

    /**
     * Find key with maximum size of transaction list.
     *
     * @param distribution - Distribution of transactions
     * @return maximum key
     * */
    public static Double maxOfDistribution(Map<Double, List<Transaction>> distribution){
        int max = 0;
        Double maxKey = 0.0;

        for(Map.Entry<Double, List<Transaction>> entry : distribution.entrySet()){
            if(entry.getValue().size() > max){
                max = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        return maxKey;
    }

    /**
     * Find transaction with maximum
     * summary of output value.
     *
     * @param tx list of all transactions
     * @return "max" transaction
     * */
    public static Transaction getMax(List<Transaction> tx){
        long max = tx.get(0).allOuts();
        Transaction tMax = tx.get(0);

        for(Transaction t : tx){
            if(t.allOuts() > max){
                max = t.allOuts();
                tMax = t;
            }
        }

        return  tMax;
    }

    /**
     * Make transaction distribution.
     * Takes the maximum value of transaction as end point (100%).
     * Starting from zero in increments of 1 percent (up to 100%),
     * counts the number of transactions in each range (0%-1%,1%-2%...).
     *
     * @param transactions - List of all Transactions.
     * @return Distribution of transactions presented as TreeMap.
     * */
    public static Map<Double, List<Transaction>>  getDistribution(List<Transaction> transactions){
        Transaction maxTx = getMax(transactions);

        double maxValue = maxTx.allOuts() / 100_000_000.0;

        TreeMap<Double, List<Transaction>> result =
                new TreeMap<>();

        double step = maxValue / 100;

        for(int i = 0; i <= 100; i++) {
            result.put(i * step, new ArrayList<>());
        }

        for(Transaction tx : transactions) {
            result.merge(result.ceilingKey(tx.allOuts() / 100_000_000.0), List.of(tx), (transactionList, transactions2) -> {
                transactionList.addAll(transactions2);
                return transactionList;
            });
        }

        return  result;
    }

    /**
     * Make transaction distribution
     * Takes the maximum value of transaction as end point (100%).
     * Starting from zero in increments of 1 percent (up to 100%),
     * counts the number of transactions in each range (0%-1%,1%-2%...),
     * until range percentage limit reached (eg. 0%-1% range has less than max percentage limit)
     *
     * @param percentageLimit - max percent of all Tx's to be
     *                        in one range
     * @param transactions - List of all Transactions.
     * @return Distribution of transactions presented as TreeMap.
     * */
    public static Map<Double, List<Transaction>> getDistribution(List<Transaction> transactions, Integer percentageLimit){
        double all = transactions.size();
        int maxPercent = 0;
        double maxKey = 0;

        LOG.info("Start distribution process: allTx:" + all);

        Map<Double, List<Transaction>> map = getDistribution(transactions);
        maxKey = maxOfDistribution(map);
        maxPercent = (int) ((map.get(maxKey).size() / all) * 100.0);

        if(maxPercent <= percentageLimit) return map;

        while(maxPercent > percentageLimit){
            map = getDistribution(map.get(maxKey));

            maxKey = maxOfDistribution(map);
            maxPercent = (int)  ((map.get(maxKey).size() / all) * 100.0);
            all = map.get(maxKey).size();
            LOG.info("Max percent: " + maxPercent + ", all:" + all);
        }

        LOG.info("End distribution process: ");

        return map;
    }
}
