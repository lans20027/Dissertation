package com.vydrin.dissertation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {
    private String hash;
    private long ver;
    private String prev_block;
    private String mrkl_root;
    private long time;
    private Transaction[] tx;

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", ver=" + ver +
                ", prev_block='" + prev_block + '\'' +
                ", mrkl_root='" + mrkl_root + '\'' +
                ", time=" + time +
                ", tx=" + Arrays.toString(tx) +
                '}';
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

    public String getPrev_block() {
        return prev_block;
    }

    public void setPrev_block(String prev_block) {
        this.prev_block = prev_block;
    }

    public String getMrkl_root() {
        return mrkl_root;
    }

    public void setMrkl_root(String mrkl_root) {
        this.mrkl_root = mrkl_root;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Transaction[] getTx() {
        return tx;
    }

    public void setTx(Transaction[] tx) {
        this.tx = tx;
    }
}
