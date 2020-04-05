package com.vydrin.dissertation.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {
    private String hash;

    private long ver;
    private long weight;
    private long block_height;
    private String relayed_by;
    private long lock_time;
    private long size;
    private boolean double_spend;
    private long block_index;
    private long time;
    private long tx_index;
    private long vin_sz;
    private long vout_sz;

    private Input[] inputs;

    private Out[] out;

    @Override
    public String toString() {
        return "Transaction{" +
                "hash='" + hash + '\'' +
                ", ver=" + ver +
                ", weight=" + weight +
                ", block_height=" + block_height +
                ", relayed_by='" + relayed_by + '\'' +
                ", lock_time=" + lock_time +
                ", size=" + size +
                ", double_spend=" + double_spend +
                ", block_index=" + block_index +
                ", time=" + time +
                ", tx_index=" + tx_index +
                "\n, vin_sz=" + vin_sz +
                "\n, vout_sz=" + vout_sz +
                "\n, inputs=" + Arrays.toString(inputs) +
                "\n, outs=" + Arrays.toString(out) +
                "\n}";
    }


    public Out[] getOuts() {
        return out;
    }

    public void setOuts(Out[] out) {
        this.out = out;
    }

    public long allInputs(){
        long allInputs = 0;

        if(inputs == null) return 0;

        for(Input input : inputs){
            Input.PrevOut prevOut = input.getPrev_out();
            if(prevOut != null){
                allInputs += Long.valueOf(prevOut.getValue());
            }
        }

        return allInputs;
    }

    public long allOuts(){
        long allOuts = 0;

        if(out == null) return 0;

        for(Out out : out){
            allOuts += Long.valueOf(out.getValue());
        }

        return allOuts;
    }

    public long getTransactionFee(){
        return allInputs() - allOuts();
    }

    public Input[] getInputs() {
        return inputs;
    }

    public void setInputs(Input[] inputs) {
        this.inputs = inputs;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getVer() {
        return ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getBlock_height() {
        return block_height;
    }

    public void setBlock_height(long block_height) {
        this.block_height = block_height;
    }

    public String getRelayed_by() {
        return relayed_by;
    }

    public void setRelayed_by(String relayed_by) {
        this.relayed_by = relayed_by;
    }

    public long getLock_time() {
        return lock_time;
    }

    public void setLock_time(long lock_time) {
        this.lock_time = lock_time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isDouble_spend() {
        return double_spend;
    }

    public void setDouble_spend(boolean double_spend) {
        this.double_spend = double_spend;
    }

    public long getBlock_index() {
        return block_index;
    }

    public void setBlock_index(long block_index) {
        this.block_index = block_index;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTx_index() {
        return tx_index;
    }

    public void setTx_index(long tx_index) {
        this.tx_index = tx_index;
    }

    public long getVin_sz() {
        return vin_sz;
    }

    public void setVin_sz(long vin_sz) {
        this.vin_sz = vin_sz;
    }

    public long getVout_sz() {
        return vout_sz;
    }

    public void setVout_sz(long vout_sz) {
        this.vout_sz = vout_sz;
    }
}
